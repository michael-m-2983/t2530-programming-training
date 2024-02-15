package lveditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
// do not use arraylist, it is garbage
import java.util.Arrays;

public class Blocks {
    private static final int Width = 20, Height = 20;

    public double blocks[][] = new double[100][4];
    // Blocks array
    // 0: posX, 1: posY, 2: dir, 3: type
    

    public Blocks() {

    }
    public void actions(Graphics g) {
        for (int i = 0; i < 100; i++) {
            
            render(g, i);
        }
        
    }
    public void render(Graphics g, int i) {
        if (!(blocks[i][3]==0)) {
            Graphics2D g2d = (Graphics2D)g;
            int bX=(int)(blocks[i][0] - Editor.ScreenX);int bY=(int)blocks[i][1];
            Double bT=blocks[i][3];char SbT=bT.toString().charAt(1);int PbT=(int) Math.floor(bT/10);
            // System.out.println(PbT);
            AffineTransform old = g2d.getTransform();
            g2d.rotate(Math.toRadians(blocks[i][2]),bX+Width/2,bY+Height/2);
            switch (SbT) { // Color
                case '0':
                    g.setColor(Color.yellow);break;
                case '1':
                    g.setColor(Color.pink);break;
                case '2':
                    g.setColor(Color.red);break;
                case '3':
                    g.setColor(Color.cyan);break;
                default:
                    g.setColor(Color.white);break;
            }
            switch (PbT) {
                case 1: // Block
                    g.setColor(Color.white); g.drawRect(bX,bY,Width,Height);
                    break;
                case 2: // Spike
                    g.setColor(Color.red); g.drawPolygon(
                    new int[] {bX, bX+Width, bX+Width/2}
                    ,new int[] {bY+Height, bY+Height, bY},3);
                    break;
                case 3: // Orb
                    g.drawOval(bX+1, bY+1, Width-1, Height-1);
                    break;
                case 4: // Pad
                    g.fillArc(bX, bY+Height-(Height/4), Width, Height/2, 180, -180);
                    break;
                default:break;
            }
            g2d.setTransform(old);
        }
    }
}
