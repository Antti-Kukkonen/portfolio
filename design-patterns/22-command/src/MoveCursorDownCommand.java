public class MoveCursorDownCommand extends Command {
    public MoveCursorDownCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.y++;
        controller.y = Math.min(controller.y, controller.MAX_Y);
    }
}
