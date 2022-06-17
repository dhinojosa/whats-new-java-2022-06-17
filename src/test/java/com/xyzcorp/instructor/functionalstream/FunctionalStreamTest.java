package com.xyzcorp.instructor.functionalstream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalStreamTest {
    @Test
    void testMapGet() {
        Map<Integer, String> integerStringMap =
            Map.of(1, "One", 2, "Two");
        String s = integerStringMap.get(3);
        System.out.println(s);

        Optional<String> s1 = Optional.ofNullable(s);
        String result = s1.map(v -> v + "!").orElse("I found nothing");
        System.out.println(result);
    }

    @Test
    void testOptionalWithAPossibleException() {
        Stream<Integer> result =
            Stream.of(1, 50, 0, 20, 10)
                  .flatMap(i -> {
                      try {
                          return Stream.of(100 / i);
                      } catch (
                          ArithmeticException e) {
                          return Stream.empty();
                      }
                  });
        System.out.println(result.toList());
    }


    @Test
    void testOptionalStream() {
        Stream<Optional<Integer>> os =
            Stream.of(Optional.of(2), Optional.empty(), Optional.of(30));
        Stream<Integer> integerStream = os
            .flatMap(optionalInteger -> optionalInteger.stream());
        System.out.println(integerStream.collect(Collectors.toList()));
    }

    @Test
    void testTakeWhile() {
        System.out.println(Stream
            .iterate(0, integer -> integer + 1)
            .takeWhile(i -> i < 100)
            .map(String::valueOf)
            .collect(Collectors.toSet()));
    }

    @Test
    void testDropWhile() {
        List<Integer> result =
            Stream.iterate(0, integer -> integer + 1)
                  .dropWhile(i -> i < 5)
                  .filter(i -> i % 2 == 0)
                  .takeWhile(i -> i < 50)
                  .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    void testThreeArgIterable() {
        Stream<Integer> stream = Stream.iterate(0, i -> i < 5, i -> i + 1);
        System.out.println(stream.collect(Collectors.toList()));
    }

    @Test
    void testTeeing() {
        Double average =
            Stream.of(100, 100, 90, 50, 40, 80, 90, 100)
                  .collect(Collectors.teeing(
                      Collectors.summingDouble(value -> value),
                      Collectors.counting(),
                      (sum, count) -> sum / count));
        System.out.println(average);
    }

}
