package sk.uniza.fri.item;

public enum Effect {
    BUFF_ATTACK(),
    BUFF_DEFENSE(),
    HEAL(),
    POISON(),
    BURN(),
    PARALYZE(),
    SLEEP(),
    FREEZE(),
    SHOCK(),
    WET(),
    SLEEPY();

    // TODO placeholder
    public int getAmount() {
        return 10;
    }
}
