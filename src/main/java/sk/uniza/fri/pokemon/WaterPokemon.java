package sk.uniza.fri.pokemon;

public class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int health, int attack, int defense, int speed) {
        super(name, defense, speed, attack, health);
        this.addSkill(new AttackSkill("Splash", "WaterSkillDesc", 0));
    }
}
