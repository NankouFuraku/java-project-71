package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "firstFilePath", description = "path to first file.")
    private String firstFilePath;
    @Parameters(index = "1", paramLabel = "secondFilePath", description = "path to second file.")
    private String secondFilePath;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish].")
    private String format;


    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(firstFilePath, secondFilePath);
        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}