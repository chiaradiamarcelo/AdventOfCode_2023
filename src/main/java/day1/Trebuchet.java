package day1;

import java.util.*;

public class Trebuchet {
    private static final List<String> DIGITS = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two",
            "three", "four", "five", "six", "seven", "eight", "nine");
    private static final Map<String, Integer> DIGIT_VALUES = new HashMap<>() {
        {
            putAll(Map.of("1", 1, "2", 2, "3", 3, "4", 4, "5", 5, "6", 6, "7", 7, "8", 8, "9", 9));
            putAll(Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8,
                    "nine", 9));
        }
    };

    private final String content;

    public Trebuchet(String content) {
        this.content = content;
    }

    public int calibrationValuesSum() {
        return content.lines().mapToInt(this::lineValue).sum();
    }

    private int lineValue(String line) {
        var firstDigitValue = digitValueOf(line, Comparator.comparing(line::indexOf));
        var lastDigitValue = digitValueOf(line, Comparator.comparing((String n) -> line.lastIndexOf(n)).reversed());
        return firstDigitValue * 10 + lastDigitValue;
    }

    private int digitValueOf(String line, Comparator<String> comparator) {
        return DIGITS.stream().filter(line::contains).sorted(comparator).map(DIGIT_VALUES::get).findFirst().orElse(0);
    }
}
