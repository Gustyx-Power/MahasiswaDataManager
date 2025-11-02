import View.MainForm;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm frame = new MainForm();
            frame.setVisible(true);
        });
    }
}
