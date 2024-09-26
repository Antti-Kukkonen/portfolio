public class MoveCursorLeftCommand extends Command {
    public MoveCursorLeftCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.x--;
        controller.x = Math.max(controller.x, controller.MIN_X);
    }
}
