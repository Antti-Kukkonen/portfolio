import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Controller {
    private final Model model;
    private final Gui gui;
    private final Stack<IMemento> undoStack;
    private final Stack<IMemento> redoStack;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void setOption(int optionNumber, int choice) {
        saveToHistory();
        model.setOption(optionNumber, choice);
        redoStack.clear();
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveToHistory();
        model.setIsSelected(isSelected);
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            System.out.println("Memento found in undo stack");
            redoStack.push(model.createMemento());
            IMemento previousState = undoStack.pop();
            model.restoreState(previousState);
            gui.updateGui();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            System.out.println("Memento found in redo stack");
            undoStack.push(model.createMemento());
            IMemento previousState = redoStack.pop();
            model.restoreState(previousState);
            gui.updateGui();
        }
    }

    private void saveToHistory() {
        IMemento currentState = model.createMemento();
        undoStack.push(currentState);
        // Limit the size of the undo stack to 20
        if (undoStack.size() > 20) {
            undoStack.remove(0);
            System.out.println("Dropped oldest undo memento");
        }
    }

    public Stack<String> getHistory() {
        Stack<String> history = new Stack<>();
        for (IMemento memento : undoStack) {
            history.push( memento.getTimestamp());
        }
        for (IMemento memento : redoStack) {
            history.push(memento.getTimestamp());
        }
        return history;
    }

    public void restoreState(int index) {
        saveToHistory();
        List<IMemento> history = new ArrayList<>();
        history.addAll(undoStack);
        history.addAll(redoStack);

        if (index >= 0 && index < history.size()) {
            IMemento selectedMemento = history.get(index);
            model.restoreState(selectedMemento);
        }

        boolean isUndo = index < undoStack.size();
        int inverseIndex = history.size() - index - 1;
        if (isUndo) {
            for (int i = 0; i < inverseIndex; i++) {
                redoStack.push(undoStack.pop());
            }
        } else {
            for (int i = 0; i < inverseIndex; i++) {
                undoStack.push(redoStack.pop());
            }
        }

        gui.updateGui();
    }
}
