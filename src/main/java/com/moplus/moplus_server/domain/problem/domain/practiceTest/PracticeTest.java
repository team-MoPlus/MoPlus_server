package com.moplus.moplus_server.domain.problem.domain.practiceTest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "practice_test_tag")
@NoArgsConstructor
public class PracticeTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int year;
    private int month;
    private Subject subject;
    private String area;

    public PracticeTest(String name, int year, int month, Subject subject) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.subject = subject;
        this.area = "수학";
    }
}
