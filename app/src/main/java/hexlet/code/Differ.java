package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        var data1 = getData(filePath1);
        var data2 = getData(filePath2);

        var dataType = getType(filePath1);
        var dataType2 = getType(filePath2);

        var parsedData1 = Parser.parseData(data1, dataType);
        var parsedData2 = Parser.parseData(data2, dataType2);

        var diff = DiffGenerator.generateDiff(parsedData1, parsedData2);

        return Formatter.reformat(diff, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String getData(String filePath) throws IOException {
        var path = Paths.get(filePath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    private static String getType(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

}
