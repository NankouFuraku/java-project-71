package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        var data1 = getData(filepath1);
        var data2 = getData(filepath2);

        var dataType = getFormat(filepath1);
        var dataType2 = getFormat(filepath2);

        var parsedData1 = Parser.parseData(data1, dataType);
        var parsedData2 = Parser.parseData(data2, dataType2);

        return getDiff(parsedData1, parsedData2);
    }

    public static String getData(String filepath) throws IOException {
        var path = Paths.get(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static String getFormat(String filepath) {
        var formatStart = filepath.lastIndexOf(".");
        return filepath.substring(formatStart + 1);
    }

    public static String getDiff(Map<String, Object> data1, Map<String, Object> data2) {
        var result = new StringBuilder();
        result.append("{\n");

        var keys = new TreeSet<String>(data1.keySet());
        keys.addAll(data2.keySet());

        keys.forEach(key -> {
            if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            } else if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else if (data1.get(key).equals(data2.get(key))) {
                result.append("    ").append(key).append(": ").append(data1.get(key)).append("\n");
            } else {
                result.append("  - ").append(key).append(": ").append(data1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(data2.get(key)).append("\n");
            }
        });

        result.append("}");

        return result.toString();
    }

}
