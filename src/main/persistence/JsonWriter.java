package persistence;

import java.io.*;

// Code taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// The abstract JsonWriter class
public abstract class JsonWriter {
    protected static final int TAB = 4;
    protected PrintWriter writer;
    protected String destination;


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter((destination));
    }

    
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    protected void saveToFile(String json) {
        writer.print(json);
    }

}
