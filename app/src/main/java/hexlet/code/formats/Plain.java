package hexlet.code.formats;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(Map<String, Map<String, Object>> diff) {
        var result = new StringBuilder();

        for (var key : diff.keySet()) {
            var statusMap = diff.get(key);
            switch (statusMap.get("status").toString()) {
                case "added" -> result.append("Property '").append(key).append("' was added with value: ")
                        .append(analyzeValue(statusMap.get("value"))).append("\n");
                case "deleted" -> result.append("Property '").append(key).append("' was removed").append("\n");
                case "unchanged" -> { }
                case "changed" -> result.append("Property '").append(key).append("' was updated. From ")
                        .append(analyzeValue(statusMap.get("oldValue"))).append(" to ")
                        .append(analyzeValue(statusMap.get("newValue"))).append("\n");
                default -> throw new RuntimeException("Invalid status: " + statusMap.get("status"));
            }
        }

        return result.toString().trim();
    }

    public static String analyzeValue(Object value) {
        if (value instanceof Map<?, ?> || value instanceof Array || value instanceof List<?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return String.valueOf(value);
        }
    }
}
