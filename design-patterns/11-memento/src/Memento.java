import java.util.Arrays;

public class Memento implements IMemento {
    private int[] options;
    private boolean isSelected;
    private long timestamp;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone(); // Copy options array
        this.isSelected = isSelected;
        setTimestamp(System.currentTimeMillis());
        System.out.println("Memento created");
    }

    public int[] getOptions() {
        return options.clone(); // Return a copy of options array
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public String getTimestamp() {
        // convert timestamp to a date string
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp))  + "\n" + "Options: " + Arrays.toString(options) + "\n" + "Is selected: " + isSelected();
    }

    @Override
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
