public class ButtonB extends UIElement {
    @Override
    void display() {
        System.out.println(
                "+" + "-------------" + "+" + "\n" +
                "| " + text + " |" + "\n" +
                "+" + "-------------" + "+"
        );
    }

    public ButtonB(String text) {
        super(text);
    }
}
