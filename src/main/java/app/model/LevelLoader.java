package app.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


class LevelLoader {
    private InputStream levels;

    GameObjects getLevel(int level) {
        level %= 60;
        if (level == 0)
            level = 60;
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(levels))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Maze: " + level)) {
                    reader.readLine();
                    break;
                }
            }
            int width = Integer.parseInt(reader.readLine().replace("Size X: ", ""));
            int height = Integer.parseInt(reader.readLine().replace("Size Y: ", ""));

            for (int i = 0; i < 3; i++) {
                reader.readLine();
            }
            int x0 = Model.FIELD_SELL_SIZE / 2;
            int y0 = Model.FIELD_SELL_SIZE / 2;

            for (int y = 0; y < height; y++) {
                line = reader.readLine();
                for (int x = 0; x < width; x++) {
                    switch (line.charAt(x)) {
                        case 'X':
                            walls.add(new Wall(x0 + x * Model.FIELD_SELL_SIZE, y0 + y * Model.FIELD_SELL_SIZE));
                            break;
                        case '*':
                            boxes.add(new Box(x0 + x * Model.FIELD_SELL_SIZE, y0 + y * Model.FIELD_SELL_SIZE));
                            break;
                        case '.':
                            homes.add(new Home(x0 + x * Model.FIELD_SELL_SIZE, y0 + y * Model.FIELD_SELL_SIZE));
                            break;
                        case '@':
                            player = new Player(x0 + x * Model.FIELD_SELL_SIZE, y0 + y * Model.FIELD_SELL_SIZE);
                            break;
                        case '&':
                            boxes.add(new Box(x0 + x * Model.FIELD_SELL_SIZE, y0 + y * Model.FIELD_SELL_SIZE));
                            homes.add(new Home(x0 + x * Model.FIELD_SELL_SIZE, y0 + y * Model.FIELD_SELL_SIZE));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, homes, player);
    }

    void setInputStream(InputStream inputStream) {
        this.levels = inputStream;
    }
}
