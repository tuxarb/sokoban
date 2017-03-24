package app.controller;

import app.model.Direction;
import app.model.GameObjects;
import app.model.Model;
import app.view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    private Controller() {
        model = new Model();
        model.restart();
        model.setEventListener(this);
        view = new View(this);
        view.init();
        view.setEventListener(this);
    }

    public static void main(String[] args) {
        new Controller();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
