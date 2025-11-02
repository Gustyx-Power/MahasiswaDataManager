package View;

import Model.StudentData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableDataForm extends JPanel {
    private ArrayList<Object> dataList;
    private MainForm mainForm;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public TableDataForm(ArrayList<Object> dataList, MainForm mainForm) {
        this.dataList = dataList;
        this.mainForm = mainForm;
        
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel tabel
        JPanel tablePanel = createTablePanel();
        
        // Panel tombol
        JPanel buttonPanel = createButtonPanel();
        
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            "Data Mahasiswa"
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
        dataTable.setRowHeight(25);
        dataTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        dataTable.getTableHeader().setBackground(new Color(41, 128, 185));
        dataTable.getTableHeader().setForeground(Color.WHITE);
        dataTable.setSelectionBackground(new Color(52, 152, 219));
        
        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(Color.WHITE);
        
        JButton hapusBtn = new JButton("Hapus Baris");
        JButton refreshBtn = new JButton("Refresh");
        JButton kembaliBtn = new JButton("Kembali");
        
        styleButton(hapusBtn);
        styleButton(refreshBtn);
        styleButton(kembaliBtn);
        
        hapusBtn.setBackground(new Color(192, 57, 43));
        refreshBtn.setBackground(new Color(41, 128, 185));
        kembaliBtn.setBackground(new Color(52, 73, 94));
        
        hapusBtn.addActionListener(e -> hapusBaris());
        refreshBtn.addActionListener(e -> refreshTable());
        kembaliBtn.addActionListener(e -> {
            CardLayout cl = (CardLayout) getParent().getLayout();
            cl.show(getParent(), "WELCOME");
        });
        
        panel.add(hapusBtn);
        panel.add(refreshBtn);
        panel.add(kembaliBtn);
        
        return panel;
    }

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

    private void hapusBaris() {
        int selectedRow = dataTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Pilih baris yang ingin dihapus!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        dataList.remove(selectedRow);
        refreshTable();
        
        JOptionPane.showMessageDialog(this, 
            "Data berhasil dihapus!", 
            "Sukses", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(120, 35));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}
