public class Main {
    public static void main(String[] args) {
        ComputerDirector gamingComputerDirector = new ComputerDirector(new GamingComputerBuilder());
        Computer gamingComputer = gamingComputerDirector.buildComputer();
        System.out.println(gamingComputer);

        ComputerDirector officeComputerDirector = new ComputerDirector(new OfficeComputerBuilder());
        Computer officeComputer = officeComputerDirector.buildComputer();
        System.out.println(officeComputer);
    }
}
