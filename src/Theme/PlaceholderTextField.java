package Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {
    public String placeholder;
    
    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        initStyle();
    }
    
    private void initStyle() {
        setFont(new Font("Arial", Font.PLAIN, 13));
        setForeground(new Color(149, 165, 166));
        
        // Border dengan padding
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UITheme.BORDER, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        
        // Set placeholder text
        setText(placeholder);
        setCaretColor(UITheme.TEXT_PRIMARY);
        
        // ====== FOCUS LISTENER ======
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(UITheme.TEXT_PRIMARY);
                }
                // Border jadi biru saat fokus
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UITheme.PRIMARY, 2),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(new Color(149, 165, 166));
                    setText(placeholder);
                }
                // Border kembali abu-abu
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(UITheme.BORDER, 2),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)
                ));
            }
        });
    }
    
    public String getCleanText() {
        String text = getText().trim();
        if (text.equals(placeholder)) return "";
        return text;
    }
}
