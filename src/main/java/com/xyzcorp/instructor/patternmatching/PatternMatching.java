package com.xyzcorp.instructor.patternmatching;

public class PatternMatching {
    public static String match(Object o) {
        if (o instanceof String s) {
            if ("Hello".equals(s)) return "Hello to you";
        } else if (o instanceof Integer i) {
            return String.valueOf(i);
        }
        return "Unknown";
    }

    public static String compoundMatch(Object o) {
        if (o instanceof String s && s.isEmpty()) {
            return "empty string";
        }
        else return "not a string or not an empty string";
    }
}
