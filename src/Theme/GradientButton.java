package Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GradientButton extends JButton {
    private Color colorStart, colorEnd;
    private Color colorStartHover, colorEndHover;
    private boolean isHovered = false;
    
    public GradientButton(String text, Color start, Color end) {
        super(text);
        this.colorStart = start;
        this.colorEnd = end;
        this.colorStartHover = colorStart.brighter();
        this.colorEndHover = colorEnd.brighter();
        
        initStyle();
    }
    
    private void initStyle() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(new Font("Arial", Font.BOLD, 12));
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(120, 40));
        
        // ====== HOVER EFFECT ======
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Tentukan warna berdasarkan hover state
        Color start = isHovered ? colorStartHover : colorStart;
        Color end = isHovered ? colorEndHover : colorEnd;
        
        // Gradient paint
        GradientPaint gradient = new GradientPaint(0, 0, start, 
                                                   getWidth(), 0, end);
        g2d.setPaint(gradient);
        
        // Draw rounded rectangle
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
        
        // Draw text
        super.paintComponent(g);
    }
}
