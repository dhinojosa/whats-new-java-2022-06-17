package com.xyzcorp.instructor.varinference;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class TypeInferenceTest {
    @Test
    void testBeforeTypeInference() {
        List<String> cs = Collections.emptyList();
    }

    @Test
    void testAfterTypeInference() {
        var cs = Collections.<String>emptyList();
    }

    @Test
    void testCompleteExampleWithVar() {
        var path = Paths.get("web.log");
        try (var lines = Files.lines(path)) {
            var warningCount
                = lines
                .filter(line -> line.contains("WARNING"))
                .count();
            System.out.printf("Found %d warnings in the log file",
                warningCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void nonDenotableType() {
        record Person(String name, int age){}

        var b = new Person("Larry Java", 40);

        var a = new Object() {
            String name = "Larry Java";
            int age = 40;
        };
        System.out.println(a.age);
        System.out.println(a.name);
    }

    @Test
    void usingNonDenotableTypeWithStreams() {
        List.of(4, 3, 10, 11).stream().map(x -> new Object(){
            final int before = x - 1;
            final int after = x + 1;
            final int negative = -x;
        }).forEach(o ->
            System.out.printf("Before: %d, After: %d, Negative: %d\n",
                o.before, o.after, o.negative));
    }
}
