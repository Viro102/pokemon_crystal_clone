package sk.uniza.fri.game;

import com.badlogic.gdx.math.MathUtils;
import sk.uniza.fri.ability.Ability;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.item.Item;
import sk.uniza.fri.item.Pokeball;
import sk.uniza.fri.pokemon.Pokemon;

public class BattleController {
    private final Player player;
    private final Pokemon enemyPokemon;
    private Pokemon selectedPokemon;
    private int turnCounter;
    private int currentPlayerPartySize;

    public BattleController(Player player, Pokemon enemyPokemon) {
        this.player = player;
        this.enemyPokemon = enemyPokemon;
        this.currentPlayerPartySize = this.player.getPartySize();
        this.selectedPokemon = player.getFirstPokemon();
    }

    public void nextTurn() {
        this.enemyTurn();
        this.turnCounter++;
    }

    private void enemyTurn() {
        int size = this.enemyPokemon.getNumOfAbilities();
        Ability ability = this.enemyPokemon.getAbility(MathUtils.random(0, size - 1));
        this.attack(ability, this.enemyPokemon, this.selectedPokemon);
    }

    public void attack(Ability ability) {
        this.attack(ability, this.selectedPokemon, this.enemyPokemon);
    }

    public void attack(Ability ability, Pokemon source, Pokemon target) {
        if (!ability.isUnlocked()) {
            System.out.println("Ability is not unlocked!");
            return;
        }
        switch (ability.getEffect()) {
            case NONE: {
                double effectiveness = source.getEffectiveness(target);
                target.decreaseHP((int) (ability.getAmount() * effectiveness));
                break;
            }
            case HEAL: {
                source.increaseHP(ability.getAmount());
                break;
            }
            case BUFF_ATTACK: {
                source.increaseATT(ability.getAmount());
                break;
            }
            case BUFF_DEFENSE: {
                source.increaseDEF(ability.getAmount());
                break;
            }
            case BUFF_SPEED: {
                source.increaseSPD(ability.getAmount());
                break;
            }
            default: {
                this.enemyPokemon.addStatusEffect(ability.getEffect());
                break;
            }
        }
    }

    public void switchPokemon(Pokemon newPokemon) {
        this.selectedPokemon = newPokemon;
    }

    public boolean checkBattleEnd() {
        if (this.enemyPokemon.hasFainted()) {
            this.selectedPokemon.gainExp(this.enemyPokemon.getLevel() * 5);
            this.player.gainGold(this.enemyPokemon.getLevel() * 5);

            Item item = this.player.getItemFromInventory("Pokeball");
            if (item instanceof Pokeball && !this.enemyPokemon.isCollected()) {
                Pokeball pokeball = (Pokeball) item;
                if (pokeball.attemptToCatch(this.enemyPokemon)) {
                    this.player.collectPokemon(this.enemyPokemon);
                }
            }

            return true;
        }

        if (this.selectedPokemon.hasFainted()) {
            this.currentPlayerPartySize--;
            if (this.currentPlayerPartySize > 0) {
                this.selectedPokemon = this.player.getFirstPokemon();
            } else {
                this.selectedPokemon.setFainted(false);
                this.selectedPokemon.heal();
                return true;
            }
        }
        return false;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Pokemon getEnemyPokemon() {
        return this.enemyPokemon;
    }

    public Pokemon getSelectedPokemon() {
        return this.selectedPokemon;
    }
}