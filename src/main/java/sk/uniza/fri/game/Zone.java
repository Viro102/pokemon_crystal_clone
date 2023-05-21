package sk.uniza.fri.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.Pokedex;
import sk.uniza.fri.pokemon.Pokemon;

import java.util.*;

public class Zone {
    private final TiledMap tiledMap;
    private final String mapName;
    private final ArrayList<RectangleMapObject> collisionObjects;
    private final ArrayList<RectangleMapObject> exitHitboxes;
    private final ArrayList<RectangleMapObject> pokemonSpawnAreas;
    private final ArrayList<Pokemon> pokemons;

    public Zone(String mapName, Player player, Pokedex pokedex) {
        this.mapName = mapName;
        this.tiledMap = new TmxMapLoader().load("tmx/" + this.mapName + ".tmx");
        this.pokemons = new ArrayList<>();
        this.collisionObjects = new ArrayList<>();
        this.exitHitboxes = new ArrayList<>();
        this.pokemonSpawnAreas = new ArrayList<>();

        MapLayer collisionLayer = this.tiledMap.getLayers().get("Collision");
        for (MapObject object : collisionLayer.getObjects()) {
            this.collisionObjects.add((RectangleMapObject) object);
        }
//        this.collisionObjects = collisionLayer.getObjects().getByType(RectangleMapObject.class);

        MapLayer exits = this.tiledMap.getLayers().get("Exits");
        for (MapObject object : exits.getObjects()) {
            this.exitHitboxes.add((RectangleMapObject) object);
        }
//        this.exitHitboxes = exits.getObjects().getByType(RectangleMapObject.class);

        if (this.tiledMap.getLayers().get("Pokemons") != null) {
            MapLayer pokemonLayer = this.tiledMap.getLayers().get("Pokemons");
            for (MapObject object : pokemonLayer.getObjects()) {
                this.pokemonSpawnAreas.add((RectangleMapObject) object);
            }
//            this.pokemonSpawnAreas = pokemonLayer.getObjects().getByType(RectangleMapObject.class);
            this.spawnPokemons(player, pokedex);
        }
    }

    private void spawnPokemons(Player player, Pokedex pokedex) {
        Array<Vector2> spawnPoints = new Array<>();

        for (RectangleMapObject area : this.pokemonSpawnAreas) {
            Rectangle rectangle = area.getRectangle();

            for (int i = 0; i < 10; i++) {
                float x = MathUtils.random(rectangle.getX(), rectangle.getX() + rectangle.getWidth());
                float y = MathUtils.random(rectangle.getY(), rectangle.getY() + rectangle.getHeight());

                Pokemon pokemon = this.randomizePokemonStats(player, pokedex);
                x += pokemon.getWidth() / 2;
                y += pokemon.getHeight() / 2;

                Vector2 spawnPoint = new Vector2(x, y);

                if (this.isFarEnough(spawnPoint, spawnPoints)) {
                    pokemon.setPosition(x, y);
                    spawnPoints.add(spawnPoint);
                    this.pokemons.add(pokemon);
                }
            }
        }
    }

    private boolean isFarEnough(Vector2 spawnPoint, Array<Vector2> spawnPoints) {
        for (Vector2 point : spawnPoints) {
            if (spawnPoint.dst(point) < 120) {
                return false;
            }
        }
        return true;
    }


    private Pokemon randomizePokemonStats(Player player, Pokedex pokedex) {
        int playerPartyPower = player.getPartyStrength();
        Pokemon pokemon = pokedex.getRandomPokemon();
        int[] pokemonStats = pokemon.getStats();


        // first stat is always level
        for (int i = 0; i < pokemonStats.length; i++) {
            if (i == 0) {
                pokemonStats[i] = MathUtils.random(player.getMaxLvlOfParty() - 2, player.getMaxLvlOfParty() + 2);
                continue;
            }
            pokemonStats[i] += MathUtils.random(playerPartyPower / 5 - 2, playerPartyPower / 5 + 2);
        }
        pokemon.setStats(pokemonStats);

        return pokemon;
    }

    public List<RectangleMapObject> getPokemonSpawnAreas() {
        return new ArrayList<>(this.pokemonSpawnAreas);
    }

    public List<RectangleMapObject> getCollisionObjects() {
        return new ArrayList<>(this.collisionObjects);
    }

    public List<RectangleMapObject> getExitHitboxes() {
        return new ArrayList<>(this.exitHitboxes);
    }

    public Map<Pokemon, Rectangle> getPokemons() {
        HashMap<Pokemon, Rectangle> pokemonHitbox = new HashMap<>();

        for (Pokemon pokemon : this.pokemons) {
            pokemonHitbox.put(pokemon, new Rectangle(pokemon.getX(), pokemon.getY(), pokemon.getWidth(), pokemon.getHeight()));
        }

        return Collections.unmodifiableMap(pokemonHitbox);
    }

    public String getMapName() {
        return this.mapName;
    }

    public TiledMap getTiledMap() {
        return this.tiledMap;
    }
}
