import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherStation extends Thread {
    private double temperature;
    private final List<Observer> observers = new ArrayList<>();

    private final int MAX_TEMP = 40;
    private final int MIN_TEMP = -20;
    private final Random random = new Random();

    public WeatherStation() {
        temperature = MIN_TEMP + random.nextDouble() * (MAX_TEMP - MIN_TEMP);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }

    public void run() {
        while (!isInterrupted()) {
            temperature += random.nextDouble() * 2 - 1;
            temperature = Math.clamp(temperature, MIN_TEMP, MAX_TEMP);
            notifyObservers();
            try {
                Thread.sleep(random.nextInt(4000) + 1000);
            } catch (InterruptedException e) {
                System.out.println("Weather station interrupted.");
            }
        }
    }
}
