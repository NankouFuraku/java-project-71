package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parseData(String data, String fileFormat) throws IOException {
        switch (fileFormat) {
            case "json" -> {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(data, new TypeReference<>() {
                });
            }
            case "yaml", "yml" -> {
                ObjectMapper mapper = new YAMLMapper();
                return mapper.readValue(data, new TypeReference<>() {
                });
            }
            default -> throw new RuntimeException("Unexpected format: " + fileFormat);
        }

    }
}
