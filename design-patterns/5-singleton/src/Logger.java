import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static Logger instance;
    private String fileName;
    private PrintWriter writer;

    private Logger() {
        this.fileName = "log.txt";
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        close();
    }

    private void initWriter() {
        if (writer == null) {
            try {
                writer = new PrintWriter(new FileWriter(fileName, true), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String message) {
        initWriter();
        writer.println(message);
    }

    public void close() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}