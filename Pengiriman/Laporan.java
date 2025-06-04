package Pengiriman;
import java.util.Date;
import java.util.List;


public class Laporan {
    private Date tanggalAwal;
    private Date tanggalAkhir;
    private PengelolaPengiriman pengelola;

    public Laporan(Date tanggalAwal, Date tanggalAkhir, PengelolaPengiriman pengelola){
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.pengelola = pengelola;
    }

    public PengelolaPengiriman getPengelola() {
        return pengelola;
    }
    public void setPengelola(PengelolaPengiriman pengelola) {
        this.pengelola = pengelola;
    }
    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }
    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }
    public Date getTanggalAwal() {
        return tanggalAwal;
    }
    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }
    
    public List<Pengiriman> getPengirimanDalamRentang() {
        return pengelola.getPengirimanDalamRentangWaktu(tanggalAwal, tanggalAkhir);
    }
    public List<Pengiriman> filterByStatusPengiriman(String status) {
        return pengelola.filterByStatus(status);
    }
    public List<Pengiriman> filterByWilayah(String status) {
        return pengelola.filterByWilayah(status);
    }

    public String tampilkanLaporan() {
        StringBuilder laporan = new StringBuilder();
        laporan.append("=== LAPORAN PENGIRIMAN ===\n");
        laporan.append("Periode: ").append(tanggalAwal).append(" hingga ").append(tanggalAkhir).append("\n");
        laporan.append("Total Pendapatan: Rp ").append(pengelola.hitungTotalPendapatan()).append("\n\n");
        
        laporan.append("=== DETAIL PENGIRIMAN ===\n");
        List<Pengiriman> pengirimans = getPengirimanDalamRentang();
        for (Pengiriman p : pengirimans) {
            laporan.append("ID Resi: ").append(p.getIdResi()).append("\n");
            laporan.append("Tanggal: ").append(p.getTanggal()).append("\n");
            laporan.append("Pengirim: ").append(p.getPengirim().getNama()).append("\n");
            laporan.append("Penerima: ").append(p.getPenerima().getNama()).append("\n");
            laporan.append("Berat: ").append(p.getBerat()).append(" kg\n");
            laporan.append("Jenis Layanan: ").append(p.getJenisLayanan()).append("\n");
            laporan.append("Tarif: Rp ").append(p.getTarif().hitungTarif(p.getBerat(),p.getJenisLayanan())).append("\n");
            laporan.append("Asal: ").append(p.getAsal()).append("\n");
            laporan.append("Tujuan: ").append(p.getTujuan().getKota() + ", " + p.getTujuan().getKecamatan() + ", " + p.getTujuan().getKelurahan()) .append("\n");
            laporan.append("Status Pengiriman: ").append(p.getStatusPengiriman()).append("\n");
            laporan.append("Status Pembayaran: ").append(p.getStatusPembayaran()).append("\n");
            laporan.append("----------------------------\n");
        }
        
        return laporan.toString();
    }

}
