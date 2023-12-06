package day6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class BoatsRace {
    private final String content;

    public BoatsRace(String content) {
        this.content = content;
    }

    public long winnersCountPart1() {
        var lines = content.lines().toList();
        var times = parseValues(lines.get(0)).stream().map(Long::parseLong).toList();
        var distances = parseValues(lines.get(1)).stream().map(Long::parseLong).toList();
        return IntStream.range(0, times.size()).mapToObj(i -> winnersList(times.get(i), distances.get(i))).reduce(1L,
                (a, b) -> a * b);
    }

    public long winnersCountPart2() {
        var lines = content.lines().toList();
        var time = Long.parseLong(String.join("", parseValues(lines.get(0))));
        var distance = Long.parseLong(String.join("", parseValues(lines.get(1))));
        return winnersList(time, distance);
    }

    private long winnersList(long time, long distance) {
        var firstWinner = findFirstWinner(time, distance, time);
        var lastWinner = time - firstWinner;
        return lastWinner - firstWinner + 1;
    }

    private long findFirstWinner(long time, long distance, long high) {
        var index = -1L;
        var low = 1L;
        while (low <= high) {
            long mid = low + ((high - low) / 2);
            if (mid * (time - mid) <= distance) {
                low = mid + 1;
            } else {
                high = mid - 1;
                index = mid;
            }
        }
        return index;
    }

    private List<String> parseValues(String line) {
        var matcher = Pattern.compile("\\d+").matcher(line);
        var result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }
}
