

public class Main {
    public static void main(String[] args) {
        UIFactory ui = new BFactory();
        UIElement textField = ui.createTextField("Hello!");
        textField.display();
        textField.setText("World!");
        textField.display();
        ui.createButton("Press here!").display();
        ui.createCheckBox("Check here!").display();

    }
}