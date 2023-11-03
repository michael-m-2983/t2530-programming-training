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
            for(int y = 1; y < cols; y++) {
                if(bricks[x][y] == null) continue;
                renderBrick(x, y, bricks[x][y], g);
            }
        }
    }

    public void renderBrick(int brx, int bry, Brick brick, Graphics g) {
        // cols = 10, rows = 10; rows are up down, cols are <->
        // width = 750, height = 250
        //so if x=1,y=1; rect would be (75*1, 25*1, 75, 25)
        g.setColor(Color.GRAY);
        g.fillRect(
        (WIDTH / cols) * brx, 
        (HEIGHT / rows) * bry, 
        WIDTH / cols, 
        HEIGHT / rows
        ); 

        g.setColor(Color.BLACK);
        g.drawRect((WIDTH / cols) * brx, (HEIGHT / rows) * bry, WIDTH / cols, HEIGHT / rows);
    }

    public void checkCollisions(int bx, int by, int radius, Ball ball, Game game) {
        final int width = (WIDTH / cols); // 75
        final int height = (HEIGHT / rows); // 25

        
        // radius = 10
        for(int x1 = 1; x1 < rows; x1++) {
            for(int y1 = 1; y1 < cols; y1++) {
                // if ball x inside a brick range, radius*2 for diameter of ball
                // if ball x higher than 75(first brick) and lower than 150(2nd brick)
                if(bx > (x1 * width) - radius
                && bx < (x1 + 1) * width 
                // if ball y higher than 25(first brick) and lower than 50(2nd brick)
                && by > (y1 * height) - radius
                && by < (y1 + 1) * height) {
                    if (bricks[x1][y1] != null) {
                        game.score += 1;
                        if (
                            bx > (x1*width)-radius && bx < (x1*width) || 
                            bx < (x1+1)*width && bx > (x1+1)*width-radius
                        ) {
                            ball.velX *= -1;
                        }
                        if (
                            by > (y1*height)-radius && by < (y1*height) || 
                            by < (y1+1)*height && by > (y1+1)*height-radius
                        ) {
                            ball.velY *= -1;
                        }
                    }
                    bricks[x1][y1] = null;
                }
            }
        }
    }

    public int getWidth() {
        return WIDTH / cols;
    }
    public int getHeight() {
        return HEIGHT / rows;
    }
}
