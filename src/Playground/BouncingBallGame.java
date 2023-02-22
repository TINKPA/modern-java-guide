package Playground;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.swing.JFrame;


public class BouncingBallGame extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;

    public BouncingBallGame() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
    }

    public void actionPerformed(ActionEvent e) {
        if (x < 0 || x > 560) {
            dx = -dx;
        }
        if (y < 0 || y > 330) {
            dy = -dy;
        }

        x += dx;
        y += dy;
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            dy = -1;
        }
        if (code == KeyEvent.VK_DOWN) {
            dy = 1;
        }
        if (code == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        if (code == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        dx = 0;
        dy = 0;
    }

    public static void main(String[] args) {
        BouncingBallGame game = new BouncingBallGame();
        game.setTitle("Bouncing Ball Game");
        game.setSize(600, 400);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
