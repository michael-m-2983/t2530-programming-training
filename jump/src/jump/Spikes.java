package jump;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Spikes {

    private static final int BaseWidth = 20, Height = 20;
    public double[] posX = {750,1000,1100,1500};
    public double posY;

    public Spikes(int posY) { // setup and crap
        this.posY = posY;
    }

    public void collision(int posX, int posY, Player p, Game game) {

    }

    public void render(Graphics g) {


        g.setColor(Color.RED);
        for (int i = 0; i < posX.length; i++) {
            posX[i] -= 2; 




            g.fillPolygon(
                new int[] {(int) posX[i], (int) posX[i]+BaseWidth, (int) (posX[i]+BaseWidth/2)}
                ,new int[] {(int) posY, (int) posY, (int) posY-Height}
                , 3
            );
        }
    }
}
