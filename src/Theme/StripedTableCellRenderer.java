package Theme;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class StripedTableCellRenderer extends JLabel implements TableCellRenderer {
    public StripedTableCellRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        setText(value != null ? value.toString() : "");
        setFont(new Font("Arial", Font.PLAIN, 11));
        
        if (isSelected) {
            setBackground(UITheme.PRIMARY_LIGHT);
            setForeground(Color.WHITE);
        } else if (row % 2 == 0) {
            setBackground(Color.WHITE);
            setForeground(UITheme.TEXT_PRIMARY);
        } else {
            setBackground(UITheme.BACKGROUND);
            setForeground(UITheme.TEXT_PRIMARY);
        }
        
        setHorizontalAlignment(CENTER);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        return this;
    }
}
