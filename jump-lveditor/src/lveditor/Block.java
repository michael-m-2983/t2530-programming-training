package lveditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

public class Block {
    private static final int Width = 20, Height = 20;
    public double posX=0, posY=430, dir=0, blocktype=10;

    public int placedblocks = 0;
    public boolean[] keypressed = new boolean[100];

    public Block() {}
    public void PrintBlockArray(Blocks b) {
        for (int i=0;!(b.blocks[i][3]==0);i++) {
            System.out.println("("+b.blocks[i][0]+","+b.blocks[i][1]+","+b.blocks[i][2]+","+b.blocks[i][3]+")");}
    }
    public String LastBInArray(Blocks b) {
        int i=placedblocks;
        return b.blocks[i][0]+";"+b.blocks[i][1]+";"+b.blocks[i][2]+";"+b.blocks[i][3];
    }
    public void addblock(Blocks b) {
        b.blocks[placedblocks][0] = posX+Editor.ScreenX;
        b.blocks[placedblocks][1] = posY;
        b.blocks[placedblocks][2] = dir;
        b.blocks[placedblocks][3] = blocktype;
        System.out.println(LastBInArray(b));
        placedblocks+=1;
    }
    public void actions(Blocks b, Graphics g) {
        int KeyActionKeys[] = {4, 20, 23, 45, 22,31,24};
        for (int i=0;i<KeyActionKeys.length;i++) {
            int actionkeyN = KeyActionKeys[i];
            if (Editor.KeyPressed[actionkeyN] && !keypressed[actionkeyN]) {
                switch (actionkeyN) {
                    case 4: addblock(b);    break; // Space
                    case 20: blocktype-=10; break; // A
                    case 23: blocktype+=10; break; // D
                    case 45: blocktype -=1; break; // Z
                    case 22: blocktype +=1; break; // C
                    case 31: dir-=90; break; // Q
                    case 24: dir+=90; break; // E
                    
                    default:break;
                }
                keypressed[actionkeyN]=true;
            } else if (!Editor.KeyPressed[actionkeyN]) {keypressed[actionkeyN]=false;}}
        if(blocktype<10){blocktype=90;} if(blocktype>99){blocktype=10;}
        if(posY>430){posY=430;}

        render(g);
    }
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        int X=(int)(posX);int Y=(int)posY;
        Double Type=blocktype;char SType=Type.toString().charAt(1);int PType=(int) Math.floor(Type/10);
        // System.out.println(PbT);
        AffineTransform old = g2d.getTransform();
        g2d.rotate(Math.toRadians(dir),X+10,Y+10);
        switch (SType) { // Color
            case '0':
                g2d.setColor(Color.yellow);break;
            case '1':
                g2d.setColor(Color.pink);break;
            case '2':
                g2d.setColor(Color.red);break;
            case '3':
                g2d.setColor(Color.cyan);break;
            default:
                g2d.setColor(Color.white);break;
            }
        switch (PType) {
            case 1:
                g2d.setColor(Color.white); g.fillRect(X,Y,Width,Height);
                break;
            case 2:
                g2d.setColor(Color.red); g.fillPolygon(
                new int[] {X, X+Width, X+Width/2}
                ,new int[] {Y+Height, Y+Height, Y},3);
                break;
            case 3: // Orb
                g2d.fillOval(X+1, Y+1, Width-1, Height-1);
                break;
            case 4: // Pad
                g2d.fillArc(X, Y+Height-(Height/4), Width, Height/2, 180, -180);
                break;
            default:break;
        }
        g2d.setTransform(old);
    }
}
