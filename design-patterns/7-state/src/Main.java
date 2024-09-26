import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Character hero = new Character();
        Scanner scanner = new Scanner(System.in);
        while (!hero.level.equals("Master")) {
            hero.printStats();
            System.out.println("What would you like to do?");
            hero.printOptions();
            String command = scanner.nextLine();
            switch (command) {
                case "train":
                    hero.train();
                    break;
                case "meditate":
                    hero.meditate();
                    break;
                case "fight":
                    hero.fight();
                    break;
                case "exit":
                    return;
            }
            hero.update();

        }
    }
}
