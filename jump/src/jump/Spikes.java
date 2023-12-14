package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Spikes {
    private static final int BaseWidth = 20, Height = 20;
    public int posX, posY;

    public Spikes(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillPolygon(
            new int[] {posX, posX+BaseWidth, posX+BaseWidth/2}
            ,new int[] {posY, posY, posY-Height}
            , 3
            );
    }
}
