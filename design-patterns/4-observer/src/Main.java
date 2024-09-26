public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation1 = new WeatherStation();
        WeatherStation weatherStation2 = new WeatherStation();
        weatherStation1.start();
        weatherStation2.start();

        WeatherObserver observer1 = new WeatherObserver("Observer 1");
        WeatherObserver observer2 = new WeatherObserver("OBS2");
        WeatherObserver observer3 = new WeatherObserver("Obby 3");

        weatherStation1.addObserver(observer1);
        weatherStation1.addObserver(observer2);
        weatherStation2.addObserver(observer3);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted.");
        }

        weatherStation1.removeObserver(observer1);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Simulation interrupted.");
        }

        weatherStation1.removeObserver(observer2);
        weatherStation2.removeObserver(observer3);

        System.out.println("Simulation finished.");

        weatherStation1.interrupt();
        weatherStation2.interrupt();
    }
}
