package com.moplus.moplus_server.domain.problemset.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProblemSet is a Querydsl query type for ProblemSet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProblemSet extends EntityPathBase<ProblemSet> {

    private static final long serialVersionUID = -499971265L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProblemSet problemSet = new QProblemSet("problemSet");

    public final com.moplus.moplus_server.global.common.QBaseEntity _super = new com.moplus.moplus_server.global.common.QBaseEntity(this);

    public final EnumPath<ProblemSetConfirmStatus> confirmStatus = createEnum("confirmStatus", ProblemSetConfirmStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final ListPath<com.moplus.moplus_server.domain.problem.domain.problem.ProblemId, com.moplus.moplus_server.domain.problem.domain.problem.QProblemId> problemIds = this.<com.moplus.moplus_server.domain.problem.domain.problem.ProblemId, com.moplus.moplus_server.domain.problem.domain.problem.QProblemId>createList("problemIds", com.moplus.moplus_server.domain.problem.domain.problem.ProblemId.class, com.moplus.moplus_server.domain.problem.domain.problem.QProblemId.class, PathInits.DIRECT2);

    public final QTitle title;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public QProblemSet(String variable) {
        this(ProblemSet.class, forVariable(variable), INITS);
    }

    public QProblemSet(Path<? extends ProblemSet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProblemSet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProblemSet(PathMetadata metadata, PathInits inits) {
        this(ProblemSet.class, metadata, inits);
    }

    public QProblemSet(Class<? extends ProblemSet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.title = inits.isInitialized("title") ? new QTitle(forProperty("title")) : null;
    }

}

