package sk.uniza.fri.pokemon;

public class FirePokemon extends Pokemon {
    public FirePokemon(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed);
        this.addSkill(new AttackSkill("fireball", "FireSkillDesc", 0));
    }

    public FirePokemon(String name, int level, int health, int attack, int defense, int speed) {
        super(name, level, health, attack, defense, speed);
    }
}
