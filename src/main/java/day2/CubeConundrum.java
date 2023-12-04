package day2;

import java.util.Optional;
import java.util.regex.Pattern;

public class CubeConundrum {
    private final String content;

    public CubeConundrum(String content) {
        this.content = content;
    }

    public int idsSum(int redLimit, int greenLimit, int blueLimit) {
        return content.lines().mapToInt(line -> idValueOf(line, redLimit, greenLimit, blueLimit)).sum();
    }

    private int idValueOf(String line, int redLimit, int greenLimit, int blueLimit) {
        if (maxOf("green", line) > greenLimit) {
            return 0;
        }
        if (maxOf("blue", line) > blueLimit) {
            return 0;
        }
        if (maxOf("red", line) > redLimit) {
            return 0;
        }
        return gameIdOf(line);
    }

    private int maxOf(String color, String line) {
        var matcher = Pattern.compile("\\d+ " + color).matcher(line);
        var max = 0;
        while (matcher.find()) {
            var count = Integer.parseInt(matcher.group().split(" ")[0]);
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    private int gameIdOf(String line) {
        return Integer.parseInt(line.split(":")[0].split(" ")[1]);
    }

    public int powerSum() {
        return content.lines().mapToInt(this::powerOf).sum();
    }

    public int powerOf(String line) {
        var maxGreen = maxOf("green", line);
        var maxRed = maxOf("red", line);
        var maxBlue = maxOf("blue", line);
        return Math.max(maxGreen, 1) * Math.max(maxRed, 1) * Math.max(maxBlue, 1);
    }
}
