package Pengiriman;
import java.util.Date;
import Pelanggan.Pengirim;
import Pelanggan.Penerima;

public class Pengiriman {
    private String idResi;
    private Date tanggal;
    private double berat;
    private String jenisBarang;
    private String jenisLayanan;
    private Pengirim pengirim;
    private Penerima penerima;
    private String statusPembayaran;
    private String statusPengiriman;
    private Tarif tarif;
    private Wilayah asal;
    private Wilayah tujuan;
    private double jarakWilayah;

    public Pengiriman(String idResi, Date tanggal, double berat, String jenisBarang, String jenisLayanan, Pengirim pengirim, 
            Penerima penerima, String statusPembayaran, String statusPengiriman, Tarif tarif, Wilayah asal, Wilayah tujuan, double jarakWilayah) {
        this.idResi = idResi;
        this.tanggal = tanggal;
        this.berat = berat;
        this.jenisBarang = jenisBarang;
        this.jenisLayanan = jenisLayanan;
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.statusPembayaran = statusPembayaran;
        this.statusPengiriman = statusPengiriman;
        this.tarif = tarif;
        this.asal = asal;
        this.tujuan = tujuan;
        this.jarakWilayah = jarakWilayah;
    }

    // Getter & Setter
    public String getIdResi() {
        return idResi;
    }
    public void setIdResi(String idResi) {
        this.idResi = idResi;
    }
    public Date getTanggal() {
        return tanggal;
    }
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    public double getBerat() {
        return berat;
    }
    public void setBerat(double berat) {
        this.berat = berat;
    }
    public String getJenisBarang() {
        return jenisBarang;
    }
    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }
    public String getJenisLayanan() {
        return jenisLayanan;
    }
    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }
    public Pengirim getPengirim() {
        return pengirim;
    }
    public void setPengirim(Pengirim pengirim) {
        this.pengirim = pengirim;
    }
    public Penerima getPenerima() {
        return penerima;
    }
    public void setPenerima(Penerima penerima) {
        this.penerima = penerima;
    }
    public String getStatusPembayaran() {
        return statusPembayaran;
    }
    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }
    public String getStatusPengiriman() {
        return statusPengiriman;
    }
    public void setStatusPengiriman(String statusPengiriman) {
        this.statusPengiriman = statusPengiriman;
    }
    public Tarif getTarif() {
        return tarif;
    }
    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }
    public Wilayah getAsal() {
        return asal;
    }
    public void setAsal(Wilayah asal) {
        this.asal = asal;
    }
    public Wilayah getTujuan() {
        return tujuan;
    }
    public void setTujuan(Wilayah tujuan) {
        this.tujuan = tujuan;
    }
    public double getJarakWilayah() {
        return jarakWilayah;
    }
    public void setJarakWilayah(double jarakWilayah) {
        this.jarakWilayah = jarakWilayah;
    }

    // Method 
    public void updateStatusPengiriman(String status) {
        this.statusPengiriman = status;
    }
    public void updateStatusPembayaran(String status) {
        this.statusPembayaran = status;
    }
    public boolean isSudahDiterima() {
        return this.statusPengiriman != null && this.statusPengiriman.equalsIgnoreCase("Sudah Diterima");
    }
    public boolean isDalamProses() {
        return this.statusPengiriman != null && this.statusPengiriman.equalsIgnoreCase("Dalam Proses");
    }
    public boolean isDibayar() {
        return this.statusPembayaran != null && this.statusPembayaran.equalsIgnoreCase("Dibayar");
    }
    public boolean isBelumDibayar() {
        return this.statusPembayaran != null && this.statusPembayaran.equalsIgnoreCase("Belum Dibayar");
}
}
