public class Character {
    CharacterState state;
    String name = "Hero";
    int xp = 0;
    int hp = 80;
    String level = "Novice";

    public Character() {
        state = new NoviceState(this);
    }

    public void setState(CharacterState state) {
        this.state = state;
    }

    public void update() {
        state.update();
    }

    public void train() {
        state.train();
    }

    public void meditate() {
        state.meditate();
    }

    public void fight() {
        state.fight();
    }

    public void printStats() {
        System.out.println(name + " - " + level);
        System.out.println("XP: " + xp);
        System.out.println("HP: " + hp);
    }

    public void printOptions() {
        state.printOptions();
    }
}
