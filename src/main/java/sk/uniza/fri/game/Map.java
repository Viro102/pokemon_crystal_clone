package sk.uniza.fri.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.Pokedex;
import sk.uniza.fri.pokemon.Pokemon;

public class Map {
    private final TiledMap tiledMap;
    private final String mapName;
    private final Array<RectangleMapObject> collisionObjects;
    private final Array<RectangleMapObject> exitHitboxes;
    private final Array<RectangleMapObject> pokemonSpawnAreas;
    private final Array<Pokemon> pokemons;

    public Map(String mapName, Player player, Pokedex pokedex) {
        this.mapName = mapName;
        this.tiledMap = new TmxMapLoader().load("tmx/" + this.mapName + ".tmx");
        this.pokemons = new Array<>();

        MapLayer collisionLayer = this.tiledMap.getLayers().get("Collision");
        this.collisionObjects = collisionLayer.getObjects().getByType(RectangleMapObject.class);

        MapLayer exits = this.tiledMap.getLayers().get("Exits");
        this.exitHitboxes = exits.getObjects().getByType(RectangleMapObject.class);

        if (this.tiledMap.getLayers().get("Pokemons") != null) {
            MapLayer pokemonLayer = this.tiledMap.getLayers().get("Pokemons");
            this.pokemonSpawnAreas = pokemonLayer.getObjects().getByType(RectangleMapObject.class);
            this.generatePokemons(player, pokedex);
        } else {
            this.pokemonSpawnAreas = new Array<>();
        }
    }

    private void generatePokemons(Player player, Pokedex pokedex) {
        for (RectangleMapObject area : this.pokemonSpawnAreas) {
            Rectangle rectangle = area.getRectangle();
            for (int i = 0; i < 10; i++) {
                float x = MathUtils.random(rectangle.getX(), rectangle.getX() + rectangle.getWidth());
                float y = MathUtils.random(rectangle.getY(), rectangle.getY() + rectangle.getHeight());
                Pokemon pokemon = this.randomizePokemonStats(player, pokedex);
                pokemon.setPosition(x, y);
                this.pokemons.add(pokemon);
            }
        }
    }

    private Pokemon randomizePokemonStats(Player player, Pokedex pokedex) {
        int[] pokemonStats = new int[5];
        int playerPartyPower = player.getPartyStrength();
        Pokemon pokemon = pokedex.getRandomPokemon();

        // first stat is always level
        for (int i = 0; i < pokemonStats.length; i++) {
            if (i == 0) {
                pokemonStats[i] = Math.abs(MathUtils.random(player.getMaxLvlOfParty() - 2, player.getMaxLvlOfParty() + 2));
                continue;
            }
            pokemonStats[i] = Math.abs(MathUtils.random(playerPartyPower - 2, playerPartyPower + 2));
        }
        pokemon.setStats(pokemonStats);

        return pokemon;
    }

    public Array<RectangleMapObject> getPokemonSpawnAreas() {
        return this.pokemonSpawnAreas;
    }

    public Array<RectangleMapObject> getCollisionObjects() {
        return this.collisionObjects;
    }

    public Array<RectangleMapObject> getExitHitboxes() {
        return this.exitHitboxes;
    }

    public Array<Pokemon> getPokemons() {
        return this.pokemons;
    }

    public String getMapName() {
        return this.mapName;
    }

    public TiledMap getTiledMap() {
        return this.tiledMap;
    }
}
