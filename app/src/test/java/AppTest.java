import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    private static String expectedStylish;
    private static String expectedPlain;
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
        expectedStylish = readFixture("resultStylish.txt");
        expectedPlain = readFixture("resultPlain.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yaml"})
    public void testGenerate(String type) throws Exception {
        var filePath1 = getFixturePath("file1." + type).toString();
        var filePath2 = getFixturePath("file2." + type).toString();

        var actualDefaultFormat = Differ.generate(filePath1, filePath2);
        var actualStylish = Differ.generate(filePath1, filePath2, "stylish");
        var actualPlain = Differ.generate(filePath1, filePath2, "plain");

        assertEquals(expectedStylish, actualDefaultFormat);
        assertEquals(expectedStylish, actualStylish);
        assertEquals(expectedPlain, actualPlain);
    }

}
