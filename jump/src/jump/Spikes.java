package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Spikes {

    private static final int BaseWidth = 20, Height = 20;
    public double[] posX = {750,1000,1100,1500};
    public int posY;

    public Spikes(int posY) { // setup and crap
        this.posY = posY;
    }

    public void collision(double posX, int posY, Player p) {

    }

    public void render(Graphics g, Player p) {


        g.setColor(Color.RED);
        for (int i = 0; i < posX.length; i++) {
            this.collision(posX[i], posY, p);
            posX[i] -= 2; 




            g.fillPolygon(
                new int[] {(int) posX[i], (int) posX[i]+BaseWidth, (int) (posX[i]+BaseWidth/2)}
                ,new int[] {(int) posY, (int) posY, (int) posY-Height}
                , 3
            );
        }
    }
}
