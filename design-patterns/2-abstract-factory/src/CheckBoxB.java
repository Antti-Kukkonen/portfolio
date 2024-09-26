public class CheckBoxB extends UIElement {
    @Override
    void display() {
        System.out.println(
                "{ } " + text
        );
    }

    public CheckBoxB(String text) {
        super(text);
    }
}
