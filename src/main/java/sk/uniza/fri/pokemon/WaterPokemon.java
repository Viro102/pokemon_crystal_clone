package sk.uniza.fri.pokemon;

public class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int health, int attack, int defense, int speed) {
        super(name, health, attack, defense, speed);
        this.addSkill(new AttackSkill("Splash", "WaterSkillDesc", 0));
        this.addSkill(new AttackSkill("Water Gun", "WaterSkillDesc", 1));
        this.addSkill(new AttackSkill("Hydro Pump", "WaterSkillDesc", 2));
    }
}
