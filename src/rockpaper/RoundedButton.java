package rockpaper;

import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;

public class RoundedButton extends JButton {
    private boolean hovering = false;

    public RoundedButton(String text) {
        super(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.white);
        setFont(getFont().deriveFont(Font.BOLD, 18f));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { hovering = true; repaint(); }
            public void mouseExited(MouseEvent e) { hovering = false; repaint(); }
            public void mousePressed(MouseEvent e) { playClickSound(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color base = hovering ? new Color(255,255,255,70) : new Color(255,255,255,30);
        g2.setColor(base);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.setColor(new Color(255,255,255,100));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth()-3, getHeight()-6
                , 20, 20);
        FontMetrics fm = g2.getFontMetrics();
        g2.setColor(Color.white);
        g2.drawString(getText(),
                (getWidth() - fm.stringWidth(getText())) / 2,
                (getHeight() + fm.getAscent()) / 2 - 3);
        g2.dispose();
    }

    private void playClickSound() {
        try {
            File soundFile = new File("sounds/click.wav");
            if (soundFile.exists()) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
        } catch (Exception e) {
            // ignore if no sound
        }
    }
}
