package Pengiriman;
import java.util.List;

public class Wilayah {
    private String namaWilayah;
    private List<String> daftarKecamatan;

    public Wilayah(String namaWilayah, List<String> daftarKecamatan) {
        this.namaWilayah = namaWilayah;
        this.daftarKecamatan = daftarKecamatan;
    }

    public List<String> getDaftarKecamatan() {
        return daftarKecamatan;
    }
    public void setDaftarKecamatan(List<String> daftarKecamatan) {
        this.daftarKecamatan = daftarKecamatan;
    }
    public String getNamaWilayah() {
        return namaWilayah;
    }
    public void setNamaWilayah(String namaWilayah) {
        this.namaWilayah = namaWilayah;
    }

    public void tambahKecamatan(String kecamatan) {
        if (!daftarKecamatan.contains(kecamatan)) {
            daftarKecamatan.add(kecamatan);
        }
    }
}
