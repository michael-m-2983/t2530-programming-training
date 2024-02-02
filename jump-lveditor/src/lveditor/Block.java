package lveditor;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
    public double posX=0, posY=430, dir=0, blocktype=1;
    public int placedblocks = 0;

    public Block() {}
    public void addblock(Blocks b) {
        b.blocks[placedblocks][0] = posX;
        b.blocks[placedblocks][1] = posY;
        b.blocks[placedblocks][2] = dir;
        b.blocks[placedblocks][3] = blocktype;
        placedblocks+=1;
    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect((int)posX,(int)posY,20,20);
    }
}
