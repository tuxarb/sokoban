package app.model;

import app.controller.EventListener;

import java.io.InputStream;

public class Model {
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader;
    static int FIELD_SELL_SIZE = 20;

    public Model() {
        levelLoader = new LevelLoader();
    }

    private void restartLevel(int level) {
        InputStream in = getClass().getResourceAsStream("/levels.txt");
        levelLoader.setInputStream(in);
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void setStartLevel(int level) {
        currentLevel = level;
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction))
            return;
        if (checkBoxCollision(direction))
            return;
        switch (direction) {
            case RIGHT:
                player.move(Model.FIELD_SELL_SIZE, 0);
                break;
            case LEFT:
                player.move(-Model.FIELD_SELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -Model.FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, Model.FIELD_SELL_SIZE);
                break;
        }
        checkCompletion();
    }

    private boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction))
                return true;
        }
        return false;
    }

    private boolean checkBoxCollision(Direction direction) {
        Player player = gameObjects.getPlayer();
        GameObject objectStop = null;

        for (GameObject gameObject : gameObjects.getAll()) {
            if (!(gameObject instanceof Player) && !(gameObject instanceof Home) &&
                    player.isCollision(gameObject, direction)) {
                objectStop = gameObject;
                break;
            }
        }
        if (objectStop == null)
            return false;

        if (objectStop instanceof Box) {
            Box firstBox = (Box) objectStop;
            if (checkWallCollision(firstBox, direction))
                return true;

            for (Box box : gameObjects.getBoxes()) {
                if (box == firstBox) continue;
                if (firstBox.isCollision(box, direction))
                    return true;
            }
            switch (direction) {
                case LEFT:
                    firstBox.move(-Model.FIELD_SELL_SIZE, 0);
                    break;
                case RIGHT:
                    firstBox.move(Model.FIELD_SELL_SIZE, 0);
                    break;
                case DOWN:
                    firstBox.move(0, Model.FIELD_SELL_SIZE);
                    break;
                case UP:
                    firstBox.move(0, -Model.FIELD_SELL_SIZE);
                    break;
            }
        }
        return false;
    }

    private void checkCompletion() {
        boolean flag = true;
        for (Home home : gameObjects.getHomes()) {
            boolean equals = false;
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    equals = true;
                    break;
                }
            }
            if (!equals)
                flag = false;
        }
        if (flag)
            eventListener.levelCompleted(currentLevel);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }
}
