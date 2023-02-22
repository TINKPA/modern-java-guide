package GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BouncingBall extends JFrame {
    public static void main(String[] args) {
        BouncingBall b = new BouncingBall();
    }




    private Timer t;




    public BouncingBall() {
        super("Bouncing Ball");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container c = new Container();
        c.setLayout(new GridLayout(2, 1));
        c.add(new StartButton());
        c.add(new StopButton());

        BouncingBallRegion b = new BouncingBallRegion();
        t = new Timer(10, b);

        setLayout(new GridLayout(2, 1));
        add(b);
        add(c);

        setSize(800, 400);
        setVisible(true);
    }




    private class StartButton extends JButton implements ActionListener {
        private StartButton() {
            super("start");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            t.start();
        }
    }
    private class StopButton extends JButton implements ActionListener {
        private StopButton() {
            super("stop");
            addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            t.stop();
        }
    }




    private class BouncingBallRegion extends JPanel  implements ActionListener, ChangeListener {
        private int ballX = 0;
        private int ballY = 0;

        private int m_ballX = 100;
        private int m_ballY = 100;

        private int speed = 1;

        private int ballDiam = 50;

        private boolean moveRight = true;
        private boolean moveDown  = true;

        private boolean m_moveRight = true;
        private boolean m_moveDown  = true;

        BouncingBallRegion() {
            //Create the label.
            JLabel silderLabel = new JLabel("Speed", JLabel.CENTER);
            silderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Create the slider
            JSlider speed = new JSlider(JSlider.HORIZONTAL, 0,20,1);

            speed.addChangeListener(this);
            add(speed);
            add(silderLabel);
        }


        private void updateDirection() {
            if (ballX + ballDiam >= this.getWidth())  { moveRight = false; }
            if (ballY + ballDiam >= this.getHeight()) { moveDown  = false; }

            if (ballX <= 0) { moveRight = true; }
            if (ballY <= 0) { moveDown  = true; }

            if (m_ballX + ballDiam >= this.getWidth())  { m_moveRight = false; }
            if (m_ballY + ballDiam >= this.getHeight()) { m_moveDown  = false; }

            if (m_ballX <= 0) { m_moveRight = true; }
            if (m_ballY <= 0) { m_moveDown  = true; }
        }


        private void updatePosition() {
            if (moveRight) { ballX+=speed; }
            else           { ballX-=speed; }

            if (moveDown)  { ballY+=speed; }
            else           { ballY-=speed; }

            if (m_moveRight) { m_ballX+=speed; }
            else           { m_ballX-=speed; }

            if (m_moveDown)  { m_ballY+=speed; }
            else           { m_ballY-=speed; }
        }


        public void actionPerformed(ActionEvent e) {
            updateDirection();
            updatePosition();
            repaint();
        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(ballX, ballY, ballDiam, ballDiam);
            g.setColor(Color.BLUE);
            g.fillOval(m_ballX, m_ballY, ballDiam, ballDiam);
        }

        /**
         * Invoked when the target of the listener has changed its state.
         *
         * @param e a ChangeEvent object
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider) e.getSource();
            if (source.getValueIsAdjusting()) {
                speed = (int)source.getValue();
                System.out.println(speed);
            }
        }
    }
}