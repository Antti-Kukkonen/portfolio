public class TogglePixelCommand extends Command {
    public TogglePixelCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.togglePixel(controller.x, controller.y);
    }
}
