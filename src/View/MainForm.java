package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private InputDataForm inputForm;
    private TableDataForm tableForm;
    private ArrayList<Object> dataList;

    public MainForm() {
        setTitle("Sistem Manajemen Data Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Inisialisasi data
        dataList = new ArrayList<>();
        
        // Panel utama menggunakan CardLayout
        mainPanel = new JPanel(new CardLayout());
        
        // Buat panel judul
        JPanel headerPanel = createHeaderPanel();
        
        // Buat panel tombol menu
        JPanel menuPanel = createMenuPanel();
        
        // Buat form-form
        inputForm = new InputDataForm(dataList, this);
        tableForm = new TableDataForm(dataList, this);
        
        // Tambahkan ke CardLayout
        mainPanel.add(createWelcomePanel(), "WELCOME");
        mainPanel.add(inputForm, "INPUT");
        mainPanel.add(tableForm, "TABLE");
        
        // Layout utama
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.add(menuPanel, BorderLayout.SOUTH);
        
        add(contentPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(41, 128, 185));
        panel.setPreferredSize(new Dimension(900, 60));
        
        JLabel titleLabel = new JLabel("SISTEM MANAJEMEN DATA MAHASISWA");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        panel.add(titleLabel);
        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(236, 240, 241));
        panel.setPreferredSize(new Dimension(900, 60));
        
        JButton tambahBtn = new JButton("Tambah Data");
        JButton lihatBtn = new JButton("Lihat Data");
        JButton keluarBtn = new JButton("Keluar");
        
        // Styling tombol
        styleButton(tambahBtn);
        styleButton(lihatBtn);
        styleButton(keluarBtn);
        
        tambahBtn.addActionListener(e -> showCard("INPUT"));
        lihatBtn.addActionListener(e -> {
            tableForm.refreshTable();
            showCard("TABLE");
        });
        keluarBtn.addActionListener(e -> System.exit(0));
        
        panel.add(tambahBtn);
        panel.add(lihatBtn);
        panel.add(keluarBtn);
        
        return panel;
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JLabel welcomeLabel = new JLabel("Selamat Datang");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(52, 73, 94));
        
        panel.add(welcomeLabel, gbc);
        
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        JLabel infoLabel = new JLabel("Klik tombol menu di bawah untuk memulai");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setForeground(new Color(127, 140, 141));
        
        panel.add(infoLabel, gbc);
        
        return panel;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void showCard(String cardName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, cardName);
    }
}
