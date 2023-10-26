package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Board {
    public final Brick[][] bricks;
    private final int rows, cols;

    public static final int WIDTH = Game.WINDOW_WIDTH, HEIGHT = Game.WINDOW_HEIGHT / 2;

    public Board(int rows, int cols) {
        bricks = new Brick[rows][cols];
        this.rows = rows;
        this.cols = cols;

        for(int x = 0; x < rows; x++) {
            if(x == 0 || x == rows - 1) continue;
            for(int y = 0; y < cols; y++) {
                bricks[x][y] = new Brick();
            }
        }
    }

    public void render(Graphics g) {
        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < cols; y++) {
                if(bricks[x][y] == null) continue;
                renderBrick(x, y + 1, bricks[x][y], g);
            }
        }
    }

    public void renderBrick(int x, int y, Brick brick, Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect((WIDTH / cols) * x, (HEIGHT / rows) * y, WIDTH / cols, HEIGHT / rows); 

        g.setColor(Color.BLACK);
        g.drawRect((WIDTH / cols) * x, (HEIGHT / rows) * y, WIDTH / cols, HEIGHT / rows);
    }

    public void checkCollisions(int x, int y, int radius) {
        final int width = (WIDTH / cols);
        final int height = (HEIGHT / rows);

        int halfRadius = radius / 2;

        for(int x1 = 0; x1 < rows; x1++) {
            for(int y1 = 0; y1 < cols; y1++) {
                if(x + halfRadius > (x1 * width) && x - halfRadius < (x1 + 1) * width && y + halfRadius > y1 * height && y - halfRadius < (y1 + 1) * height) {
                    bricks[x1][y1] = null;
                }
            }
        }
    }
}
