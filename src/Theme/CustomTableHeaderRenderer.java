package Theme;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomTableHeaderRenderer extends JLabel implements TableCellRenderer {
    public CustomTableHeaderRenderer() {
        setOpaque(true);
        setBackground(UITheme.PRIMARY);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 12));
        setHorizontalAlignment(CENTER);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(36, 113, 163)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value != null ? value.toString() : "");
        return this;
    }
}
