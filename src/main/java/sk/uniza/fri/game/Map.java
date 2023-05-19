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

public class Map {
    private final TiledMap tiledMap;
    private final String mapName;
    private final Array<RectangleMapObject> collisionObjects;
    private final Array<RectangleMapObject> exitHitboxes;
    private final Array<PolygonMapObject> pokemonSpawnAreas;

    public Map(String mapName) {
        this.mapName = mapName;
        this.tiledMap = new TmxMapLoader().load("tmx/" + this.mapName + ".tmx");

        MapLayer collisionLayer = this.tiledMap.getLayers().get("Collision");
        this.collisionObjects = collisionLayer.getObjects().getByType(RectangleMapObject.class);

        MapLayer exits = this.tiledMap.getLayers().get("Exits");
        this.exitHitboxes = exits.getObjects().getByType(RectangleMapObject.class);

        if (this.tiledMap.getLayers().get("Pokemons") != null) {
            MapLayer pokemonLayer = this.tiledMap.getLayers().get("Pokemons");
            this.pokemonSpawnAreas = pokemonLayer.getObjects().getByType(PolygonMapObject.class);
        } else {
            this.pokemonSpawnAreas = new Array<>();
        }
    }

    public void generatePokemons() {
        for (PolygonMapObject area : this.pokemonSpawnAreas) {
            Polygon p = area.getPolygon();
            Rectangle r = p.getBoundingRectangle();
            float x = MathUtils.random(r.getX(), r.getX() + r.getWidth());
            float y = MathUtils.random(r.getY(), r.getY() + r.getHeight());
            if (p.contains(x, y)) {
//                WaterPokemon pokemon = new WaterPokemon();
            }
        }
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

    public String getMapName() {
        return this.mapName;
    }

    public TiledMap getTiledMap() {
        return this.tiledMap;
    }
}
