abstract class Command {
    protected final Controller controller;


    abstract void execute();

    public Command(Controller controller) {
        this.controller = controller;
    }
}
