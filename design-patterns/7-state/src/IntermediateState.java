public class IntermediateState extends CharacterState {

    public IntermediateState(Character character) {
        super(character);
    }

    @Override
    public void meditate() {
        character.hp += 10;
        character.hp = Math.min(character.hp, 100);
    }

    @Override
    public void fight() {

    }

    @Override
    public void update() {
        if (character.xp >= 100) {
            character.xp = 0;
            character.level = "Expert";
            character.setState(new ExpertState(character));
        }
    }

    public void printOptions() {
        System.out.println("train");
        System.out.println("meditate");
        System.out.println("exit");
    }
}
