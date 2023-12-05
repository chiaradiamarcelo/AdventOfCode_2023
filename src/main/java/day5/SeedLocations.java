package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SeedLocations {
    private final String content;

    public SeedLocations(String content) {
        this.content = content;
    }

    public long lowest() {
        var scanner = new Scanner(content);
        var min = Long.MAX_VALUE;
        var matcher = Pattern.compile("\\d+").matcher(scanner.nextLine());
        scanner.nextLine();
        var mappings = mappingsFrom(scanner);
        while (matcher.find()) {
            var startRange = Long.parseLong(matcher.group());
            matcher.find();
            var endRange = Long.parseLong(matcher.group()) + startRange;
            for (long seed = startRange; seed <= endRange; seed++) {
                var next = seed;
                for (List<long[]> map : mappings) {
                    next = mappedValueOf(map, next);
                }
                min = Math.min(min, next);
            }
        }
        return min;
    }

    private List<List<long[]>> mappingsFrom(Scanner scanner) {
        var result = new ArrayList<List<long[]>>();
        while (scanner.hasNext()) {
            result.add(mappingFrom(scanner));
        }
        return result;
    }

    private List<long[]> mappingFrom(Scanner scanner) {
        var mapping = new ArrayList<long[]>();
        scanner.nextLine();
        var lastLine = "lastLine";
        while (scanner.hasNext() && !lastLine.isBlank()) {
            lastLine = scanner.nextLine();
            if (!lastLine.isBlank()) {
                var soilMatcher = Pattern.compile("\\d+").matcher(lastLine);
                soilMatcher.find();
                var destinationRange = Long.parseLong(soilMatcher.group());
                soilMatcher.find();
                var sourceRange = Long.parseLong(soilMatcher.group());
                soilMatcher.find();
                var rangeLength = Long.parseLong(soilMatcher.group());

                mapping.add(new long[] { destinationRange, sourceRange, rangeLength });
            }
        }
        return mapping;
    }

    private long mappedValueOf(List<long[]> mapping, long value) {
        for (long[] mapLine : mapping) {
            var destinationRange = mapLine[0];
            var sourceRange = mapLine[1];
            var rangeLength = mapLine[2];
            if (value >= sourceRange && value <= sourceRange + rangeLength - 1) {
                var offset = value - sourceRange;
                return destinationRange + offset;
            }
        }
        return value;
    }
}
