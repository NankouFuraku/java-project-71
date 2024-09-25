import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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

    @Test
    void testGenerate() throws IOException {
        var filePath1 = "src/test/resources/fixtures/file1.json";
        var filePath2 = "src/test/resources/fixtures/file2.json";
        var actual = Differ.generate(filePath1, filePath2);
        assertEquals(expected, actual);
    }

}
