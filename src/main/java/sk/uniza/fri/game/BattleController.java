package sk.uniza.fri.game;

import com.badlogic.gdx.math.MathUtils;
import sk.uniza.fri.ability.Ability;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.Pokemon;

public class BattleController {
    private final Player player;
    private final Pokemon enemyPokemon;
    private Pokemon selectedPokemon;
    private boolean isPlayerTurn;

    public BattleController(Player player, Pokemon enemyPokemon) {
        this.player = player;
        this.enemyPokemon = enemyPokemon;
        this.selectedPokemon = player.getFirstPokemon();
        this.isPlayerTurn = true;
    }

    public void nextTurn() {
        this.isPlayerTurn = !this.isPlayerTurn;
        if (!this.isPlayerTurn) {
            this.enemyTurn();
        }
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
            return true;
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