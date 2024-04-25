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
        
        // radius = 10 x1 and y1 starts at 1 (first brick top-left position)
        for(int x1 = 1; x1 < rows; x1++) {
            for(int y1 = 1; y1 < cols; y1++) {
                //some useful variables
                int cornerX = (x1*width); // brick corner x pos (1st is 75)
                int cornerY = (y1*height); // brick corner y pos (2nd is 25)
                int nextcornerX = (x1+1)*width;
                int nextcornerY = (y1+1)*height;

                // collision detection: Brick hitbox is ball diameter larger
                if(bx > cornerX - radius // ball x higher than 65 = 75(1st brick) - 10(ball radius)
                && bx < nextcornerX // and lower than 150(2nd brick)?

                && by > cornerY - radius // ball y lower than than 15 = 25(1st brick) - 10(ball radius)
                && by < nextcornerY // and higher than 50(2nd brick)
                ) {
                    if (bricks[x1][y1] != null) {
                        game.score += 1;
                        // ball bounce
                        if ((
                            bx > cornerX-radius && 
                            bx < cornerX ||
                            bx < nextcornerX &&
                            bx > nextcornerY-radius
                            ) &&

                            // by > cornerY &&
                            // by < nextcornerY-radius &&
                            bricks[x1][y1] != null
                        ) {
                            ball.velX *= -1;
                            game.Xcontacts = 1;
                        }
                        if ((
                            by > cornerY-radius && 
                            by < cornerY ||
                            by < nextcornerY &&
                            by > nextcornerY-radius
                            ) &&
                            // bx > cornerX &&
                            // bx < nextcornerX-radius &&
                            bricks[x1][y1] != null
                        ) {
                            ball.velY *= -1;
                            game.Ycontacts += 1;
                        }
                        bricks[x1][y1] = null;
                    }
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
