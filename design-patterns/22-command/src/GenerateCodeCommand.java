public class GenerateCodeCommand extends Command {
    public GenerateCodeCommand(Controller controller) {
        super(controller);
    }

    @Override
    void execute() {
        controller.generateCode();
    }
}
