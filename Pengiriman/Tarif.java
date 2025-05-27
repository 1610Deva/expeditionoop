package Pengiriman;

public class Tarif {
    private float hargaPerKg;
    private float hargaPerJarak;
    private String jenisLayanan;

    public Tarif(float hargaPerKg, float hargaPerJarak, String jenisLayanan) {
        this.hargaPerKg = hargaPerKg;
        this.hargaPerJarak = hargaPerJarak;
        this.jenisLayanan = jenisLayanan;
    }

    public float gethargaPerKg() {
        return hargaPerKg;
    }
    public void sethargaPerKg(float hargaPerKg) {
        this.hargaPerKg = hargaPerKg;
    }
    public float gethargaPerJarak() {
        return hargaPerJarak;
    }
    public void sethargaPerJarak(float hargaPerJarak) {
        this.hargaPerJarak = hargaPerJarak;
    }
    public String getJenisLayanan() {
        return jenisLayanan;
    }
    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    public double hitungTarif(double berat, double jarak, String jenisLayanan) {
        if (berat <= 0 || jarak < 0) {
            throw new IllegalArgumentException("Berat harus lebih dari 0 dan jarak tidak boleh negatif.");
        }
        if (jenisLayanan == null || jenisLayanan.isEmpty()) {
            throw new IllegalArgumentException("Jenis layanan tidak boleh kosong.");
        }
        
        double tarifDasar = berat * this.hargaPerKg + jarak * this.hargaPerJarak;
        switch (jenisLayanan.toLowerCase()) {
            case "ekspres":
                return tarifDasar * 1.5;
            case "reguler":
                return tarifDasar * 1.2;
            case "ekonomi":
            default:
                return tarifDasar;
        }
    }

}
