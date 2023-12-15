package jump;

import java.awt.Color;
import java.awt.Graphics;

public class Spikes {
    public static Spike[][] spike; 
    private static final int BaseWidth = 20, Height = 20;
    public double posX, posY;
    public double velX, velY;


    public Spikes(int posX, int posY) {
        spike = new Spike[posX][posY];
        this.posX = posX;
        this.posY = posY;
        
    }

    public void collision(int posX, int posY, Player p, Game game) {

    }
    public void render(Graphics g) {

        g.setColor(Color.RED);
        g.fillPolygon(
            new int[] {(int) posX, (int) posX+BaseWidth, (int) (posX+BaseWidth/2)}
            ,new int[] {(int) posY, (int) posY, (int) posY-Height}
            , 3
            );
    }
}
