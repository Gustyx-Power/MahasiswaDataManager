package Model;

public class StudentData {
    private String nama;
    private String nim;
    private String jurusan;
    private String jenisKelamin;
    private String hobi;

    public StudentData(String nama, String nim, String jurusan, 
                       String jenisKelamin, String hobi) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.jenisKelamin = jenisKelamin;
        this.hobi = hobi;
    }

    // Getter dan Setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }

    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { 
        this.jenisKelamin = jenisKelamin; 
    }

    public String getHobi() { return hobi; }
    public void setHobi(String hobi) { this.hobi = hobi; }
}
