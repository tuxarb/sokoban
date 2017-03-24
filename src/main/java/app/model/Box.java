package app.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Box extends CollisionObject implements Movable {
    private BufferedImage image = null;

    Box(int x, int y) {
        super(x, y);
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("/images/box.jpg"));
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
            graphics.drawImage(image, leftUpperCornerX, leftUpperCornerY, panel);
        } else {
            graphics.setColor(Color.ORANGE);
            graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
            graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
            graphics.setColor(Color.WHITE);
            graphics.drawString("Я", getX() - getWidth() / 3, getY() + getHeight() / 3);
        }
    }
}
