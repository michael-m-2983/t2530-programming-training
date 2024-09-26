package breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Board {
    public final int[][] bricks;
    private final int rows, cols;
    public final int width = 75, height = 25;

    public Board(int rows, int cols) {
        this.bricks = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;

        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < cols; y++) {
                bricks[x][y] = 1;
            }
        }
    }

    public void periodic(Graphics g, Ball ball) {
        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < cols; y++) {
                if(bricks[x][y]==0) continue;
                renderBrick((x+1)*width, (y+1)*width, bricks[x][y], g);
                checkCollision((x+1)*width, (y+1)*width, bricks[x][y], ball);
            }
        }
    }

    public void renderBrick(int bx, int by, int bs, Graphics g) {
        // cols = 10, rows = 10; rows are up down, cols are <->
        // width = 750, height = 250
        //so if x=1,y=1; rect would be (75*1, 25*1, 75, 25)
        switch (bs) {
            case 1: g.setColor(Color.GRAY); break;
            default: g.setColor(Color.GRAY); break;
        }
        g.fillRect(bx, by, bx+width, by+height); 
        g.setColor(Color.BLACK); g.drawRect(bx, by, bx+width, by+height);
    }

    public void checkCollision(int bx, int by, int bs, Ball ball) {
        





        // radius = 10 x1 and y1 starts at 1 (first brick top-left position)
        if (ball.contactrefresh > 0) {return;}
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
                    if (bricks[x1][y1] > 0) {
                        game.score += 1;
                        // ball bounce
                        if ((
                            bx > cornerX-radius && 
                            bx < cornerX ||
                            bx < nextcornerX &&
                            bx > nextcornerY-radius
                            )
                            // by > cornerY &&
                            // by < nextcornerY-radius &&
                            
                        ) {
                            ball.velX *= -1;
                            game.Xcontacts += 1;
                            ball.contactrefresh = 30;
                        } else if ((
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
                            ball.contactrefresh = 30;
                        }
                        bricks[x1][y1] = null;
                    }
                }
            }
        }
    }
}
