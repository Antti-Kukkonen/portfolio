public class CheckBoxA extends UIElement {
    @Override
    void display() {
        System.out.println(
                "[ ] " + text
        );
    }

    public CheckBoxA(String text) {
        super(text);
    }
}
