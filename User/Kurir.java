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
        clearScreen();
        System.out.println();
        System.out.println(CYAN + BOLD + "                   📦 PENGIRIMAN DI WILAYAH KERJA" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + CYAN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();
        
        System.out.println("     " + BLUE + "📍 WILAYAH KERJA:" + RESET);
        System.out.println("        └─ " + BOLD + this.wilayah.getKota() + ", " + 
                        this.wilayah.getKecamatan() + ", " + this.wilayah.getKelurahan() + RESET);
        System.out.println();
        
        boolean found = false;
        int counter = 1;

        for (Pengiriman p : daftarPengiriman) {
            if (p.getTujuan().getKota().equalsIgnoreCase(this.wilayah.getKota()) 
                && p.getTujuan().getKecamatan().equalsIgnoreCase(this.wilayah.getKecamatan())
                && p.getTujuan().getKelurahan().equalsIgnoreCase(this.wilayah.getKelurahan())) {
                
                if (!found) {
                    System.out.println("     " + GREEN + "📊 Daftar pengiriman di wilayah ini:" + RESET);
                    System.out.println();
                }
                
                String statusColor = getStatusColor(p.getStatusPengiriman());
                String bayarColor = p.getStatusPembayaran().equals("Sudah Dibayar") ? GREEN : RED;
                
                System.out.println("     " + BLUE + "📋 Pengiriman " + counter + ":" + RESET);
                System.out.println("        🆔 ID Resi    : " + YELLOW + BOLD + p.getIdResi() + RESET);
                System.out.println("        👤 Penerima   : " + WHITE + BOLD + p.getPenerima().getNama() + RESET);
                System.out.println("        📱 Kontak     : " + CYAN + p.getPenerima().getNoKontak() + RESET);
                System.out.println("        📦 Barang     : " + WHITE + p.getJenisBarang() + RESET);
                System.out.println("        ⚖️  Berat      : " + WHITE + p.getBerat() + " kg" + RESET);
                System.out.println("        📅 Tanggal    : " + WHITE + formatDate(p.getTanggal()) + RESET);
                System.out.println("        📊 Status     : " + statusColor + BOLD + p.getStatusPengiriman() + RESET);
                System.out.println("        💳 Pembayaran : " + bayarColor + BOLD + p.getStatusPembayaran() + RESET);
                System.out.println("        📍 Alamat     : " + GRAY + p.getTujuan().getKota() + ", " +
                                p.getTujuan().getKecamatan() + ", " + p.getTujuan().getKelurahan() + RESET);
                System.out.println();
                found = true;
                counter++;
            }
        }
        
        if (!found) {
            System.out.println("     " + GRAY + "📭 Tidak ada pengiriman di wilayah ini saat ini." + RESET);
            System.out.println();
        }
        
        System.out.println("    " + CYAN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.print("    " + DIM + "Tekan ENTER untuk kembali ke menu..." + RESET);
        scanner.nextLine();
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
    clearScreen();
    System.out.println();
    System.out.println(YELLOW + BOLD + "                     📈 RIWAYAT LENGKAP KURIR" + RESET);
    System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
    System.out.println("    " + YELLOW + "═══════════════════════════════════════════════════════════════════" + RESET);
    System.out.println();
    
    System.out.println("     " + BLUE + "👤 KURIR: " + WHITE + BOLD + this.nama.toUpperCase() + RESET);
    System.out.println("     " + BLUE + "📍 WILAYAH: " + CYAN + this.wilayah.getKelurahan() + RESET);
    System.out.println();

    // 1. Tampilkan riwayat aktivitas kurir
    tampilkanRiwayatAktivitas();

    // 2. Tampilkan statistik pengiriman
    tampilkanStatistikPengiriman(daftarPengiriman);

    // 3. Tampilkan pengiriman yang sudah ditangani
    tampilkanPengirimanYangDitangani(daftarPengiriman);

    System.out.println("    " + YELLOW + "═══════════════════════════════════════════════════════════════════" + RESET);
    System.out.print("    " + DIM + "Tekan ENTER untuk kembali ke menu..." + RESET);
    scanner.nextLine();
}

public void lihatRiwayat() {
    clearScreen();
    System.out.println();
    System.out.println(YELLOW + BOLD + "                        📈 RIWAYAT KURIR" + RESET);
    System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
    System.out.println("    " + YELLOW + "═══════════════════════════════════════════════════════════════════" + RESET);
    System.out.println();
    
    System.out.println("     " + BLUE + "👤 KURIR: " + WHITE + BOLD + this.nama.toUpperCase() + RESET);
    System.out.println("     " + BLUE + "📍 WILAYAH: " + CYAN + this.wilayah.getKelurahan() + RESET);
    System.out.println();

    // Tampilkan riwayat aktivitas
    tampilkanRiwayatAktivitas();

    System.out.println();
    System.out.println("     " + CYAN + "💡 CATATAN:" + RESET);
    System.out.println("        • Untuk melihat riwayat lengkap termasuk statistik,");
    System.out.println("        • gunakan menu admin atau panggil lihatRiwayat(daftarPengiriman)");
    System.out.println();
    
    System.out.println("    " + YELLOW + "═══════════════════════════════════════════════════════════════════" + RESET);
    System.out.print("    " + DIM + "Tekan ENTER untuk kembali ke menu..." + RESET);
    scanner.nextLine();
}

private void tampilkanRiwayatAktivitas() {
    System.out.println("     " + GREEN + BOLD + "📋 RIWAYAT AKTIVITAS:" + RESET);
    System.out.println("    " + GREEN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

    if (riwayatAktivitas.isEmpty()) {
        System.out.println("        " + GRAY + "📭 Belum ada aktivitas yang tercatat." + RESET);
    } else {
        // Tampilkan 10 aktivitas terakhir
        int startIndex = Math.max(0, riwayatAktivitas.size() - 10);
        for (int i = startIndex; i < riwayatAktivitas.size(); i++) {
            System.out.println("        " + (i + 1) + ". " + WHITE + riwayatAktivitas.get(i) + RESET);
        }

        if (riwayatAktivitas.size() > 10) {
            System.out.println("        " + GRAY + "... dan " + (riwayatAktivitas.size() - 10) + " aktivitas lainnya" + RESET);
        }
    }
    System.out.println();
}

private void tampilkanStatistikPengiriman(List<Pengiriman> daftarPengiriman) {
    System.out.println("     " + PURPLE + BOLD + "📊 STATISTIK PENGIRIMAN:" + RESET);
    System.out.println("    " + PURPLE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

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

    System.out.println("        📦 Total pengiriman di wilayah ini: " + BOLD + totalPengiriman + RESET);
    System.out.println("        " + GREEN + "✅ Sudah diterima: " + BOLD + sudahDiterima + RESET);
    System.out.println("        " + BLUE + "🚚 Dalam pengantaran: " + BOLD + dalamPengantaran + RESET);
    System.out.println("        " + YELLOW + "⏳ Dalam proses: " + BOLD + dalamProses + RESET);

    // Hitung persentase keberhasilan
    if (totalPengiriman > 0) {
        double persentaseSelesai = (double) sudahDiterima / totalPengiriman * 100;
        System.out.printf("        " + CYAN + "📈 Tingkat keberhasilan: " + BOLD + "%.1f%%" + RESET + "\n", persentaseSelesai);
    }
    System.out.println();
}

private void tampilkanPengirimanYangDitangani(List<Pengiriman> daftarPengiriman) {
    System.out.println("     " + CYAN + BOLD + "📦 PENGIRIMAN YANG SUDAH DITANGANI:" + RESET);
    System.out.println("    " + CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);    boolean found = false;
    int counter = 1;

    for (Pengiriman p : daftarPengiriman) {
        if (p.getTujuan().getKota().equalsIgnoreCase(this.wilayah.getKota()) 
            && p.getTujuan().getKecamatan().equalsIgnoreCase(this.wilayah.getKecamatan())
            && p.getTujuan().getKelurahan().equalsIgnoreCase(this.wilayah.getKelurahan())) {
            
            String statusColor = getStatusColor(p.getStatusPengiriman());
            
            System.out.println("        " + counter + ". " + BOLD + "ID Resi: " + YELLOW + p.getIdResi() + RESET);
            System.out.println("           👤 Penerima: " + WHITE + p.getPenerima().getNama() + RESET);
            System.out.println("           📅 Tanggal: " + GRAY + formatDate(p.getTanggal()) + RESET);
            System.out.println("           📊 Status: " + statusColor + p.getStatusPengiriman() + RESET);
            System.out.println();
            found = true;
            counter++;

            // Batasi tampilan maksimal 5 pengiriman terakhir
            if (counter > 5) {
                System.out.println("        " + GRAY + "... dan pengiriman lainnya" + RESET);
                break;
            }
        }
    }
    if (!found) {
        System.out.println("        " + GRAY + "📭 Belum ada pengiriman yang selesai ditangani." + RESET);
    }
    System.out.println();
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
    clearScreen();
    System.out.println();
    
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
            System.out.println(RED + "❌ Tipe periode tidak valid. Gunakan: harian, mingguan, atau bulanan" + RESET);
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

    // Display the header with improved styling
    String warnaPeriode = tipePeriode.equalsIgnoreCase("harian") ? GREEN : 
                         tipePeriode.equalsIgnoreCase("mingguan") ? BLUE : PURPLE;
    
    System.out.println(warnaPeriode + BOLD + "                    📊 RIWAYAT " + tipePeriode.toUpperCase() + " KURIR" + RESET);
    System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
    System.out.println("    " + warnaPeriode + "═══════════════════════════════════════════════════════════════════" + RESET);
    System.out.println();
    
    System.out.println("     " + BLUE + "👤 KURIR: " + WHITE + BOLD + this.nama.toUpperCase() + RESET);
    System.out.println("     " + BLUE + "📅 PERIODE: " + CYAN + BOLD + judulPeriode + RESET);
    System.out.println("     " + BLUE + "📍 WILAYAH: " + CYAN + this.wilayah.getKelurahan() + RESET);
    System.out.println();

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

    System.out.println("    " + warnaPeriode + "═══════════════════════════════════════════════════════════════════" + RESET);
    System.out.print("    " + DIM + "Tekan ENTER untuk kembali ke menu..." + RESET);
    scanner.nextLine();
}

    private void tampilkanDetailPengiriman(List<Pengiriman> pengirimanList, String tipePeriode) {
    System.out.println("     " + CYAN + BOLD + "📦 PENGIRIMAN " + tipePeriode.toUpperCase() + ":" + RESET);
    System.out.println("    " + CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    if (pengirimanList.isEmpty()) {
        System.out.println("        " + GRAY + "📭 Tidak ada pengiriman pada periode ini." + RESET);
        System.out.println();
        return;
    }

    // Sort deliveries by date (newest first)
    pengirimanList.sort((p1, p2) -> p2.getTanggal().compareTo(p1.getTanggal()));

    int count = 1;
    for (Pengiriman p : pengirimanList) {
        if (count > 10) {
            System.out.println("        " + GRAY + "... dan " + (pengirimanList.size() - 10) + " pengiriman lainnya." + RESET);
            break;
        }

        String statusColor = getStatusColor(p.getStatusPengiriman());
        String bayarColor = p.getStatusPembayaran().equals("Sudah Dibayar") ? GREEN : RED;

        System.out.println("        " + count + ". " + BOLD + "ID Resi: " + YELLOW + p.getIdResi() + RESET);
        System.out.println("           👤 Penerima: " + WHITE + p.getPenerima().getNama() + RESET);
        System.out.println("           📍 Alamat: " + GRAY + p.getTujuan().getKota() + ", " +
                           p.getTujuan().getKecamatan() + ", " + p.getTujuan().getKelurahan() + RESET);
        System.out.println("           🕐 Waktu: " + CYAN + dateFormat.format(p.getTanggal()) + RESET);
        System.out.println("           📊 Status: " + statusColor + p.getStatusPengiriman() + RESET);
        System.out.println("           💳 Pembayaran: " + bayarColor + p.getStatusPembayaran() + RESET);
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
            rataRataPerHari = (double) totalSelesai / 30;
        }
        
        System.out.printf("        " + PURPLE + "📊 Rata-rata pengiriman selesai: " + BOLD + "%.1f per hari" + RESET + "\n", rataRataPerHari);
        System.out.println();
    }
}

    private String getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "dalam proses":
                return BLUE;
            case "dalam pengantaran":
                return YELLOW;
            case "sudah diterima":
                return GREEN;
            case "gagal dikirim":
                return RED;
            default:
                return WHITE;
        }
    }

    // Tambahkan konstanta DIM jika belum ada
    public static final String DIM = "\033[2m";

    // Pastikan ada scanner import
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);



    public void tampilkanMenu() {
        clearScreen();
        System.out.println();
        System.out.println(GREEN + BOLD + "                        🚚 DASHBOARD KURIR" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();
        
        // Informasi kurir dan statistik
        System.out.println("     " + BLUE + "👤 INFORMASI KURIR:" + RESET);
        System.out.println("        └─ Nama       : " + WHITE + BOLD + this.nama + RESET);
        System.out.println("        └─ ID Kurir   : " + YELLOW + BOLD + this.idKurir + RESET);
        System.out.println("        └─ Wilayah    : " + CYAN + this.wilayah.getKota() + ", " + 
                        this.wilayah.getKecamatan() + ", " + this.wilayah.getKelurahan() + RESET);
        System.out.println("        └─ Aktivitas  : " + GREEN + BOLD + this.riwayatAktivitas.size() + " kegiatan" + RESET);
        System.out.println();
        
        // Menu options
        System.out.println("       " + CYAN + BOLD + "1." + RESET + "  " + WHITE + BOLD + "Lihat Pengiriman di Wilayah" + RESET);
        System.out.println("           " + GRAY + "→ Tampilkan pengiriman di area kerja" + RESET);
        System.out.println();
        
        System.out.println("       " + BLUE + BOLD + "2." + RESET + "  " + WHITE + BOLD + "Update Status Pengiriman" + RESET);
        System.out.println("           " + GRAY + "→ Ubah status paket dalam pengantaran" + RESET);
        System.out.println();
        
        System.out.println("       " + PURPLE + BOLD + "3." + RESET + "  " + WHITE + BOLD + "Update Status Pembayaran" + RESET);
        System.out.println("           " + GRAY + "→ Konfirmasi pembayaran COD" + RESET);
        System.out.println();
        
        System.out.println("       " + YELLOW + BOLD + "4." + RESET + "  " + WHITE + BOLD + "Lihat Riwayat Aktivitas" + RESET);
        System.out.println("           " + GRAY + "→ Riwayat lengkap kegiatan kurir" + RESET);
        System.out.println();
        
        System.out.println("       " + GREEN + BOLD + "5." + RESET + "  " + WHITE + BOLD + "Laporan Periode" + RESET);
        System.out.println("           " + GRAY + "→ Laporan harian/mingguan/bulanan" + RESET);
        System.out.println();
        
        System.out.println("       " + RED + BOLD + "6." + RESET + "  " + WHITE + BOLD + "Logout" + RESET);
        System.out.println("           " + GRAY + "→ Keluar dari sistem" + RESET);
        System.out.println();
        
        System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.print("    " + YELLOW + BOLD + "⚡ Pilihan Anda [1-6]: " + RESET);
    }

    // Tambahkan konstanta warna jika belum ada
    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";
    public static final String GRAY = "\033[90m";

    // Method helper untuk clear screen
    private void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }
}
