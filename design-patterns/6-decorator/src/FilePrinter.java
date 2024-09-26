import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter extends PrinterDecorator {
    public FilePrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        try {
            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.print(message);
    }
}
