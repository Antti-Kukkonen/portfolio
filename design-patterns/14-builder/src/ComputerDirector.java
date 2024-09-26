public class ComputerDirector {
    private final ComputerBuilder computerBuilder;
    public ComputerDirector(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public Computer buildComputer() {
        computerBuilder.buildProcessor();
        computerBuilder.buildRAM();
        computerBuilder.buildHardDrive();
        computerBuilder.buildGraphicsCard();
        computerBuilder.buildOperatingSystem();
        return computerBuilder.getComputer();
    }
}
