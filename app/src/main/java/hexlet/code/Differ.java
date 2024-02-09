package hexlet.code;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {
    public static String getFileData(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new RuntimeException("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static String getFileFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return filePath.substring(index + 1);
    }

    public static Map<String, Map<String, Object>> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        Map<String, Map<String, Object>> diffMap = new LinkedHashMap<>();

        for (String key : keys) {
            Map<String, Object> valueMap = new LinkedHashMap<>();
            if (data1.containsKey(key) && !data2.containsKey(key)) {
                valueMap.put("status", "deleted");
                valueMap.put("value", data1.get(key));
            } else if (!data1.containsKey(key) && data2.containsKey(key)) {
                valueMap.put("status", "added");
                valueMap.put("value", data2.get(key));
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                valueMap.put("status", "changed");
                valueMap.put("oldValue", data1.get(key));
                valueMap.put("newValue", data2.get(key));
            } else {
                valueMap.put("status", "unchanged");
                valueMap.put("value", data1.get(key));
            }
            diffMap.put(key, valueMap);
        }

        return diffMap;
    }

    public static String format(Map<String, Map<String, Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : diffMap.keySet()) {
            Map<String, Object> valueMap = diffMap.get(key);
            Object status = valueMap.get("status");
            if (status.equals("deleted")) {
                result.append("  - ").append(key).append(": ").append(valueMap.get("value")).append("\n");
            } else if (status.equals("added")) {
                result.append("  + ").append(key).append(": ").append(valueMap.get("value")).append("\n");
            } else if (status.equals("changed")) {
                result.append("  - ").append(key).append(": ").append(valueMap.get("oldValue")).append("\n");
                result.append("  + ").append(key).append(": ").append(valueMap.get("newValue")).append("\n");
            } else if (status.equals("unchanged")) {
                result.append("    ").append(key).append(": ")
                        .append(valueMap.get("value")).append("\n");
            }
        }
        result.append("}");

        return result.toString();
    }
    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        String firstFileData = getFileData(firstFilePath);
        String secondFileData = getFileData(secondFilePath);

        String firstFileFormat = getFileFormat(firstFilePath);
        String secondFileFormat = getFileFormat(secondFilePath);

        Map<String, Object> firstFileParsed = Parser.parseData(firstFileData, firstFileFormat);
        Map<String, Object> secondFileParsed = Parser.parseData(secondFileData, secondFileFormat);

        Map<String, Map<String, Object>> diff = genDiff(firstFileParsed, secondFileParsed);

        return format(diff);
    }
}