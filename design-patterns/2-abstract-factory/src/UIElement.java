public abstract class UIElement {
    String text = "";

    abstract void display();

    void setText(String text) {
        this.text = text;
    }
    UIElement(String text) {
        this.text = text;
    }
    UIElement() {}
}
