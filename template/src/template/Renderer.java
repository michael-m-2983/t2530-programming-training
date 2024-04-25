package template;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Renderer extends JPanel implements ActionListener { 
    // Initiate your variables here
    public static final int WindowWidth = 800;
    public static final int WindowHeight = 600;

    private final Timer timer;

    public Renderer() { // Initiate all your entities here

        this.timer = new Timer(1, this);
        this.timer.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    @Override
    public void paint(Graphics g) { // Render and execute all your entities here 

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WindowWidth, WindowHeight);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }
}
