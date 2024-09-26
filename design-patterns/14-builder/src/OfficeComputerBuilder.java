public class OfficeComputerBuilder implements ComputerBuilder {
    private final Computer computer = new Computer();

    @Override
    public void buildProcessor() {
        computer.addComponent(new Component("Intel Core i5-9400F"));
    }

    @Override
    public void buildRAM() {
        computer.addComponent(new Component("Crucial Ballistix 8GB"));
    }

    @Override
    public void buildHardDrive() {
        computer.addComponent(new Component("Samsung 860 EVO 500GB"));
    }

    @Override
    public void buildGraphicsCard() {
        computer.addComponent(new Component("Integrated Intel UHD Graphics 630"));
    }

    @Override
    public void buildOperatingSystem() {
        computer.addComponent(new Component("Windows 10 Pro"));
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
