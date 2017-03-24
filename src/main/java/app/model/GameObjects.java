package app.model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    private Set<Home> homes;
    private Set<Wall> walls;
    private Player player;
    private Set<Box> boxes;

    Set<Home> getHomes() {
        return homes;
    }

    Set<Wall> getWalls() {
        return walls;
    }

    Player getPlayer() {
        return player;
    }

    Set<Box> getBoxes() {
        return boxes;
    }

    GameObjects(Set<Wall> walls, Set<Box> boxes, Set<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    public Set<GameObject> getAll() {
        Set<GameObject> allObjects = new HashSet<>();
        allObjects.addAll(walls);
        allObjects.addAll(boxes);
        allObjects.addAll(homes);
        allObjects.add(player);
        return allObjects;
    }
}
