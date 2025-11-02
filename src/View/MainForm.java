package View;

import Theme.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private InputDataForm inputForm;
    private TableDataForm tableForm;
    private ArrayList<Object> dataList;
    
    // ====== STATISTIK LABELS ======
    private JLabel totalLabel;
    private JLabel priaLabel;
    private JLabel wanLabel;

    public MainForm() {
        setTitle("Sistem Manajemen Data Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ====== FULLSCREEN & RESIZABLE ======
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);
        
        // Inisialisasi data
        dataList = new ArrayList<>();
        
        // Panel utama menggunakan CardLayout
        mainPanel = new JPanel(new CardLayout());
        
        // Buat panel dengan layout
        JPanel headerPanel = createHeaderPanel();
        
        // Inisialisasi form
        inputForm = new InputDataForm(dataList, this);
        tableForm = new TableDataForm(dataList, this);
        
        // Tambah ke CardLayout
        mainPanel.add(createWelcomePanel(), "WELCOME");
        mainPanel.add(inputForm, "INPUT");
        mainPanel.add(tableForm, "TABLE");
        
        // Layout utama - HAPUS MENU PANEL
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(mainPanel, BorderLayout.CENTER);
        
        add(contentPanel);
    }

    // ====== GRADIENT HEADER PANEL ======
    private JPanel createHeaderPanel() {
        JPanel panel = new GradientPanel(UITheme.PRIMARY, UITheme.PRIMARY_LIGHT);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("SISTEM MANAJEMEN DATA MAHASISWA");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        
        panel.add(titleLabel);
        return panel;
    }

    // ====== WELCOME PANEL (OPSI 2 - STATS + ACTIONS) ======
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(UITheme.BACKGROUND_LIGHT);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // ====== WELCOME SECTION ======
        JPanel welcomeSection = createWelcomeSection();
        
        // ====== COMBINED STATS & ACTIONS ======
        JPanel statsActionSection = createStatsWithActions();
        
        // ====== INFO SECTION ======
        JPanel infoSection = createInfoSection();
        
        // Layout
        JPanel topPanel = new JPanel(new BorderLayout(20, 20));
        topPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        topPanel.add(welcomeSection, BorderLayout.WEST);
        topPanel.add(statsActionSection, BorderLayout.CENTER);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(infoSection, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createWelcomeSection() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15, UITheme.BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)  // ← Kurangi dari 30 ke 20
        ));
        panel.setPreferredSize(new Dimension(280, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        
        // ====== JUDUL ======
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);  // ← Kurangi dari 20 ke 10
        JLabel welcomeLabel = new JLabel("Selamat Datang");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26));  // ← Kurangi dari 28 ke 26
        welcomeLabel.setForeground(UITheme.TEXT_PRIMARY);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(welcomeLabel, gbc);
        
        // ====== SEPARATOR ======
        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 8, 0);  // ← Kurangi dari 15 ke 8
        JSeparator separator = new JSeparator();
        separator.setForeground(UITheme.BORDER_LIGHT);
        separator.setPreferredSize(new Dimension(180, 1));  // ← Kurangi lebar
        panel.add(separator, gbc);
        
        // ====== INFO TEXT ======
        gbc.gridy = 2;
        gbc.insets = new Insets(8, 0, 2, 0);  // ← Kurangi dari 15 ke 8
        JLabel appNameLabel = new JLabel("Aplikasi Manajemen");
        appNameLabel.setFont(new Font("Arial", Font.PLAIN, 12));  // ← Kurangi dari 13 ke 12
        appNameLabel.setForeground(UITheme.TEXT_PRIMARY);
        appNameLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(appNameLabel, gbc);
        
        gbc.gridy = 3;
        gbc.insets = new Insets(2, 0, 10, 0);  // ← Kurangi
        JLabel appDescLabel = new JLabel("Data Mahasiswa");
        appDescLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        appDescLabel.setForeground(UITheme.TEXT_PRIMARY);
        appDescLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(appDescLabel, gbc);
        
        // ====== VERSION ======
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 0);  // ← Hilangkan spacing
        JLabel versionLabel = new JLabel("v1.0");
        versionLabel.setFont(new Font("Arial", Font.ITALIC, 10));  // ← Kurangi dari 11 ke 10
        versionLabel.setForeground(UITheme.TEXT_SECONDARY);
        versionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(versionLabel, gbc);
        
        return panel;
    }


    // ====== COMBINED STATS & ACTIONS ======
    private JPanel createStatsWithActions() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(UITheme.BACKGROUND_LIGHT);
        
        // ====== TOP LEFT: TOTAL MAHASISWA ======
        totalLabel = new JLabel("0");
        panel.add(createStatCard("📊 Total Mahasiswa", totalLabel, UITheme.PRIMARY));
        
        // ====== TOP RIGHT: TAMBAH DATA BUTTON ======
        GradientButton addBtn = new GradientButton("➕ TAMBAH DATA", 
            UITheme.SUCCESS, UITheme.SUCCESS_LIGHT);
        addBtn.setFont(new Font("Arial", Font.BOLD, 13));
        addBtn.setPreferredSize(new Dimension(150, 80));
        addBtn.addActionListener(e -> showCard("INPUT"));
        panel.add(addBtn);
        
        // ====== BOTTOM LEFT: GENDER STATS ======
        JPanel genderPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        genderPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        
        priaLabel = new JLabel("0");
        wanLabel = new JLabel("0");
        
        genderPanel.add(createStatCard("👨 Laki-laki", priaLabel, UITheme.SUCCESS));
        genderPanel.add(createStatCard("👩 Perempuan", wanLabel, UITheme.INFO));
        panel.add(genderPanel);
        
        // ====== BOTTOM RIGHT: LIHAT DATA BUTTON ======
        GradientButton viewBtn = new GradientButton("📋 LIHAT DATA", 
            UITheme.PRIMARY, UITheme.PRIMARY_LIGHT);
        viewBtn.setFont(new Font("Arial", Font.BOLD, 13));
        viewBtn.setPreferredSize(new Dimension(150, 80));
        viewBtn.addActionListener(e -> {
            tableForm.refreshTable();
            showCard("TABLE");
        });
        panel.add(viewBtn);
        
        return panel;
    }

    // ====== STAT CARD (UPDATE: TERIMA LABEL OBJECT) ======
    private JPanel createStatCard(String label, JLabel valueLabel, Color color) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(10, UITheme.BORDER),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Left side: Color indicator
        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(color);
        colorPanel.setPreferredSize(new Dimension(5, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        panel.add(colorPanel, gbc);
        
        // Label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 15, 0, 0);
        JLabel labelText = new JLabel(label);
        labelText.setFont(new Font("Arial", Font.PLAIN, 11));
        labelText.setForeground(UITheme.TEXT_SECONDARY);
        panel.add(labelText, gbc);
        
        // Value - SEKARANG BISA DI-UPDATE
        gbc.gridy = 1;
        valueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valueLabel.setForeground(UITheme.TEXT_PRIMARY);
        panel.add(valueLabel, gbc);
        
        return panel;
    }

    // ====== INFO SECTION ======
    private JPanel createInfoSection() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15, UITheme.BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        JLabel titleLabel = new JLabel("📌 Fitur & Cara Penggunaan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(UITheme.TEXT_PRIMARY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JTextArea infoArea = new JTextArea();
        infoArea.setText(
            "✅ FITUR APLIKASI:\n" +
            "   • Input Data Mahasiswa (Nama, NIM, Jurusan, Jenis Kelamin, Hobi)\n" +
            "   • Lihat Data dalam Tabel Terformat\n" +
            "   • Cari/Filter Data Mahasiswa\n" +
            "   • Hapus Data yang Tidak Perlu\n" +
            "   • Validasi NIM Duplikat Otomatis\n" +
            "   • UI Modern dengan Gradient & Animasi\n\n" +
            "🎯 CARA MENGGUNAKAN:\n" +
            "   1. Klik tombol '➕ TAMBAH DATA' untuk input data mahasiswa baru\n" +
            "   2. Isi semua form (Nama, NIM, Jurusan, JK, Hobi)\n" +
            "   3. Klik 'Simpan' untuk menyimpan data\n" +
            "   4. Klik tombol '📋 LIHAT DATA' untuk melihat semua data\n" +
            "   5. Gunakan kolom pencarian untuk filter data\n" +
            "   6. Pilih baris dan klik 'Hapus Baris' jika ingin menghapus\n\n" +
            "💡 TIPS:\n" +
            "   • NIM yang sama tidak dapat diduplikat\n" +
            "   • Anda dapat memilih multiple hobi\n" +
            "   • Data tersimpan selama aplikasi berjalan"
        );
        
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 11));
        infoArea.setForeground(UITheme.TEXT_PRIMARY);
        infoArea.setBackground(UITheme.BACKGROUND_LIGHT);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    // ====== UPDATE STATISTIK (DIPANGGIL DARI INPUT FORM) ======
    public void updateStatistics() {
        int total = dataList.size();
        int pria = 0, wanita = 0;
        
        for (Object obj : dataList) {
            Model.StudentData student = (Model.StudentData) obj;
            if (student.getJenisKelamin().equals("Laki-laki")) {
                pria++;
            } else if (student.getJenisKelamin().equals("Perempuan")) {
                wanita++;
            }
        }
        
        // Update label
        totalLabel.setText(String.valueOf(total));
        priaLabel.setText(String.valueOf(pria));
        wanLabel.setText(String.valueOf(wanita));
    }

    private void showCard(String cardName) {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, cardName);
    }
}
