package com.xyzcorp.instructor.patternmatching;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatternMatchingTest {
    @Test
    void testEnsureThatMatchWorks() {
        assertThat(PatternMatching.match("Hello"))
            .isEqualTo("Hello to you");
    }

    @Test
    void testCompoundMatch() {
        assertThat(PatternMatching.compoundMatch("Hello"))
            .isEqualTo("not a string or not an empty string");

        assertThat(PatternMatching.compoundMatch(""))
            .isEqualTo("empty string");
    }

}
