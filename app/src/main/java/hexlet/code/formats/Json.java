package hexlet.code.formats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Map;

public class Json {
    public static String format(Map<String, Map<String, Object>> diff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(diff);
    }
}
