public class ExpertState extends CharacterState {
    public ExpertState(Character character) {
        super(character);
    }

    @Override
    public void meditate() {
        character.hp += 20;
        character.hp = Math.min(character.hp, 100);
    }

    @Override
    public void fight() {
        if (character.hp <= 10) {
            System.out.println("You do not have enough health points to fight!");
            return;
        }
        character.hp -= 10;
        character.xp += 40;
    }

    @Override
    public void update() {
        if (character.xp >= 200) {
            character.xp = 0;
            character.level = "Master";
            System.out.println("You are now a master!");
        }
    }

    public void printOptions() {
        System.out.println("train");
        System.out.println("meditate");
        System.out.println("fight");
        System.out.println("exit");
    }
}
