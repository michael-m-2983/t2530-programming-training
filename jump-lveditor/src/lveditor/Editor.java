package lveditor;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

public class Editor extends JPanel implements ActionListener, KeyListener{
    public static final int WinWidth=750,WinHeight=500;
    public static boolean KeyPressed[] = new boolean[4];

    private final Timer timer;

    private final Blocks blocks;
    private final Block block;

    public Editor() {
        this.blocks = new Blocks();
        this.block = new Block();

        this.timer = new Timer(1, this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
new Timer(1, this);
        this.timer.start();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
    }
    @Override
    public void paint(Graphics g) {
        // BG and Ground
        g.setColor(Color.black); g.fillRect(0, 0, WinWidth, WinHeight);
        g.setColor(Color.white); g.drawRect(-1, WinHeight-50, WinWidth, 15);

        // Running code


        // rendering
        block.render(g);
        blocks.render(g);
        // scores
        g.setColor(Color.white);
        g.drawString(""+block.posY, 12, 12);

        g.dispose();
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
        public void keyPressed(KeyEvent e) { // - - - - - - - - - - CONTROLS - - - - - - - - - - \\
            switch(e.getKeyCode()) { // is 'a' 49?
                case 37: // Left Key
                    block.posX-=10;
                    KeyPressed[0] = true;
                    break;
                case 38: // Up Key
                    block.posY-=10;
                    KeyPressed[1] = true;
                    break;
                case 39: // Right Key
                    block.posX+=10;
                    KeyPressed[2] = true;
                    break;
                case 40: // Down Key
                    block.posY+=10;
                    KeyPressed[3] = true;
                    break;
                case 65:
                    block.addblock(blocks);
                    break;
                default: // Everything else
                    break; 
            }
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case 37: // Left Key
                    KeyPressed[0] = false;
                    break;
                case 38: // Up Key
                    KeyPressed[1] = false;
                    break;
                case 39: // Right Key
                    KeyPressed[2] = false;
                    break;
                case 40: // Down Key
                    KeyPressed[3] = false;
                    break;
                default: // Everything else
                    break; 
            }
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start(); repaint();
    }
}
