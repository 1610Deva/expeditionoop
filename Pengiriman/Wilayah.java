package Pengiriman;

public class Wilayah {
    private String kota;
    private String kecamatan;
    private String kelurahan;

    // Konstruktor dengan parameter lengkap
    public Wilayah(String kota, String kecamatan, String kelurahan) {
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
    }

    // Getter dan Setter
    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    // Override equals() untuk membandingkan dua objek Wilayah
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Wilayah other = (Wilayah) obj;
        return this.kota.equalsIgnoreCase(other.kota) &&
               this.kecamatan.equalsIgnoreCase(other.kecamatan) &&
               this.kelurahan.equalsIgnoreCase(other.kelurahan);
    }

    // Override toString() untuk representasi string yang jelas
    @Override
    public String toString() {
        return "Wilayah{" +
               "kota='" + kota + '\'' +
               ", kecamatan='" + kecamatan + '\'' +
               ", kelurahan='" + kelurahan + '\'' +
               '}';
    }
}