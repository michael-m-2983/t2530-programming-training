package hello;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setSize(1000, 100);
        window.setResizable(false);

        window.setTitle("Team 2530 Jump Game");
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(new Renderer());

        window.setVisible(true);
    }
}