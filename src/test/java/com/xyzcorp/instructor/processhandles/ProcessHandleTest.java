package com.xyzcorp.instructor.processhandles;

import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessHandleTest {
    @Test
    void processHandlesTest() {
        System.out.printf("Process Starting on %s%n", ProcessHandle.current().pid());
    }

    @Test
    void processDescendentsTest() {
        ProcessHandle current = ProcessHandle.current();
        Stream<ProcessHandle> descendants = current.descendants();
        System.out.printf("Process Descendents %s%n", descendants.toList());
        System.out.printf("Process Descendents %s%n", descendants.collect(Collectors.toList()));
    }
}
