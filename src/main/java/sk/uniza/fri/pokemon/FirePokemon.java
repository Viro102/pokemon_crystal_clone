package sk.uniza.fri.pokemon;

public class FirePokemon extends Pokemon {
    public FirePokemon(String name, int health, int attack, int defense, int speed, String evolvesInto) {
        super(name, health, attack, defense, speed, evolvesInto);
        this.addSkill(new AttackMove("fireball", "FireSkillDesc", 0));
    }
}
