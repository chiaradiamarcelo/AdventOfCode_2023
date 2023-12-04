package day4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Scratchcards {
    private final String content;

    public Scratchcards(String content) {
        this.content = content;
    }

    public int points() {
        return content.lines().mapToInt(this::pointsOf).sum();
    }

    private int pointsOf(String line) {
        var winningNumbersCount = winnersCount(line);
        return winningNumbersCount == 0 ? 0 : (int) Math.pow(2, winningNumbersCount - 1);
    }

    private int winnersCount(String line) {
        var splitLine = line.split(":")[1].split("\\|");
        var winningNumbers = numbersIn(splitLine[0]);
        var myNumbers = numbersIn(splitLine[1]);
        return (int) myNumbers.stream().filter(winningNumbers::contains).count();
    }

    private List<Integer> numbersIn(String string) {
        return Arrays.stream(string.split(" ")).filter(f -> !f.equals("")).map(Integer::parseInt).toList();
    }

    public int scratchcardsCount() {
        var winnersCountByLineId = content.lines().collect(Collectors.toMap(this::idOf, this::winnersCount));
        return winnersCountByLineId.keySet().stream().mapToInt(id -> totalCountOf(id, winnersCountByLineId)).sum();
    }

    private int totalCountOf(int lineId, Map<Integer, Integer> winnersCountByLineId) {
        var totalCount = 1;
        var winnersCount = winnersCountByLineId.get(lineId);
        for (int i = 0; i < winnersCount; i++) {
            var nextLineId = lineId + i + 1;
            totalCount += totalCountOf(nextLineId, winnersCountByLineId);
        }
        return totalCount;
    }

    private int idOf(String line) {
        var matcher = Pattern.compile("\\d+").matcher(line);
        matcher.find();
        return Integer.parseInt(matcher.group());
    }
}
