package day2;

import java.util.regex.Pattern;

public class CubeConundrum {
    private final String content;
    private final int redLimit;
    private final int blueLimit;
    private final int greenLimit;

    public CubeConundrum(String content, int redLimit, int greenLimit, int blueLimit) {
        this.content = content;
        this.redLimit = redLimit;
        this.blueLimit = blueLimit;
        this.greenLimit = greenLimit;
    }

    public int idsSum() {
        return content.lines().mapToInt(this::idValueOf).sum();
    }

    private int idValueOf(String line) {
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

    private Integer maxOf(String color, String line) {
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
