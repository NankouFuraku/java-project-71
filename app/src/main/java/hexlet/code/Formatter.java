package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formats.Json;
import hexlet.code.formats.Plain;
import hexlet.code.formats.Stylish;

import java.util.Map;

public class Formatter {
    public static String reformat(Map<String, Map<String, Object>> diff, String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.format(diff);
            case "plain" -> Plain.format(diff);
            case "json" -> Json.format(diff);
            default -> throw new RuntimeException("Unsupported format: " + format);
        };
    }
}
