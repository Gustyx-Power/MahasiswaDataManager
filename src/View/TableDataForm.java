package View;

import Model.StudentData;
import Theme.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableDataForm extends JPanel {
    private ArrayList<Object> dataList;
    private MainForm mainForm;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public TableDataForm(ArrayList<Object> dataList, MainForm mainForm) {
        this.dataList = dataList;
        this.mainForm = mainForm;
        
        setLayout(new BorderLayout(15, 15));
        setBackground(UITheme.BACKGROUND_LIGHT);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel search
        JPanel searchPanel = createSearchPanel();
        
        // Panel tabel
        JPanel tablePanel = createTablePanel();
        
        // Panel tombol
        JPanel buttonPanel = createButtonPanel();
        
        add(searchPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // ====== SEARCH PANEL ======
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15, UITheme.BORDER),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel searchLabel = new JLabel("Cari Nama:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 11));
        searchLabel.setForeground(UITheme.TEXT_PRIMARY);
        
        searchField = new PlaceholderTextField("Ketik nama mahasiswa...");
        searchField.setPreferredSize(new Dimension(250, 35));
        
        GradientButton searchBtn = new GradientButton("Cari", 
            UITheme.PRIMARY, UITheme.PRIMARY_LIGHT);
        searchBtn.addActionListener(e -> cariData());
        
        GradientButton resetBtn = new GradientButton("Reset", 
            UITheme.INFO, new Color(62, 92, 114));
        resetBtn.addActionListener(e -> {
            searchField.setText("");
            refreshTable();
        });
        
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchBtn);
        panel.add(resetBtn);
        
        return panel;
    }

    // ====== TABLE PANEL ======
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new RoundedBorder(15, UITheme.BORDER),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        // Model tabel
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No");
        tableModel.addColumn("Nama");
        tableModel.addColumn("NIM");
        tableModel.addColumn("Jurusan");
        tableModel.addColumn("Jenis Kelamin");
        tableModel.addColumn("Hobi");
        
        dataTable = new JTable(tableModel);
        dataTable.setFont(new Font("Arial", Font.PLAIN, 11));
        dataTable.setRowHeight(28);
        dataTable.setGridColor(UITheme.BORDER_LIGHT);
        dataTable.setShowGrid(true);
        
        // ====== APPLY CUSTOM RENDERER (STRIPED) ======
        StripedTableCellRenderer cellRenderer = new StripedTableCellRenderer();
        for (int i = 0; i < dataTable.getColumnCount(); i++) {
            dataTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        
        // ====== CUSTOM HEADER ======
        CustomTableHeaderRenderer headerRenderer = new CustomTableHeaderRenderer();
        for (int i = 0; i < dataTable.getColumnCount(); i++) {
            dataTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        // Selection
        dataTable.setSelectionBackground(UITheme.PRIMARY_LIGHT);
        dataTable.setSelectionForeground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(null);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    // ====== BUTTON PANEL ======
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(UITheme.BACKGROUND_LIGHT);
        
        GradientButton hapusBtn = new GradientButton("Hapus Baris", 
            UITheme.DANGER, UITheme.DANGER_LIGHT);
        GradientButton refreshBtn = new GradientButton("Refresh", 
            UITheme.INFO, new Color(62, 92, 114));
        GradientButton kembaliBtn = new GradientButton("Kembali", 
            UITheme.PRIMARY, UITheme.PRIMARY_LIGHT);
        
        hapusBtn.addActionListener(e -> hapusBaris());
        refreshBtn.addActionListener(e -> refreshTable());
        kembaliBtn.addActionListener(e -> showCard("WELCOME"));
        
        panel.add(hapusBtn);
        panel.add(refreshBtn);
        panel.add(kembaliBtn);
        
        return panel;
    }

    // ====== SEARCH FUNCTION ======
    private void cariData() {
        String keyword = searchField.getText().toLowerCase().trim();
        tableModel.setRowCount(0);
        
        if (keyword.isEmpty()) {
            refreshTable();
            return;
        }
        
        int no = 1;
        for (Object obj : dataList) {
            StudentData student = (StudentData) obj;
            if (student.getNama().toLowerCase().contains(keyword)) {
                Object[] row = {
                    no++,
                    student.getNama(),
                    student.getNim(),
                    student.getJurusan(),
                    student.getJenisKelamin(),
                    student.getHobi()
                };
                tableModel.addRow(row);
            }
        }
    }

    // ====== REFRESH FUNCTION ======
    public void refreshTable() {
        tableModel.setRowCount(0);
        
        int no = 1;
        for (Object obj : dataList) {
            StudentData student = (StudentData) obj;
            Object[] row = {
                no++,
                student.getNama(),
                student.getNim(),
                student.getJurusan(),
                student.getJenisKelamin(),
                student.getHobi()
            };
            tableModel.addRow(row);
        }
    }

 // Bagian hapusBaris() - tambah updateStatistics()

    private void hapusBaris() {
        int selectedRow = dataTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih baris yang ingin dihapus!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Yakin ingin menghapus data ini?", 
            "Konfirmasi", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dataList.remove(selectedRow);
            refreshTable();
            
            // ====== UPDATE STATISTIK ======
            mainForm.updateStatistics();
            
            JOptionPane.showMessageDialog(this, 
                "Data berhasil dihapus!", 
                "Sukses", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void showCard(String cardName) {
        JPanel parent = (JPanel) getParent();
        CardLayout cl = (CardLayout) parent.getLayout();
        cl.show(parent, cardName);
    }
}
