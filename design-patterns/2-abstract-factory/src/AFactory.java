public class AFactory extends UIFactory {

    @Override
    public UIElement createButton(String text) {
        return new ButtonA(text);
    }

    @Override
    public UIElement createTextField(String text) {
        return new TextFieldA(text);
    }

    @Override
    public UIElement createCheckBox(String text) {
        return new CheckBoxA(text);
    }
}
