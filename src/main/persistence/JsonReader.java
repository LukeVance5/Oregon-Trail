package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Code taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public abstract class JsonReader {
    protected String source;

    public JsonReader(String source) {

        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    protected String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(str -> contentBuilder.append(str));
        }

        return contentBuilder.toString();
    }
}
