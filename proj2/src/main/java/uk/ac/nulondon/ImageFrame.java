package uk.ac.nulondon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ImageFrame {
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setContentPane(new ImagePanel(new ImageData("src/main/resources/beach.png"), frame));
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class ImagePanel extends JPanel implements KeyListener {
    ImageData image;
    JLabel label;
    int width, height;
    double scaleFactor;
    BufferedImage bufferedImage;

    public ImagePanel(ImageData image, JFrame f) {
        this.image = image;
        label = new JLabel("");
        scaleFactor = 32;
        bufferedImage = image.getBufferedImage();
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        label.setIcon(new ImageIcon(bufferedImage.getScaledInstance((int) (width*scaleFactor), (int) (height*scaleFactor), Image.SCALE_SMOOTH)));
        add(label);
        this.setBounds(0, 0, (int) (width*scaleFactor), (int) (height*scaleFactor));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    image.saveImage("src/main/resources/newImage.png");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_B) {
            image.highlightBluestSeam();
            bufferedImage = image.getBufferedImage();
            label.setIcon(new ImageIcon(bufferedImage.getScaledInstance((int) (image.width*scaleFactor), (int) (image.height*scaleFactor), Image.SCALE_SMOOTH)));
            try {
                image.saveImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            image.highlightLowestEnergySeam();
            bufferedImage = image.getBufferedImage();
            label.setIcon(new ImageIcon(bufferedImage.getScaledInstance((int) (image.width*scaleFactor), (int) (image.height*scaleFactor), Image.SCALE_SMOOTH)));
            try {
                image.saveImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            image.removeSeam();
            bufferedImage = image.getBufferedImage();
            label.setIcon(new ImageIcon(bufferedImage.getScaledInstance((int) (image.width*scaleFactor), (int) (image.height*scaleFactor), Image.SCALE_SMOOTH)));
            try {
                image.saveImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_U) {
            image.insertSeam();
            bufferedImage = image.getBufferedImage();
            label.setIcon(new ImageIcon(bufferedImage.getScaledInstance((int) (image.width*scaleFactor), (int) (image.height*scaleFactor), Image.SCALE_SMOOTH)));
            try {
                image.saveImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            image.debug();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
