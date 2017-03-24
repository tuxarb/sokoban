package app.view;

import app.controller.Controller;
import app.controller.EventListener;
import app.model.GameObjects;

import javax.swing.*;
import java.net.URL;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
        setResizable(false);
        URL iconURL = getClass().getResource("/images/icon.jpg");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }
        setVisible(true);
    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public void update() {
        field.repaint();
    }

    public void completed(int level) {
        update();
        JOptionPane.showMessageDialog(null, level + " level is passed!", "Success", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }

    GameObjects getGameObjects() {
        return controller.getGameObjects();
    }
}
