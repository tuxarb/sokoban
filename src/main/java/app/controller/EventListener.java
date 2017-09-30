package app.controller;

import app.model.Direction;
import app.model.GameObjects;

public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);
    GameObjects getGameObjects();
    int getCurrentLevel();
}
