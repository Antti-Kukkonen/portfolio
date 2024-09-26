public class ButtonA extends UIElement {
    @Override
    void display() {
        System.out.println(
                "+" + "=============" + "+" + "\n" +
                "| " + text + " |" + "\n" +
                "+" + "=============" + "+"
        );
    }

    public ButtonA(String text) {
        super(text);
    }
}
