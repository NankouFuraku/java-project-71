package hexlet.code.formats;

import java.util.Map;

public class Stylish {
    public static String format(Map<String, Map<String, Object>> diff) {
        var result = new StringBuilder();
        result.append("{\n");

        for (var key : diff.keySet()) {
            var statusMap = diff.get(key);
            switch (statusMap.get("status").toString()) {
                case "added" -> result.append("  + ").append(key).append(": ")
                        .append(statusMap.get("value")).append("\n");
                case "deleted" -> result.append("  - ").append(key).append(": ")
                        .append(statusMap.get("value")).append("\n");
                case "unchanged" -> result.append("    ").append(key).append(": ")
                        .append(statusMap.get("value")).append("\n");
                case "changed" -> result.append("  - ").append(key).append(": ")
                        .append(statusMap.get("oldValue")).append("\n")
                        .append("  + ").append(key).append(": ")
                            .append(statusMap.get("newValue")).append("\n");
                default -> throw new RuntimeException("Invalid status: " + statusMap.get("status"));
            }
        }

        result.append("}");

        return result.toString();
    }
}
