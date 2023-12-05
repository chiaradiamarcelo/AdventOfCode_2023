package day5;

import com.google.common.collect.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SeedLocationsIntervals {
    private final String content;

    public SeedLocationsIntervals(String content) {
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
            var endRange = Long.parseLong(matcher.group()) + startRange - 1;
            var ranges = List.of(Range.closed(startRange, endRange));
            for (List<long[]> map : mappings) {
                ranges = mappedValueOf(ranges, map);
            }
            var minRange = ranges.stream().mapToLong(Range::lowerEndpoint).min().orElseThrow();
            min = Math.min(minRange, min);
        }
        return min;
    }

    private List<Range<Long>> mappedValueOf(List<Range<Long>> ranges, List<long[]> mapping) {
        var result = new ArrayList<Range<Long>>();
        var allToProcess = new ArrayList<>(ranges);

        for (long[] mapLine : mapping) {
            var toProcessLeft = new ArrayList<Range<Long>>();

            for (Range<Long> toProcess : allToProcess) {
                var destinationRange = mapLine[0];
                var sourceRange = mapLine[1];
                var rangeLength = mapLine[2];

                var mapRange = Range.closed(sourceRange, sourceRange + rangeLength - 1);

                if (toProcess.isConnected(mapRange)) {
                    var intersection = toProcess.intersection(mapRange);
                    if (intersection.upperEndpoint() < toProcess.upperEndpoint()) {
                        var leftover = Range.closed(intersection.upperEndpoint() + 1, toProcess.upperEndpoint());
                        toProcessLeft.add(leftover);
                    }
                    if (intersection.lowerEndpoint() > toProcess.lowerEndpoint()) {
                        var leftover = Range.closed(toProcess.lowerEndpoint(), intersection.lowerEndpoint() - 1);
                        toProcessLeft.add(leftover);
                    }
                    var offsetUpperbound = intersection.upperEndpoint() - sourceRange;
                    var offsetLowerbound = intersection.lowerEndpoint() - sourceRange;
                    result.add(Range.closed(destinationRange + offsetLowerbound, destinationRange + offsetUpperbound));
                } else {
                    toProcessLeft.add(toProcess);
                }
            }
            allToProcess = new ArrayList<>(toProcessLeft);
        }
        result.addAll(allToProcess);
        return result;
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
}
