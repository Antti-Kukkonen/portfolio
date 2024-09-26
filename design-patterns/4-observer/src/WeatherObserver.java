public class WeatherObserver implements Observer {
    private final String name;

    public WeatherObserver(String name) {
        this.name = name;
    }

    public void update(double temperature) {
        System.out.printf("%s Temp: %.1f°C\n", name, temperature);
    }
}
