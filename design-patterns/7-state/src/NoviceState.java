public class NoviceState extends CharacterState {
    public NoviceState(Character character) {
        super(character);
    }

    @Override
    public void meditate() {

    }

    @Override
    public void fight() {

    }

    @Override
    public void update() {
        if (character.xp >= 100) {
            character.xp = 0;
            character.level = "Intermediate";
            character.setState(new IntermediateState(character));
        }
    }

    public void printOptions() {
        System.out.println("train");
        System.out.println("exit");
    }
}
