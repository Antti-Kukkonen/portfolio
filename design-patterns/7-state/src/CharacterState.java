public abstract class CharacterState {
    Character character;

    public CharacterState(Character character) {
        this.character = character;
    }

    public void train() {
        character.xp += 10;
    }
    public abstract void meditate();
    public abstract void fight();

    public abstract void update();

    public abstract void printOptions();
}
