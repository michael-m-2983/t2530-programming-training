package jump;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;


public class Game extends JPanel implements ActionListener, KeyListener {

    public static final int WINDOW_WIDTH = 750, WINDOW_HEIGHT = 500;
    public static final int WinWidth = 750, WinHeight = 450;
    public static boolean JumpKeyDown;

    public static final boolean useleveldata = true; // DEBUG LVDATA OFF / ON SWITCH
    public static final boolean debug = false;

    public static boolean play = true;

    private final Player player;

    private final Blocks blocks;

    private final Timer timer;
    private final Sound sound;

    public Game() { // - - - - - - - - VARIABLES at game start - - - - - - - - \\
        this.blocks = new Blocks(WinHeight);
        if (useleveldata) {
            try {
                this.blocks.importLvdata("Level.txt");
            } catch (FileNotFoundException e) {
                // Auto-generated catch block
                e.printStackTrace();
            } 
        } else {this.blocks.generate();}

        this.player = new Player(100,350);

        this.sound = new Sound("Endless_Night.wav");
        this.sound.play();

        this.timer = new Timer(1, this);
        this.timer.start();

        System.out.println("All Spawning/Variables successful");

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
    public void paint(Graphics g) { // draw and do crap, once per tick 

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Draw GROUND
        g.setColor(Color.WHITE);
        g.fillRect(0, WinHeight, WINDOW_WIDTH, 15); // 0, 450

        // Run Physics
        player.update();

        // Draw Entities
        player.render(g);
        blocks.render(g, player);

        // Draw Score
        g.setColor(Color.WHITE);
        String[] write = {
            "Timer: " + timer.toString(),
            "Player Y: " + Math.floor(player.posY),
            "Blocks: " + blocks.blocks,
            "lastblockx: " + blocks.block[blocks.lastblock][0]
        };
        for (int i=0;i<write.length;i++) {g.drawString(write[i],12,16+i*10);}
        g.dispose();

        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) { // - - - - - - - - - - CONTROLS - - - - - - - - - - \\
        // Left arrow: 37
        switch(e.getKeyCode()) {
            case 32: // Space bar
                JumpKeyDown = true;
                break;
            default: // Everything else
                break; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 32: // Space bar
                JumpKeyDown = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // timer thingy
        timer.start();

        repaint();
    }
}

