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
        var result = 1;
        var winnersList = winnersListPart1();
        for (long winnersCount : winnersList) {
            result *= winnersCount;
        }
        return result;
    }

    public List<Long> winnersListPart1() {
        var lines = content.lines().toList();
        var times = parseValues(lines.get(0));
        var distances = parseValues(lines.get(1));
        return IntStream.range(0, times.size())
                .mapToObj(i -> winnersList(Long.parseLong(times.get(i)), Long.parseLong(distances.get(i)))).toList();
    }

    private long winnersList(long time, long distance) {
        var result = new ArrayList<Long>();
        for (long i = 0; i < time; i++) {
            var speed = i;
            var myDistance = speed * (time - i);
            if (myDistance > distance) {
                result.add(i);
            }
        }
        return result.size();
    }

    public long winnersCountPart2() {
        var lines = content.lines().toList();
        var time = Long.parseLong(String.join("", parseValues(lines.get(0))));
        var distance = Long.parseLong(String.join("", parseValues(lines.get(1))));
        return winnersList(time, distance);
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
