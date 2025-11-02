package View;

import Model.StudentData;
import Theme.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputDataForm extends JPanel {
    private ArrayList<Object> dataList;
    private MainForm mainForm;
    
    private PlaceholderTextField namaField;
    private PlaceholderTextField nimField;
    private JComboBox<String> jurusanCombo;
    private JRadioButton pria, wanita;
    private JCheckBox hobiBaca, hobiOlahraga, hobiGaming;
    private JTextArea displayArea;

    public InputDataForm(ArrayList<Object> dataList, MainForm mainForm) {
        this.dataList = dataList;
        this.mainForm = mainForm;
        
        setLayout(new BorderLayout(20, 20));
        setBackground(UITheme.BACKGROUND_LIGHT);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel input (2 kolom - lebih modern)
        JPanel inputPanel = createInputPanel();
        
        // Panel tombol
        JPanel buttonPanel = createButtonPanel();
        
        // Panel display
        JPanel displayPanel = createDisplayPanel();
        
        // Layout
        JPanel topPanel = new JPanel(new BorderLayout(20, 20));
        topPanel.setBackground(UITheme.BACKGROUND_LIGHT);
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(displayPanel, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15, UITheme.BORDER),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)  // ← Kurangi dari 25
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);  // ← Kurangi dari 15,15,15,15
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        
        // ====== KOLOM 1 (KIRI) ======
        
        // Nama - Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 12, 5, 12);  // ← Untuk label
        panel.add(createLabel("Nama:"), gbc);
        
        // Nama - TextField
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 12, 12, 12);  // ← Untuk input
        namaField = new PlaceholderTextField("Masukkan Nama");
        namaField.setPreferredSize(new Dimension(200, 35));
        panel.add(namaField, gbc);
        
        // NIM - Label
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 12, 5, 12);
        panel.add(createLabel("NIM:"), gbc);
        
        // NIM - TextField
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 12, 12, 12);
        nimField = new PlaceholderTextField("Masukkan NIM");
        nimField.setPreferredSize(new Dimension(200, 35));
        panel.add(nimField, gbc);
        
        // Jenis Kelamin - Label
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 12, 5, 12);
        panel.add(createLabel("Jenis Kelamin:"), gbc);
        
        // Jenis Kelamin - RadioButton
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 12, 12, 12);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        genderPanel.setBackground(Color.WHITE);
        pria = new JRadioButton("Laki-laki");
        wanita = new JRadioButton("Perempuan");
        pria.setBackground(Color.WHITE);
        wanita.setBackground(Color.WHITE);
        pria.setFont(new Font("Arial", Font.PLAIN, 12));
        wanita.setFont(new Font("Arial", Font.PLAIN, 12));
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(pria);
        genderGroup.add(wanita);
        genderPanel.add(pria);
        genderPanel.add(wanita);
        panel.add(genderPanel, gbc);
        
        // ====== KOLOM 2 (KANAN) ======
        
        // Jurusan - Label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 12, 5, 12);
        panel.add(createLabel("Jurusan:"), gbc);
        
        // Jurusan - ComboBox
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 12, 12, 12);
        String[] jurusanList = {"Pilih Jurusan", "Teknik Informatika", 
                               "Sistem Informasi", "Teknik Komputer", "RPL"};
        jurusanCombo = new JComboBox<>(jurusanList);
        jurusanCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        jurusanCombo.setBackground(Color.WHITE);
        jurusanCombo.setPreferredSize(new Dimension(200, 35));
        panel.add(jurusanCombo, gbc);
        
        // Hobi - Label
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 12, 5, 12);
        panel.add(createLabel("Hobi:"), gbc);
        
        // Hobi - CheckBox
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 12, 0, 12);
        gbc.gridheight = 3;
        JPanel hobiPanel = new JPanel(new GridLayout(3, 1, 0, 8));  // ← Kurangi gap dari 10 ke 8
        hobiPanel.setBackground(Color.WHITE);
        hobiBaca = new JCheckBox("📚 Membaca");
        hobiOlahraga = new JCheckBox("⚽ Olahraga");
        hobiGaming = new JCheckBox("🎮 Gaming");
        hobiBaca.setBackground(Color.WHITE);
        hobiOlahraga.setBackground(Color.WHITE);
        hobiGaming.setBackground(Color.WHITE);
        hobiBaca.setFont(new Font("Arial", Font.PLAIN, 12));
        hobiOlahraga.setFont(new Font("Arial", Font.PLAIN, 12));
        hobiGaming.setFont(new Font("Arial", Font.PLAIN, 12));
        hobiPanel.add(hobiBaca);
        hobiPanel.add(hobiOlahraga);
        hobiPanel.add(hobiGaming);
        panel.add(hobiPanel, gbc);
        
        return panel;
    }

    // ====== BUTTON PANEL ======
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panel.setBackground(UITheme.BACKGROUND_LIGHT);
        
        GradientButton simpanBtn = new GradientButton("✓ Simpan", 
            UITheme.SUCCESS, UITheme.SUCCESS_LIGHT);
        GradientButton hapusBtn = new GradientButton("✕ Hapus", 
            UITheme.DANGER, UITheme.DANGER_LIGHT);
        GradientButton kembaliBtn = new GradientButton("← Kembali", 
            UITheme.INFO, new Color(62, 92, 114));
        
        simpanBtn.setPreferredSize(new Dimension(130, 45));
        hapusBtn.setPreferredSize(new Dimension(130, 45));
        kembaliBtn.setPreferredSize(new Dimension(130, 45));
        
        simpanBtn.addActionListener(e -> simpanData());
        hapusBtn.addActionListener(e -> hapusForm());
        kembaliBtn.addActionListener(e -> showCard("WELCOME"));
        
        panel.add(simpanBtn);
        panel.add(hapusBtn);
        panel.add(kembaliBtn);
        
        return panel;
    }

    // ====== DISPLAY PANEL (SIDE BY SIDE) ======
    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(UITheme.BACKGROUND_LIGHT);
        panel.setPreferredSize(new Dimension(300, 0));
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15, UITheme.BORDER),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        displayArea.setEditable(false);
        displayArea.setBackground(UITheme.BACKGROUND);
        displayArea.setForeground(UITheme.TEXT_PRIMARY);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(null);
        
        JLabel titleLabel = new JLabel("Preview Data");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleLabel.setForeground(UITheme.TEXT_PRIMARY);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void simpanData() {
        String nama = namaField.getCleanText();
        String nim = nimField.getCleanText();
        String jurusan = (String) jurusanCombo.getSelectedItem();
        String jk = pria.isSelected() ? "Laki-laki" : 
                   (wanita.isSelected() ? "Perempuan" : "Belum dipilih");
        
        StringBuilder hobi = new StringBuilder();
        if (hobiBaca.isSelected()) hobi.append("Membaca ");
        if (hobiOlahraga.isSelected()) hobi.append("Olahraga ");
        if (hobiGaming.isSelected()) hobi.append("Gaming");
        
        // Validasi
        if (nama.isEmpty() || nim.isEmpty() || jurusan.equals("Pilih Jurusan")) {
            JOptionPane.showMessageDialog(this, 
                "Mohon isi semua field dengan lengkap!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Cek duplikat NIM
        for (Object obj : dataList) {
            StudentData student = (StudentData) obj;
            if (student.getNim().equals(nim)) {
                JOptionPane.showMessageDialog(this, 
                    "NIM sudah terdaftar!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Simpan
        StudentData student = new StudentData(nama, nim, jurusan, jk, hobi.toString());
        dataList.add(student);
        
        displayArea.setText("✓ DATA DISIMPAN\n\n" +
                           "━━━━━━━━━━━━━━━━━\n" +
                           "Nama      : " + nama + "\n" +
                           "NIM       : " + nim + "\n" +
                           "Jurusan   : " + jurusan + "\n" +
                           "JK        : " + jk + "\n" +
                           "Hobi      : " + hobi.toString() + "\n" +
                           "━━━━━━━━━━━━━━━━━");
        
        JOptionPane.showMessageDialog(this, 
            "Data berhasil disimpan!", 
            "Sukses", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // ====== PANGGIL UPDATE STATISTIK ======
        mainForm.updateStatistics();
        
        hapusForm();
    }

    private void hapusForm() {
        namaField.setText(namaField.placeholder);
        nimField.setText(nimField.placeholder);
        jurusanCombo.setSelectedIndex(0);
        pria.setSelected(false);
        wanita.setSelected(false);
        hobiBaca.setSelected(false);
        hobiOlahraga.setSelected(false);
        hobiGaming.setSelected(false);
        displayArea.setText("");
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(UITheme.TEXT_PRIMARY);
        return label;
    }

    private void showCard(String cardName) {
        JPanel parent = (JPanel) getParent();
        CardLayout cl = (CardLayout) parent.getLayout();
        cl.show(parent, cardName);
    }
}
