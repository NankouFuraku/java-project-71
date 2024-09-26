import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static String expected;
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expected = readFixture("resultJson.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerate(String type) throws Exception {
        var filePath1 = getFixturePath("file1." + type).toString();
        var filePath2 = getFixturePath("file2." + type).toString();
        var actual = Differ.generate(filePath1, filePath2);
        assertEquals(expected, actual);
    }

}
