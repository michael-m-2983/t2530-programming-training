package breakout;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends JPanel implements ActionListener, KeyListener {

    public static final int WINDOW_WIDTH = 750, WINDOW_HEIGHT = 500;

    private int score = 0;

    private final Player player;

    private final Ball ball;

    private final Board board;

    private final Timer timer;

    // TODO: bricks

    public Game() {
        this.player = new Player(WINDOW_WIDTH / 2 - Player.WIDTH / 2, WINDOW_HEIGHT - Player.HEIGHT * 5);

        this.ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT - Player.HEIGHT * 8);
<<<<<<< HEAD
        this.ball.velX = ThreadLocalRandom.current().nextFloat(-0.5f,0.5f);
        this.ball.velY = -1.4;
=======
        this.ball.posY = 15;
        this.ball.posX = 75;
        this.ball.velY = -0.1;
        this.ball.velX = 0;
>>>>>>> ad2f7363c170424b7572eed421acef9ce1cb0fe3

        this.board = new Board(10, 10);

        this.timer = new Timer(0, this);
        this.timer.start();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {

        // Check collision between ball and bricks
<<<<<<< HEAD

        board.checkCollisions(ball);
=======
        board.checkCollisions((int) ball.posX, (int) ball.posY, Ball.RADIUS, ball);
>>>>>>> ad2f7363c170424b7572eed421acef9ce1cb0fe3

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Draw Bricks
        board.render(g);

        // Draw Player
        player.render(g);

        // Draw ball
        ball.render(g, player);

        // Draw Score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 12, 16);
        g.drawString("BX: " + ball.posX, 12, 30);
        g.drawString("BY: " + ball.posY, 12, 42);
        g.drawString("Width of brick: " + board.getWidth(), 12, 54);
        g.drawString("Height of brick: " + board.getHeight(), 12, 66);
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
            case 38: //down
                player.moveUp();
                break;
            case 40: //up
                player.moveDown();
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
