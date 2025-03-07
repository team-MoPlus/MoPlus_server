package com.moplus.moplus_server.domain.problemset.service;

import com.moplus.moplus_server.domain.concept.domain.ConceptTag;
import com.moplus.moplus_server.domain.concept.repository.ConceptTagRepository;
import com.moplus.moplus_server.domain.problem.domain.problem.Problem;
import com.moplus.moplus_server.domain.problem.repository.ProblemRepository;
import com.moplus.moplus_server.domain.problemset.domain.ProblemSet;
import com.moplus.moplus_server.domain.problemset.dto.response.ProblemSetGetResponse;
import com.moplus.moplus_server.domain.problemset.dto.response.ProblemSummaryResponse;
import com.moplus.moplus_server.domain.problemset.repository.ProblemSetRepository;
import com.moplus.moplus_server.domain.publish.domain.Publish;
import com.moplus.moplus_server.domain.publish.repository.PublishRepository;
import com.moplus.moplus_server.global.error.exception.BusinessException;
import com.moplus.moplus_server.global.error.exception.ErrorCode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProblemSetGetService {

    private final ProblemSetRepository problemSetRepository;
    private final ProblemRepository problemRepository;
    private final ConceptTagRepository conceptTagRepository;
    private final PublishRepository publishRepository;

    @Transactional(readOnly = true)
    public ProblemSetGetResponse getProblemSet(Long problemSetId) {
        ProblemSet problemSet = problemSetRepository.findByIdElseThrow(problemSetId);
        if (problemSet.isDeleted()) {
            throw new BusinessException(ErrorCode.DELETE_PROBLEM_SET_GET_ERROR);
        }
        List<LocalDate> publishedDates = publishRepository.findByProblemSetId(problemSetId).stream()
                .map(Publish::getPublishedDate)
                .toList();

        List<ProblemSummaryResponse> problemSummaries = new ArrayList<>();
        for (Long problemId : problemSet.getProblemIds()) {
            Problem problem = problemRepository.findByIdElseThrow(problemId);
            Set<String> tagNames = new HashSet<>(
                    conceptTagRepository.findAllByIdsElseThrow(problem.getConceptTagIds())
                            .stream()
                            .map(ConceptTag::getName)
                            .toList());
            problem.getChildProblems().stream()
                    .map(childProblem -> conceptTagRepository.findAllByIdsElseThrow(childProblem.getConceptTagIds()))
                    .forEach(conceptTags -> tagNames.addAll(conceptTags.stream().map(ConceptTag::getName).toList()));
            problemSummaries.add(ProblemSummaryResponse.of(problem, tagNames));
        }
        return ProblemSetGetResponse.of(problemSet, publishedDates, problemSummaries);
    }
}
