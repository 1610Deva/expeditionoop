package Pelanggan;

public class Penerima extends Pelanggan {
    // Atribut tambahan khusus untuk Penerima
    private String catatanPenerimaan;
    private boolean isDiterima;

    // Constructor default
    public Penerima() {
        super(); // memanggil constructor parent
        this.catatanPenerimaan = "";
        this.isDiterima = false;
    }

    // Constructor dengan parameter lengkap
    public Penerima(String nama, String alamat, String noKontak, String catatanPenerimaan, boolean isDiterima) {
        super(nama, alamat, noKontak); // memanggil constructor parent
        this.catatanPenerimaan = catatanPenerimaan;
        this.isDiterima = isDiterima;
    }

    // Getter
    public String getCatatanPenerimaan() {
        return catatanPenerimaan;
    }
    public boolean getIsDiterima() {
        return isDiterima;
    }

    // Method untuk konfirmasi penerimaan
    public void konfirmasiPenerimaan() {
        this.isDiterima = true;
        System.out.println("✅ Penerimaan paket telah dikonfirmasi oleh " + this.nama);
    }

    // Method untuk memberikan ulasan
    public void beriUlasan(String ulasan) {
        System.out.println("⭐ Ulasan dari " + this.nama + ": " + ulasan);
    }

    // Implementasi wajib abstract method dari parent class
    @Override
    public void terimaNotifikasi(String pesan) {
        System.out.println("📱 Notifikasi PENERIMA untuk " + this.nama + ": " + pesan);
    }

    // Override method tampilkanInfo untuk menampilkan info lengkap penerima
    @Override
    public void tampilkanInfo() {
        System.out.println("=== INFORMASI PENERIMA ===");
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("No Kontak: " + noKontak);
        System.out.println("Catatan Penerimaan: " + catatanPenerimaan);
        System.out.println("Status Diterima: " + (isDiterima ? "Sudah Diterima" : "Belum Diterima"));
        System.out.println("===============================");
    }
} 

