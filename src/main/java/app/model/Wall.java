package app.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


class Wall extends CollisionObject {
    private BufferedImage image = null;

    Wall(int x, int y) {
        super(x, y);
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("/images/wall.jpg"));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void draw(Graphics graphics, JPanel panel) {
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;
        if (image != null) {
            graphics.drawImage(image, leftUpperCornerX, leftUpperCornerY, panel);
        } else {
            graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
            graphics.setColor(Color.GRAY);
            graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        }
    }
}
