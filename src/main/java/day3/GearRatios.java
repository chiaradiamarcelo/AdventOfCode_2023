package day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class GearRatios {
    private static final Pattern SYMBOL_PATTERN = Pattern.compile("[^a-z0-9 .]", Pattern.CASE_INSENSITIVE);
    private static final Pattern STAR_PATTERN = Pattern.compile("\\*", Pattern.CASE_INSENSITIVE);
    private final String content;

    public GearRatios(String content) {
        this.content = content;
    }

    public int partsSum() {
        String previousLine = null;
        String nextLine;
        var result = 0;
        var lines = content.lines().toList();
        for (int i = 0; i < lines.size(); i++) {
            nextLine = i == lines.size() - 1 ? null : lines.get(i + 1);
            result += partsSum(lines.get(i), previousLine, nextLine);
            previousLine = lines.get(i);
        }
        return result;
    }

    private int partsSum(String line, String previousLine, String nextLine) {
        var matcher = Pattern.compile("\\d+").matcher(line);
        var ret = 0;
        while (matcher.find()) {
            var number = Integer.parseInt(matcher.group());
            var startIndex = matcher.start();
            var endIndex = matcher.end();
            // left
            if (startIndex > 0) {
                var symbolIndex = indexOf(line, startIndex - 1, startIndex, SYMBOL_PATTERN);
                if (symbolIndex != -1) {
                    ret += number;
                }
            }
            // right
            if (endIndex < line.length()) {
                var symbolIndex = indexOf(line, endIndex, endIndex + 1, SYMBOL_PATTERN);
                if (symbolIndex != -1) {
                    ret += number;
                }
            }
            // up
            if (previousLine != null) {
                var symbolIndex = indexOf(previousLine, Math.max(0, startIndex - 1),
                        Math.min(line.length(), endIndex + 1), SYMBOL_PATTERN);
                if (symbolIndex != -1) {
                    ret += number;
                }
            }
            // down
            if (nextLine != null) {
                var symbolIndex = indexOf(nextLine, Math.max(0, startIndex - 1), Math.min(line.length(), endIndex + 1),
                        SYMBOL_PATTERN);
                if (symbolIndex != -1) {
                    ret += number;
                }
            }
        }
        return ret;
    }

    private int indexOf(String line, int startIndex, int endIndex, Pattern pattern) {
        var matcher = pattern.matcher(line.substring(startIndex, endIndex));
        if (matcher.find()) {
            return matcher.start() + startIndex;
        }
        return -1;
    }

    public int gearRatiosSum() {
        var gears = new HashMap<String, List<Integer>>();
        String previousLine = null;
        String nextLine;
        var result = 0;
        var lines = content.lines().toList();
        for (int i = 0; i < lines.size(); i++) {
            nextLine = i == lines.size() - 1 ? null : lines.get(i + 1);
            gearRatiosSum(lines.get(i), previousLine, nextLine, i, gears);
            previousLine = lines.get(i);
        }
        for (List<Integer> list : gears.values()) {
            if (list.size() == 2) {
                result += list.get(0) * list.get(1);
            }
        }
        return result;
    }

    private void gearRatiosSum(String line, String previousLine, String nextLine, int index,
            Map<String, List<Integer>> gears) {
        var matcher = Pattern.compile("\\d+").matcher(line);
        while (matcher.find()) {
            var number = Integer.parseInt(matcher.group());
            var startIndex = matcher.start();
            var endIndex = matcher.end();
            // left
            if (startIndex > 0) {
                var starIndex = indexOf(line, startIndex - 1, startIndex, STAR_PATTERN);
                if (starIndex != -1)
                    addToGears(index + "#" + starIndex, number, gears);
            }
            // right
            if (endIndex < line.length()) {
                var starIndex = indexOf(line, endIndex, endIndex + 1, STAR_PATTERN);
                if (starIndex != -1)
                    addToGears(index + "#" + starIndex, number, gears);
            }
            // up
            if (previousLine != null) {
                var starIndex = indexOf(previousLine, Math.max(0, startIndex - 1),
                        Math.min(line.length(), endIndex + 1), STAR_PATTERN);
                if (starIndex != -1)
                    addToGears(index - 1 + "#" + starIndex, number, gears);
            }
            // down
            if (nextLine != null) {
                var starIndex = indexOf(nextLine, Math.max(0, startIndex - 1), Math.min(line.length(), endIndex + 1),
                        STAR_PATTERN);
                if (starIndex != -1)
                    addToGears(index + 1 + "#" + starIndex, number, gears);
            }
        }
    }

    private void addToGears(String key, int value, Map<String, List<Integer>> gears) {
        var list = gears.getOrDefault(key, new ArrayList<>());
        list.add(value);
        gears.put(key, list);
    }
}
