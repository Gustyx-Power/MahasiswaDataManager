package View;

import Model.StudentData;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputDataForm extends JPanel {
    private ArrayList<Object> dataList;
    private MainForm mainForm;
    
    private JTextField namaField;
    private JTextField nimField;
    private JComboBox<String> jurusanCombo;
    private JRadioButton pria, wanita;
    private JCheckBox hobiBaca, hobiOlahraga, hobiGaming;
    private JTextArea displayArea;

    public InputDataForm(ArrayList<Object> dataList, MainForm mainForm) {
        this.dataList = dataList;
        this.mainForm = mainForm;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel input
        JPanel inputPanel = createInputPanel();
        
        // Panel tombol
        JPanel buttonPanel = createButtonPanel();
        
        // Panel display
        JPanel displayPanel = createDisplayPanel();
        
        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(topPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 15));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            "Form Input Data Mahasiswa"
        ));
        
        // Nama
        panel.add(createLabel("Nama:"));
        namaField = new JTextField();
        namaField.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(namaField);
        
        // NIM
        panel.add(createLabel("NIM:"));
        nimField = new JTextField();
        nimField.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(nimField);
        
        // Jurusan
        panel.add(createLabel("Jurusan:"));
        String[] jurusanList = {"Pilih Jurusan", "Teknik Informatika", 
                               "Sistem Informasi", "Teknik Komputer", "RPL"};
        jurusanCombo = new JComboBox<>(jurusanList);
        jurusanCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(jurusanCombo);
        
        // Jenis Kelamin
        panel.add(createLabel("Jenis Kelamin:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(Color.WHITE);
        pria = new JRadioButton("Laki-laki");
        wanita = new JRadioButton("Perempuan");
        pria.setBackground(Color.WHITE);
        wanita.setBackground(Color.WHITE);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(pria);
        genderGroup.add(wanita);
        genderPanel.add(pria);
        genderPanel.add(wanita);
        panel.add(genderPanel);
        
        // Hobi
        panel.add(createLabel("Hobi:"));
        JPanel hobiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        hobiPanel.setBackground(Color.WHITE);
        hobiBaca = new JCheckBox("Membaca");
        hobiOlahraga = new JCheckBox("Olahraga");
        hobiGaming = new JCheckBox("Gaming");
        hobiBaca.setBackground(Color.WHITE);
        hobiOlahraga.setBackground(Color.WHITE);
        hobiGaming.setBackground(Color.WHITE);
        hobiPanel.add(hobiBaca);
        hobiPanel.add(hobiOlahraga);
        hobiPanel.add(hobiGaming);
        panel.add(hobiPanel);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(Color.WHITE);
        
        JButton simpanBtn = new JButton("Simpan");
        JButton hapusBtn = new JButton("Hapus");
        JButton kembaliBtn = new JButton("Kembali");
        
        styleButton(simpanBtn);
        styleButton(hapusBtn);
        styleButton(kembaliBtn);
        
        simpanBtn.setBackground(new Color(39, 174, 96));
        hapusBtn.setBackground(new Color(192, 57, 43));
        kembaliBtn.setBackground(new Color(52, 73, 94));
        
        simpanBtn.addActionListener(e -> simpanData());
        hapusBtn.addActionListener(e -> hapusForm());
        kembaliBtn.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "WELCOME");
        });
        
        panel.add(simpanBtn);
        panel.add(hapusBtn);
        panel.add(kembaliBtn);
        
        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            "Data Terakhir"
        ));
        
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Courier New", Font.PLAIN, 11));
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(236, 240, 241));
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private void simpanData() {
        String nama = namaField.getText().trim();
        String nim = nimField.getText().trim();
        String jurusan = (String) jurusanCombo.getSelectedItem();
        String jk = pria.isSelected() ? "Laki-laki" : 
                   (wanita.isSelected() ? "Perempuan" : "Belum dipilih");
        
        StringBuilder hobi = new StringBuilder();
        if (hobiBaca.isSelected()) hobi.append("Membaca ");
        if (hobiOlahraga.isSelected()) hobi.append("Olahraga ");
        if (hobiGaming.isSelected()) hobi.append("Gaming");
        
        if (nama.isEmpty() || nim.isEmpty() || jurusan.equals("Pilih Jurusan")) {
            JOptionPane.showMessageDialog(this, 
                "Mohon isi semua field dengan lengkap!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        StudentData student = new StudentData(nama, nim, jurusan, jk, hobi.toString());
        dataList.add(student);
        
        // Tampilkan di display area
        displayArea.setText("Nama: " + nama + "\n" +
                           "NIM: " + nim + "\n" +
                           "Jurusan: " + jurusan + "\n" +
                           "Jenis Kelamin: " + jk + "\n" +
                           "Hobi: " + hobi.toString());
        
        JOptionPane.showMessageDialog(this, 
            "Data berhasil disimpan!", 
            "Sukses", 
            JOptionPane.INFORMATION_MESSAGE);
        
        hapusForm();
    }

    private void hapusForm() {
        namaField.setText("");
        nimField.setText("");
        jurusanCombo.setSelectedIndex(0);
        pria.setSelected(false);
        wanita.setSelected(false);
        hobiBaca.setSelected(false);
        hobiOlahraga.setSelected(false);
        hobiGaming.setSelected(false);
        displayArea.setText("");
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 35));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(52, 73, 94));
        return label;
    }
}
