package breakout;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setSize(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        window.setResizable(false);

        window.setTitle("Team 2530 Breakout");
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(new Game());

        window.setVisible(true);
    }
}