package Pelanggan;

public class Pengirim extends Pelanggan{
    private String alamat;
    private String catatanKhusus;

    public Pengirim() {
        super();
        this.alamat = " ";
        this.catatanKhusus = " ";
    }

    public Pengirim(String nama, String alamat, String noKontak, String catatanKhusus) {
        super(nama, noKontak); // memanggil constructor parent
        this.alamat = alamat;
        this.catatanKhusus = catatanKhusus;
    }

    // Getter
    public String getCatatanKhusus() {
        return catatanKhusus;
    }
    public String getAlamat() {
        return alamat;
    }

    // Method buat pengiriman
    public void buatPengiriman() {
        System.out.println("=== PENGIRIMAN DIBUAT ===");
        System.out.println("Pengirim: " + this.nama);
        System.out.println("Alamat: " + this.alamat);
        System.out.println("Catatan Khusus: " + this.catatanKhusus);
        System.out.println("=========================");
    }
    
    public void cetakResi(Pengirim p) {
        System.out.println("=== RESI PENGIRIMAN ===");
        System.out.println("Pengirim: " + p.getNama());
        System.out.println("Alamat Pengirim: " + p.getAlamat());
        System.out.println("Kontak: " + p.getNoKontak());
        System.out.println("Catatan Khusus: " + p.getCatatanKhusus());
        System.out.println("========================");
    }
}
