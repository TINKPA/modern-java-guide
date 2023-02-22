import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

class SizedCanvas extends JPanel {
    // We use the constructor of SizedCanvas
    // to set the preferred size of the JPanel.
    SizedCanvas(int width, int height) {
        super();
        setPreferredSize(new Dimension(width, height));
    }

    // Same as before...
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.ORANGE);
        g.fillRect(150, 100, 500, 200);
    }
}

class FourthGUI {
    public static void main(String[] args) {
        // Same as before...
        JFrame frame = new JFrame("My Fourth GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instead of writing
        // frame.getContentPane().setPreferredSize(new Dimension(800, 400));
        // to set the preferred size of the contentPane,
        // we use the constructor of SizedCanvas
        // to set the preferred size of the JPanel.
        frame.add(new SizedCanvas(800, 400));

        // Same as before...
        frame.pack();
        frame.setVisible(true);
    }
}