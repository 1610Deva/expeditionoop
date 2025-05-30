package Pengiriman;

public class Wilayah {
    private String namaWilayah;

    public Wilayah(String namaWilayah) {
        this.namaWilayah = namaWilayah;
    }

    public String getNamaWilayah() {
        return namaWilayah;
    }

    public void setNamaWilayah(String namaWilayah) {
        this.namaWilayah = namaWilayah;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Wilayah)) return false;
        Wilayah other = (Wilayah) obj;
        return this.namaWilayah.equalsIgnoreCase(other.namaWilayah);
    }
}