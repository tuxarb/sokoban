package app.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Player extends CollisionObject implements Movable {
    private BufferedImage image = null;

    Player(int x, int y) {
        super(x, y);
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("/images/player.png"));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void move(int x, int y) {
        this.setX(getX() + x);
        this.setY(getY() + y);
    }

    @Override
    public void draw(Graphics graphics, JPanel panel) {
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
        if (image != null) {
            graphics.setColor(new Color(0, 100, 0));
            graphics.drawImage(image, leftUpperCornerX, leftUpperCornerY, panel);
        } else {
            graphics.setColor(Color.RED);
            graphics.drawOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
            graphics.fillOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
            graphics.setColor(Color.WHITE);
            graphics.drawString("И", getX() - getWidth() / 4, getY() + 5);
        }
    }
}
