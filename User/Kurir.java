package User;

import Pengiriman.Pengiriman;
import Pengiriman.Wilayah;
import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Kurir extends User {
    private String nama;
    private String idKurir;
    private String password;
    private Wilayah wilayah;
    private List<String> riwayatAktivitas; // Untuk menyimpan riwayat aktivitas kurir


    public Kurir(String nama, String idKurir, String password, Wilayah wilayah)
    {
        this.nama = nama;
        this.idKurir = idKurir;
        this.password = password;
        this.wilayah = wilayah;
        this.riwayatAktivitas = new ArrayList<>();
    }

    // Setter
    public void setNama(String nama)
    {
        this.nama = nama;
    }
    public void setIdKurir(String idKurir)
    {
        this.idKurir = idKurir;
    }
    public void setWilayah(Wilayah wilayah)
    {
        this.wilayah = wilayah;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Getter
    public String getNama()
    {
        return nama;
    }
    public String getIdKurir()
    {
        return idKurir;
    }
    public Wilayah getWilayah()
    {
        return wilayah;
    }
    public String getPassword() {
        return password;
    }
    public void setWilayah(Wilayah wilayah, boolean overwrite)
    {
        if (overwrite || this.wilayah == null) {
            this.wilayah = wilayah;
        }
    }
    public List<String> getRiwayatAktivitas() {
        return riwayatAktivitas;
    }

    public void lihatPengirimanWilayah(List<Pengiriman> daftarPengiriman) {
        System.out.println("\n=== PENGIRIMAN DI WILAYAH " + this.wilayah.getKelurahan() + " ===");
        boolean found = false;
    
        for (Pengiriman p : daftarPengiriman) {
            if (p.getTujuan().getKota().equalsIgnoreCase(this.wilayah.getKota()) 
                && p.getTujuan().getKecamatan().equalsIgnoreCase(this.wilayah.getKecamatan())
                && p.getTujuan().getKelurahan().equalsIgnoreCase(this.wilayah.getKelurahan())) {
                
    
                System.out.println("ID Resi: " + p.getIdResi());
                System.out.println("Penerima: " + p.getPenerima().getNama());
                System.out.println("Alamat: " + p.getTujuan().getKota() + ", " +
                                   p.getTujuan().getKecamatan() + ", " + p.getTujuan().getKelurahan());
                System.out.println("Status: " + p.getStatusPengiriman());
                System.out.println("-----------------------------");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Tidak ada pengiriman di wilayah ini.");
        }
    }

    public void updateStatus(Pengiriman pengiriman, String status) {
        if (pengiriman != null) {
            String statusLama = pengiriman.getStatusPengiriman();
            pengiriman.updateStatusPengiriman(status);

            // Catat aktivitas ke riwayat
            String aktivitas = getCurrentTimestamp() + " - Mengupdate status pengiriman " +
                    pengiriman.getIdResi() + " dari '" + statusLama + "' menjadi '" + status + "'";
            riwayatAktivitas.add(aktivitas);

            System.out.println("Kurir " + this.nama + " mengupdate status pengiriman " +
                    pengiriman.getIdResi() + " menjadi: " + status);
        }
    }

    public void updateStatusBayar(Pengiriman pengiriman, String status) {
        if (pengiriman != null) {
            String statusLama = pengiriman.getStatusPembayaran();
            pengiriman.updateStatusPembayaran(status);

            // Catat aktivitas ke riwayat
            String aktivitas = getCurrentTimestamp() + " - Mengupdate status pembayaran " +
                    pengiriman.getIdResi() + " dari '" + statusLama + "' menjadi '" + status + "'";
            riwayatAktivitas.add(aktivitas);

            System.out.println("Kurir " + this.nama + " mengupdate status pembayaran " +
                    pengiriman.getIdResi() + " menjadi: " + status);
        }
    }

    /**
     * Method untuk melihat riwayat lengkap aktivitas kurir
     * @param daftarPengiriman - daftar semua pengiriman untuk filter berdasarkan kurir
     */
    public void lihatRiwayat(List<Pengiriman> daftarPengiriman) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           RIWAYAT LENGKAP KURIR " + this.nama.toUpperCase());
        System.out.println("                  Wilayah: " + this.wilayah.getKelurahan());
        System.out.println("=".repeat(60));

        // 1. Tampilkan riwayat aktivitas kurir
        tampilkanRiwayatAktivitas();

        // 2. Tampilkan statistik pengiriman
        tampilkanStatistikPengiriman(daftarPengiriman);

        // 3. Tampilkan pengiriman yang sudah ditangani
        tampilkanPengirimanYangDitangani(daftarPengiriman);

        System.out.println("=".repeat(60));
    }

    /**
     * Overload method lihatRiwayat() untuk kompatibilitas dengan kode yang sudah ada
     */
    public void lihatRiwayat() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        RIWAYAT KURIR " + this.nama.toUpperCase());
        System.out.println("           Wilayah: " + this.wilayah.getKelurahan());
        System.out.println("=".repeat(50));

        // Tampilkan riwayat aktivitas
        tampilkanRiwayatAktivitas();

        System.out.println("=".repeat(50));
        System.out.println("Note: Untuk melihat riwayat lengkap termasuk statistik,");
        System.out.println("      gunakan menu admin atau panggil lihatRiwayat(daftarPengiriman)");
        System.out.println("=".repeat(50));
    }

    /**
     * Method untuk menampilkan riwayat aktivitas kurir
     */
    private void tampilkanRiwayatAktivitas() {
        System.out.println("\n📋 RIWAYAT AKTIVITAS:");
        System.out.println("-".repeat(50));

        if (riwayatAktivitas.isEmpty()) {
            System.out.println("   Belum ada aktivitas yang tercatat.");
        } else {
            // Tampilkan 10 aktivitas terakhir
            int startIndex = Math.max(0, riwayatAktivitas.size() - 10);
            for (int i = startIndex; i < riwayatAktivitas.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + riwayatAktivitas.get(i));
            }

            if (riwayatAktivitas.size() > 10) {
                System.out.println("   ... dan " + (riwayatAktivitas.size() - 10) + " aktivitas lainnya");
            }
        }
    }

    /**
     * Method untuk menampilkan statistik pengiriman
     */
    private void tampilkanStatistikPengiriman(List<Pengiriman> daftarPengiriman) {
        System.out.println("\n📊 STATISTIK PENGIRIMAN:");
        System.out.println("-".repeat(50));

        int totalPengiriman = 0;
        int sudahDiterima = 0;
        int dalamProses = 0;
        int dalamPengantaran = 0;

        for (Pengiriman p : daftarPengiriman) {
            if (p.getTujuan().getKota().equalsIgnoreCase(this.wilayah.getKota()) 
                && p.getTujuan().getKecamatan().equalsIgnoreCase(this.wilayah.getKecamatan())
                && p.getTujuan().getKelurahan().equalsIgnoreCase(this.wilayah.getKelurahan())) {
                totalPengiriman++;

                String status = p.getStatusPengiriman();
                if (status.equalsIgnoreCase("Sudah Diterima")) {
                    sudahDiterima++;
                } else if (status.equalsIgnoreCase("Dalam Proses")) {
                    dalamProses++;
                } else if (status.equalsIgnoreCase("Dalam Pengantaran")) {
                    dalamPengantaran++;
                }
            }
        }

        System.out.println("   Total pengiriman di wilayah ini: " + totalPengiriman);
        System.out.println("   ✅ Sudah diterima: " + sudahDiterima);
        System.out.println("   🚚 Dalam pengantaran: " + dalamPengantaran);
        System.out.println("   ⏳ Dalam proses: " + dalamProses);

        // Hitung persentase keberhasilan
        if (totalPengiriman > 0) {
            double persentaseSelesai = (double) sudahDiterima / totalPengiriman * 100;
            System.out.printf("   📈 Tingkat keberhasilan: %.1f%%\n", persentaseSelesai);
        }
    }

    /**
     * Method untuk menampilkan detail pengiriman yang sudah ditangani
     */
    private void tampilkanPengirimanYangDitangani(List<Pengiriman> daftarPengiriman) {
        System.out.println("\n📦 PENGIRIMAN YANG SUDAH DITANGANI:");
        System.out.println("-".repeat(50));

        boolean found = false;
        int counter = 1;

        for (Pengiriman p : daftarPengiriman) {
            if (p.getTujuan().getKota().equalsIgnoreCase(this.wilayah.getKota()) 
                && p.getTujuan().getKecamatan().equalsIgnoreCase(this.wilayah.getKecamatan())
                && p.getTujuan().getKelurahan().equalsIgnoreCase(this.wilayah.getKelurahan())) {
                
                System.out.println("   " + counter + ". ID Resi: " + p.getIdResi());
                System.out.println("      Penerima: " + p.getPenerima().getNama());
                System.out.println("      Tanggal: " + formatDate(p.getTanggal()));
                System.out.println("      Status: " + p.getStatusPengiriman());
                System.out.println();
                found = true;
                counter++;

                // Batasi tampilan maksimal 5 pengiriman terakhir
                if (counter > 5) {
                    System.out.println("   ... dan pengiriman lainnya");
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("   Belum ada pengiriman yang selesai ditangani.");
        }
    }

    /**
     * Method untuk mendapatkan timestamp saat ini
     */
    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * Method untuk memformat tanggal
     */
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    /**
     * Method untuk menambah aktivitas manual ke riwayat
     */
    public void tambahAktivitas(String aktivitas) {
        String aktivitasLengkap = getCurrentTimestamp() + " - " + aktivitas;
        riwayatAktivitas.add(aktivitasLengkap);
    }

    /**
     * Method untuk melihat riwayat dengan filter periode
     */
    public void lihatRiwayatByPeriode(List<Pengiriman> daftarPengiriman, Date tanggalAwal, Date tanggalAkhir) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    RIWAYAT KURIR " + this.nama.toUpperCase() + " PERIODE TERTENTU");
        System.out.println("    Periode: " + formatDate(tanggalAwal) + " - " + formatDate(tanggalAkhir));
        System.out.println("    Wilayah: " + this.wilayah.getKelurahan());
        System.out.println("=".repeat(60));

        // Filter pengiriman berdasarkan periode
        List<Pengiriman> pengirimanPeriode = new ArrayList<>();
        for (Pengiriman p : daftarPengiriman) {
            if (p.getTujuan().getKota().equalsIgnoreCase(this.wilayah.getKota()) 
                && p.getTujuan().getKecamatan().equalsIgnoreCase(this.wilayah.getKecamatan())
                && p.getTujuan().getKelurahan().equalsIgnoreCase(this.wilayah.getKelurahan()) &&
                    p.getTanggal().compareTo(tanggalAwal) >= 0 &&
                    p.getTanggal().compareTo(tanggalAkhir) <= 0) {
                pengirimanPeriode.add(p);
            }
        }

        tampilkanStatistikPengiriman(pengirimanPeriode);
        tampilkanPengirimanYangDitangani(pengirimanPeriode);

        System.out.println("=".repeat(60));
    }


    public void lihatRiwayatPeriode(List<Pengiriman> daftarPengiriman, String tipePeriode) {
        // Default ke tanggal saat ini jika tidak ada tanggal yang ditentukan
        lihatRiwayatPeriode(daftarPengiriman, tipePeriode, new Date());
    }

    public void lihatRiwayatPeriode(List<Pengiriman> daftarPengiriman, String tipePeriode, Date tanggalReferensi) {
        Date tanggalAwal = null;
        Date tanggalAkhir = null;
        Calendar cal = Calendar.getInstance();

        // Menetapkan tanggal referensi jika tidak diberikan
        if (tanggalReferensi == null) {
            tanggalReferensi = new Date();
        }
        cal.setTime(tanggalReferensi);

        // Menghitung tanggal awal dan akhir berdasarkan tipe periode
        switch (tipePeriode.toLowerCase()) {
            case "harian":
                // For daily - start and end of the same day
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                tanggalAwal = cal.getTime();

                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                tanggalAkhir = cal.getTime();
                break;

            case "mingguan":
                cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                tanggalAwal = cal.getTime();

                cal.add(Calendar.DAY_OF_WEEK, 6);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                tanggalAkhir = cal.getTime();
                break;

            case "bulanan":
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                tanggalAwal = cal.getTime();

                cal.add(Calendar.MONTH, 1);
                cal.add(Calendar.DATE, -1);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                tanggalAkhir = cal.getTime();
                break;

            default:
                System.out.println("Tipe periode tidak valid. Gunakan: harian, mingguan, atau bulanan");
                return;
        }

        // Format the period title
        String judulPeriode;
        SimpleDateFormat sdfHari = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat sdfMinggu = new SimpleDateFormat("dd MMM");
        SimpleDateFormat sdfBulan = new SimpleDateFormat("MMMM yyyy");

        switch (tipePeriode.toLowerCase()) {
            case "harian":
                judulPeriode = sdfHari.format(tanggalReferensi);
                break;
            case "mingguan":
                judulPeriode = sdfMinggu.format(tanggalAwal) + " - " + sdfMinggu.format(tanggalAkhir);
                break;
            case "bulanan":
                judulPeriode = sdfBulan.format(tanggalReferensi);
                break;
            default:
                judulPeriode = "Periode Tidak Valid";
        }

        // Display the header
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    RIWAYAT " + tipePeriode.toUpperCase() + " KURIR " + this.nama.toUpperCase());
        System.out.println("    Periode: " + judulPeriode);
        System.out.println("    Wilayah: " + this.wilayah.getKelurahan());
        System.out.println("=".repeat(60));

        // Filter deliveries for the period
        List<Pengiriman> pengirimanPeriode = new ArrayList<>();
        for (Pengiriman p : daftarPengiriman) {
            if (p.getTujuan().getKota().equalsIgnoreCase(this.wilayah.getKota()) 
                && p.getTujuan().getKecamatan().equalsIgnoreCase(this.wilayah.getKecamatan())
                && p.getTujuan().getKelurahan().equalsIgnoreCase(this.wilayah.getKelurahan()) &&
                    p.getTanggal().compareTo(tanggalAwal) >= 0 &&
                    p.getTanggal().compareTo(tanggalAkhir) <= 0) {
                pengirimanPeriode.add(p);
            }
        }

        // Display statistics and delivery details
        tampilkanStatistikPengiriman(pengirimanPeriode);
        tampilkanDetailPengiriman(pengirimanPeriode, tipePeriode);

        System.out.println("=".repeat(60));
    }

    private void tampilkanDetailPengiriman(List<Pengiriman> pengirimanList, String tipePeriode) {
        System.out.println("\n📦 PENGIRIMAN " + tipePeriode.toUpperCase() + ":");
        System.out.println("-".repeat(50));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if (pengirimanList.isEmpty()) {
            System.out.println("   Tidak ada pengiriman pada periode ini.");
            return;
        }

        // Sort deliveries by date (newest first)
        pengirimanList.sort((p1, p2) -> p2.getTanggal().compareTo(p1.getTanggal()));

        int count = 1;
        for (Pengiriman p : pengirimanList) {
            if (count > 10) {
                System.out.println("   ... dan " + (pengirimanList.size() - 10) + " pengiriman lainnya.");
                break;
            }

            System.out.println("   " + count + ". ID Resi: " + p.getIdResi());
            System.out.println("      Penerima: " + p.getPenerima().getNama());
            System.out.println("      Alamat: " + p.getTujuan().getKota() + ", " +
                               p.getTujuan().getKecamatan() + ", " + p.getTujuan().getKelurahan());
            System.out.println("      Waktu: " + dateFormat.format(p.getTanggal()));
            System.out.println("      Status: " + p.getStatusPengiriman());
            System.out.println("      Pembayaran: " + p.getStatusPembayaran());
            System.out.println();
            count++;
        }

        // Calculate and display additional statistics for weekly and monthly views
        if (!tipePeriode.equalsIgnoreCase("harian") && !pengirimanList.isEmpty()) {
            int totalSelesai = 0;
            for (Pengiriman p : pengirimanList) {
                if (p.getStatusPengiriman().equalsIgnoreCase("Sudah Diterima")) {
                    totalSelesai++;
                }
            }

            double rataRataPerHari = 0;
            if (tipePeriode.equalsIgnoreCase("mingguan")) {
                rataRataPerHari = (double) totalSelesai / 7;
            } else if (tipePeriode.equalsIgnoreCase("bulanan")) {
                // Approximate to 30 days per month
                rataRataPerHari = (double) totalSelesai / 30;
            }

            System.out.printf("   📊 Rata-rata pengiriman selesai: %.1f per hari\n", rataRataPerHari);
        }
    }



    public void tampilkanMenu() {
        System.out.println("Menu Kurir");
    }
}
