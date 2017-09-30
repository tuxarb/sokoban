package app.view;

import app.controller.EventListener;
import app.model.Direction;
import app.model.GameObject;
import app.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    Field(View view) {
        this.view = view;
        addKeyListener(new KeyHandler());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        GradientPaint grad = new GradientPaint(
                getX(), getY(), new Color(0, 0, 0),
                getWidth() - getX(), getHeight() - getY(), new Color(80, 80, 80)
        );
        ((Graphics2D) g).setPaint(grad);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        GameObjects gameObjects = view.getGameObjects();
        Set<GameObject> set = gameObjects.getAll();

        for (GameObject object : set) {
            object.draw(g, this);
        }
        g.setColor(Color.lightGray);
        g.setFont(new Font("Copperplate", 2, 24));
        g.drawString("Level: " + view.getCurrentLevel(), getWidth() / 2 - 50, getHeight() - 5);

    }

    void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    int result = JOptionPane.showConfirmDialog(view, "Are you sure?", "Restarting...", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        eventListener.restart();
                    }
                    break;
            }
        }
    }
}
