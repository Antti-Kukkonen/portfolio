public class TextFieldA extends UIElement {
    @Override
    void display() {
        System.out.println(
                "# " + text
        );
    }

    public TextFieldA(String text) {
        super(text);
    }
}
