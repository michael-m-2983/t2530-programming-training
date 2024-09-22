package breakout;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements ActionListener, KeyListener {

    public static final int WINDOW_WIDTH = 750, WINDOW_HEIGHT = 500;

    private int score = 0;

    private final Player player;

    private final Ball ball;

    private final Board board;

    private final Timer timer;

    private final Image backgroundImage;

    // TODO: bricks

    public Game() throws IOException {
        this.player = new Player(WINDOW_WIDTH / 2 - Player.WIDTH / 2, WINDOW_HEIGHT - Player.HEIGHT * 5);

        this.ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT - Player.HEIGHT * 8);
        this.ball.velY = -0.6;

        this.board = new Board(10, 10);

        this.timer = new Timer(1, this);
        this.timer.start();

        this.backgroundImage = ImageIO.read(new File("background.png"));

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {

        // Check collision between ball and bricks
        board.checkCollisions((int) ball.posX, (int) ball.posY, Ball.RADIUS, ball);

        // Draw background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        g.drawImage(this.backgroundImage, 0, 0, null);
        
        // Draw Bricks
        board.render(g);

        // Draw Player
        player.render(g);

        // Draw ball
        ball.render(g, player);

        // Draw Score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 12, 16);

        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 37: // Left
                player.moveLeft();
                break;
            case 39: // Right
                player.moveRight();
                break;
            default: // Everything else
                break; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        repaint();
    }
    
}
