package sk.uniza.fri.item;

public enum Effect {
    BUFF_ATTACK(),
    BUFF_DEFENSE(),
    HEAL();

    // TODO placeholder
    public int getAmount() {
        return 10;
    }
}
