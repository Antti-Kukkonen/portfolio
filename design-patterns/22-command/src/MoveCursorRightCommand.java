public class MoveCursorRightCommand extends Command {
    public MoveCursorRightCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.x++;
        controller.x = Math.min(controller.x, controller.MAX_X);
    }
}
