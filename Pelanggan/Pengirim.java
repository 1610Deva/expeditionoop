package Pelanggan;

public class Pengirim extends Pelanggan{
    private String catatanKhusus;
    private boolean isDibayar;

    public Pengirim() {
        super();
        this.catatanKhusus = " ";
        this.isDibayar = false;
    }

    public Pengirim(String nama, String alamat, String noKontak, String catatanKhusus, boolean isDibayar) {
        super(nama, alamat, noKontak); // memanggil constructor parent
        this.catatanKhusus = catatanKhusus;
        this.isDibayar = isDibayar;
    }

    // Getter
    public String getCatatanKhusus() {
        return catatanKhusus;
    }

    public boolean getIsDibayar() {
        return isDibayar;
    }

    // Method buat pengiriman
    public void buatPengiriman() {
        System.out.println("=== PENGIRIMAN DIBUAT ===");
        System.out.println("Pengirim: " + this.nama);
        System.out.println("Alamat: " + this.alamat);
        System.out.println("Catatan Khusus: " + this.catatanKhusus);
        System.out.println("Status Bayar: " + (this.isDibayar ? "Sudah Dibayar" : "Belum Dibayar"));
        System.out.println("=========================");
    }
    
    public void cetakResi(Pengirim p) {
        System.out.println("=== RESI PENGIRIMAN ===");
        System.out.println("Pengirim: " + p.getNama());
        System.out.println("Alamat Pengirim: " + p.getAlamat());
        System.out.println("Kontak: " + p.getNoKontak());
        System.out.println("Catatan Khusus: " + p.getCatatanKhusus());
        System.out.println("Status Pembayaran: " + (p.getIsDibayar() ? "LUNAS" : "BELUM BAYAR"));
        System.out.println("========================");
    }

//    // Method untuk pembayaran ongkir
//    public void bayarOngkir(double jumlah) {
//        this.isDibayar = true;
//        System.out.println("Ongkos kirim sebesar Rp " + jumlah + " telah dibayar oleh " + this.nama);
//    }
//
//    // Implementasi wajib abstract method dari parent class
//    public void terimaNotifikasi(String pesan) {
//        System.out.println("📱 Notifikasi PENGIRIM untuk " + this.nama + ": " + pesan);
//    }

    // Override method tampilkanInfo untuk menampilkan info lengkap pengirim
    @Override
    public void tampilkanInfo() {
        System.out.println("=== INFORMASI PENGIRIM ===");
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("No Kontak: " + noKontak);
        System.out.println("Catatan Khusus: " + catatanKhusus);
        System.out.println("Status Bayar: " + (isDibayar ? "Sudah Dibayar" : "Belum Dibayar"));
        System.out.println("===============================");
    }

}
