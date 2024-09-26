public class Main {
    public static void main(String[] args) {
        Printer printer = new BasicPrinter();
        printer.print("Hello, console!");
        printer = new EncryptedPrinter(printer);
        printer.print("Hello, encryption!");

        printer = new EncryptedPrinter(new FilePrinter(new BasicPrinter()));
        printer.print("Hello, encrypted file!");
    }
}
