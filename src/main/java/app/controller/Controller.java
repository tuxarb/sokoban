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
        model.setEventListener(this);
        Integer startLevel = getStartLevel();
        if (startLevel != null) {
            model.setStartLevel(startLevel);
        }
        model.restart();
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

    @Override
    public int getCurrentLevel() {
        return model.getCurrentLevel();
    }

    @Override
    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }

    private Integer getStartLevel() {
        String startLevel = System.getProperty("startLevel");
        if (startLevel == null)
            return null;
        int level;
        try {
            level = Integer.parseInt(startLevel);
        } catch (NumberFormatException e) {
            return null;
        }
        return (level > 0 && level <= 60) ? level : null;
    }
}
