package hexlet.code;

import hexlet.code.formats.Stylish;

import java.util.Map;

public class Formatter {
    public static String reformat(Map<String, Map<String, Object>> diff, String format) {
        return switch (format) {
            case "stylish" -> Stylish.format(diff);
            default -> throw new RuntimeException("Unsupported format: " + format);
        };
    }
}
