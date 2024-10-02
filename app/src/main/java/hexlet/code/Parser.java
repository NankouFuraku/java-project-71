package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseData(String data, String format) throws IOException {
        var mapper = switch (format) {
            case "yaml", "yml" -> new YAMLMapper();
            case "json" -> new ObjectMapper();
            default -> throw new IOException("Invalid format: " + format);

        };
        return mapper.readValue(data, new TypeReference<>() { });
    }
}
