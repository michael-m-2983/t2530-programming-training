package jump;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.Math;

public class Spikes {

    private static final int BaseWidth = 20, Height = 20;
    public double[] posXs = {750};
    public int posY;

    public Spikes(int posY) { // setup and crap
        this.posY = posY;
    }

    public void collision(double posX, int posY, Player p) {

    }

    public void generate() {
        for(int i=1; i<100; i++) {
            posXs[i] = posXs[i-1] + Math.floor(Math.random()*5)*100;
        
        }
    }

    public void render(Graphics g, Player p) {


        g.setColor(Color.RED);
        for (int i = 0; i < posXs.length; i++) {
            this.collision(posXs[i], posY, p);
            posXs[i] -= 2; 




            g.fillPolygon(
                new int[] {(int) posXs[i], (int) posXs[i]+BaseWidth, (int) (posXs[i]+BaseWidth/2)}
                ,new int[] {(int) posY, (int) posY, (int) posY-Height}
                , 3
            );
        }
    }
}
