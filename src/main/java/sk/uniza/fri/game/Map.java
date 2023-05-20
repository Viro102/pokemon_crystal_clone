package sk.uniza.fri.game;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import sk.uniza.fri.character.Player;
import sk.uniza.fri.pokemon.FirePokemon;
import sk.uniza.fri.pokemon.Pokemon;

public class Map {
    private final TiledMap tiledMap;
    private final String mapName;
    private final Array<RectangleMapObject> collisionObjects;
    private final Array<RectangleMapObject> exitHitboxes;
    private final Array<PolygonMapObject> pokemonSpawnAreas;
    private final Array<Pokemon> pokemons;

    public Map(String mapName, Player player) {
        this.mapName = mapName;
        this.tiledMap = new TmxMapLoader().load("tmx/" + this.mapName + ".tmx");
        this.pokemons = new Array<>();

        MapLayer collisionLayer = this.tiledMap.getLayers().get("Collision");
        this.collisionObjects = collisionLayer.getObjects().getByType(RectangleMapObject.class);

        MapLayer exits = this.tiledMap.getLayers().get("Exits");
        this.exitHitboxes = exits.getObjects().getByType(RectangleMapObject.class);

        if (this.tiledMap.getLayers().get("Pokemons") != null) {
            MapLayer pokemonLayer = this.tiledMap.getLayers().get("Pokemons");
            this.pokemonSpawnAreas = pokemonLayer.getObjects().getByType(PolygonMapObject.class);
            this.generatePokemons(player);
        } else {
            this.pokemonSpawnAreas = new Array<>();
        }
    }

    private void generatePokemons(Player player) {
        for (PolygonMapObject area : this.pokemonSpawnAreas) {
            Polygon p = area.getPolygon();
            Rectangle r = p.getBoundingRectangle();
            for (int i = 0; i < 10; i++) {
                float x = MathUtils.random(r.getX(), r.getX() + r.getWidth());
                float y = MathUtils.random(r.getY(), r.getY() + r.getHeight());
                if (p.contains(x, y)) {
                    int[] stats = this.generatePokemonStats(player);
                    FirePokemon pokemon = new FirePokemon("charmander", stats[0], stats[1], stats[2], stats[3], stats[4]);
                    pokemon.setPosition(x, y);
                    this.pokemons.add(pokemon);
                }
            }
        }
    }

    private int[] generatePokemonStats(Player player) {
        int[] pokemonStats = new int[5];
        int playerPartyPower = player.getPartyStrength();

        // first position is for level
        for (int i = 1; i < pokemonStats.length; i++) {
            pokemonStats[i] = Math.abs(MathUtils.random(playerPartyPower - 2, playerPartyPower + 2));
        }
        pokemonStats[0] = Math.abs(MathUtils.random(playerPartyPower / 4 - 1, playerPartyPower / 4 + 1));

        return pokemonStats;
    }

    public Array<PolygonMapObject> getPokemonSpawnAreas() {
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
