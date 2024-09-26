public class TextFieldB extends UIElement {
    @Override
    void display() {
        System.out.println(
                "@ " + text
        );
    }

    public TextFieldB(String text) {
        super(text);
    }
}
