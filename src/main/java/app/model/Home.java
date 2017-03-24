package app.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class Home extends GameObject {
    private BufferedImage image = null;

    Home(int x, int y) {
        super(x, y);
        this.setWidth(10);
        this.setHeight(10);
        try {
            image = ImageIO.read(this.getClass().getResourceAsStream("/images/home.png"));
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
            graphics.setColor(Color.BLUE);
            graphics.drawOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        }
    }
}
