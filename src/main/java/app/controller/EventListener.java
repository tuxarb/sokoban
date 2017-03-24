package app.controller;

import app.model.Direction;

public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);
}
