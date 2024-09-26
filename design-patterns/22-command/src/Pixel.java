public class Pixel {
    private boolean isOn;

    public void toggle() {
        this.isOn = !this.isOn;
    }

    public boolean isOn() {
        return isOn;
    }
}
