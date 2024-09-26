public class MoveCursorUpCommand extends Command {
    public MoveCursorUpCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.y--;
        controller.y = Math.max(controller.y, controller.MIN_Y);
    }
}
