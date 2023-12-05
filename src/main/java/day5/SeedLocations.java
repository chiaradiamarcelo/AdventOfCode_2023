package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class SeedLocations {
    private final String content;

    public SeedLocations(String content) {
        this.content = content;
    }

    public long lowestSoil() {
        var scanner = new Scanner(content);
        var seeds = seedsOf(scanner.nextLine());
        scanner.nextLine();
        return fewest(seeds, scanner).stream().mapToLong(v -> v).min().orElseThrow();
    }

    private List<Long> fewest(List<Long> seeds, Scanner scanner) {
        if (!scanner.hasNext()) {
            return seeds;
        }
        scanner.nextLine();
        var mapNumbers = mapNumbersOf(scanner);
        var newSeeds = seeds.stream().map(seed -> mapFor(mapNumbers, seed)).toList();
        return fewest(newSeeds, scanner);
    }

    private long mapFor(List<long[]> mapNumbers, long seed) {
        for (long[] mapLine : mapNumbers) {
            var destinationRange = mapLine[0];
            var sourceRange = mapLine[1];
            var rangeLength = mapLine[2];
            if (seed >= sourceRange && seed <= sourceRange + rangeLength) {
                var offset = seed - sourceRange;
                return destinationRange + offset;
            }
        }
        return seed;
    }

    private List<long[]> mapNumbersOf(Scanner scanner) {
        var result = new ArrayList<long[]>();
        var lastLine = "lastLine";
        while (scanner.hasNext() && lastLine != "") {
            lastLine = scanner.nextLine();
            if (lastLine != "") {
                var soilMatcher = Pattern.compile("\\d+").matcher(lastLine);
                soilMatcher.find();
                var destinationRange = Long.parseLong(soilMatcher.group());
                soilMatcher.find();
                var sourceRange = Long.parseLong(soilMatcher.group());
                soilMatcher.find();
                var rangeLength = Long.parseLong(soilMatcher.group());

                result.add(new long[] { destinationRange, sourceRange, rangeLength });
            }
        }
        return result;
    }

    private List<Long> seedsOf(String nextLine) {
        var seeds = new ArrayList<Long>();
        var matcher = Pattern.compile("\\d+").matcher(nextLine);
        while (matcher.find()) {
            var startRange = Long.parseLong(matcher.group());
            matcher.find();
            var endRange = Long.parseLong(matcher.group()) + startRange + 1;
            LongStream.range(startRange, endRange).forEach(seeds::add);
        }
        return seeds;
    }
}
