package sk.uniza.fri.item;

import com.badlogic.gdx.math.MathUtils;
import sk.uniza.fri.ability.Ability;

public enum Effect {
    BUFF_ATTACK(),
    BUFF_DEFENSE(),
    BUFF_SPEED(),
    HEAL(),
    POISON(),
    BURN(),
    FREEZE(),
    SHOCK(),
    WET(),
    ENTANGLE(),
    NONE();

    public int getAmount(Ability ability) {
        return (int) (ability.getPower() + MathUtils.random(-0.5f, 0.5f) * ability.getPower() / 2);
    }

    public int getDuration(Ability ability) {
        return ability.getDuration();
    }

    public int getAmount(Potion potion) {
        return potion.getAmount();
    }

    public int getDuration(Potion potion) {
        return potion.getDuration();
    }
}
