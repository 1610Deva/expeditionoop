package Pengiriman;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class PengelolaPengiriman {
    private List<Pengiriman> daftarPengiriman;

    public PengelolaPengiriman (List<Pengiriman> daftarPengiriman) {
        this.daftarPengiriman = daftarPengiriman;
    }

    public List<Pengiriman> getDaftarPengiriman() {
        return daftarPengiriman;
    }
    public void setDaftarPengiriman(List<Pengiriman> daftarPengiriman) {
        this.daftarPengiriman = daftarPengiriman;
    }
    public void tambahPengiriman(Pengiriman pengiriman) {
        daftarPengiriman.add(pengiriman);
    }
    public void hapusPengiriman(Pengiriman pengiriman) {
        daftarPengiriman.remove(pengiriman);
    }
    public Pengiriman cariPengiriman(String idResi) {
        for (Pengiriman pengiriman : daftarPengiriman) {
            if (pengiriman.getIdResi().equals(idResi)) {
                return pengiriman;
            }
        }
        return null; // Jika tidak ditemukan
    }
    public List<Pengiriman> getSemuaPengiriman() {
        return daftarPengiriman;
    }
    public List<Pengiriman> getPengirimanDalamRentangWaktu(Date tanggalAwal, Date tanggalAkhir) {
        List<Pengiriman> hasil = new ArrayList<>();
        for (Pengiriman pengiriman : daftarPengiriman) {
            Date tanggalPengiriman = pengiriman.getTanggal();
            if (tanggalPengiriman != null && !tanggalPengiriman.before(tanggalAwal) && !tanggalPengiriman.after(tanggalAkhir)) {
                hasil.add(pengiriman);
            }
        }
        return hasil;
    }
    
    public List<Pengiriman> filterByStatus(String status) {
        List<Pengiriman> hasil = new ArrayList<>();
        for (Pengiriman pengiriman : daftarPengiriman) {
            if (pengiriman.getStatusPengiriman().equals(status)) {
                hasil.add(pengiriman);
            }
        }
        return hasil;
    }
    public List<Pengiriman> filterByWilayah(String wilayah) {
        List<Pengiriman> hasil = new ArrayList<>();
        for (Pengiriman pengiriman : daftarPengiriman) {
            if (pengiriman.getAsal().equals(wilayah) || pengiriman.getTujuan().equals(wilayah)) {
                hasil.add(pengiriman);
            }
        }
        return hasil;
    }
    public void updateStatusPengiriman(String idResi, String status) {
        Pengiriman pengiriman = cariPengiriman(idResi);
        if (pengiriman != null) {
            pengiriman.setStatusPengiriman(status);
        } else {
            System.out.println("Pengiriman dengan ID Resi " + idResi + " tidak ditemukan.");
        }
    }
    public double hitungTotalPendapatan() {
        double totalPendapatan = 0.0;
        for (Pengiriman pengiriman : daftarPengiriman) {
            if (pengiriman.getTarif() != null) {
                totalPendapatan += pengiriman.getTarif().hitungTarif(pengiriman.getBerat(), pengiriman.getTarif().getJenisLayanan());
            }
        }
        return totalPendapatan;
    }   
}
