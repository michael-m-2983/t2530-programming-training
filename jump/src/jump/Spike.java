package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Spike {
    private static final int BaseWidth = 20, Height = 20;
    public double posX, posY;
    public double velX = -2, velY;


    public Spike(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void render(Graphics g) {
        posX += velX;
        posY += velY;
        

        // g.setColor(Color.RED);
        // g.fillPolygon(
        //     new int[] {(int) posX, (int) posX+BaseWidth, (int) (posX+BaseWidth/2)}
        //     ,new int[] {(int) posY, (int) posY, (int) posY-Height}
        //     , 3
        //     );
    }
}
