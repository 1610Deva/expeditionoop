package Pengiriman;

public class Tarif {
    private float berat;
    private String jenisLayanan;

    public Tarif(float berat, String jenisLayanan) {
        this.berat = berat;
        this.jenisLayanan = jenisLayanan;
    }
    public float getberat() {
        return berat;
    }
    public void setberat(float berat) {
        this.berat = berat;
    }
    public String getJenisLayanan() {
        return jenisLayanan;
    }
    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    public double hitungTarif(double berat, String jenisLayanan) {
        if (berat <= 0) {
            throw new IllegalArgumentException("Berat harus lebih dari 0 dan jarak tidak boleh negatif.");
        }
        if (jenisLayanan == null || jenisLayanan.isEmpty()) {
            throw new IllegalArgumentException("Jenis layanan tidak boleh kosong.");
        }
        
        double tarifPerKg = 8000; // Tarif dasar per kg
        double tarifDasar = berat * tarifPerKg;
        switch (jenisLayanan.toLowerCase()) {
            case "ekspres":
                return tarifDasar * 1.5;
            case "reguler":
                return tarifDasar * 1.2;
            case "ekonomis":
                return tarifDasar;
            default:
                return tarifDasar;
        }
    }

}
