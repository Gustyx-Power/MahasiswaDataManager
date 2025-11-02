package Theme;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    private Color colorStart, colorEnd;
    
    public GradientPanel(Color start, Color end) {
        this.colorStart = start;
        this.colorEnd = end;
        setOpaque(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        GradientPaint gradient = new GradientPaint(0, 0, colorStart, 
                                                   0, getHeight(), colorEnd);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
