package User;

import Pengiriman.*;
import Pelanggan.*;
import java.util.*;
import static Main.Main.*;
import Main.Main;

public class Admin extends User {
    private String nama;
    private static List<Kurir> daftarKurir = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public Admin(String nama) {
        this.nama = nama;
    }

    // Method untuk login admin
    public static Admin loginAdmin() {
        System.out.println("\n=== LOGIN ADMIN ===");
        System.out.print("Masukkan username admin: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        // Validasi sederhana (bisa diganti dengan autentikasi database)
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println("Login berhasil!");
            return new Admin(username);
        } else {
            System.out.println("Username atau password salah!");
            return null;
        }
    }

    public static List<Kurir> getDaftarKurir() {
        return daftarKurir;
    }

    // Method untuk login kurir (dipanggil dari admin)
    public static Kurir loginKurir() {
        System.out.println("\n=== LOGIN KURIR ===");
        System.out.print("Masukkan ID Kurir: ");
        String idKurir = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        // Cari kurir di daftar kurir
        for (Kurir kurir : daftarKurir) {
            if (idKurir.equals(kurir.getIdKurir()) && password.equals(kurir.getPassword())) {
                System.out.println("Login berhasil sebagai Kurir " + kurir.getNama());
                return kurir;
            }
        }
        
        System.out.println("ID Kurir dan password salah!");
        return null;
    }

    public void kelolaKurir() {
        while (true) {
            clearScreen();
            System.out.println();
            System.out.println(CYAN + BOLD + "                        👥 KELOLA KURIR" + RESET);
            System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
            System.out.println("    " + CYAN + "═══════════════════════════════════════════════════════════════════" + RESET);
            System.out.println();

            // Statistik kurir
            System.out.println("     " + BLUE + "📊 STATISTIK:" + RESET);
            System.out.println("        └─ Total Kurir Terdaftar: " + GREEN + BOLD + daftarKurir.size() + " orang" + RESET);
            System.out.println();

            // Menu options
            System.out.println("       " + GREEN + BOLD + "1." + RESET + "  " + BOLD + "Tambah Kurir Baru" + RESET);
            System.out.println("           " + GRAY + "→ Daftarkan kurir ke sistem" + RESET);
            System.out.println();

            System.out.println("       " + CYAN + BOLD + "2." + RESET + "  " + BOLD + "Lihat Daftar Kurir" + RESET);
            System.out.println("           " + GRAY + "→ Tampilkan semua kurir" + RESET);
            System.out.println();

            System.out.println("       " + PURPLE + BOLD + "3." + RESET + "  " + BOLD + "Edit Data Kurir" + RESET);
            System.out.println("           " + GRAY + "→ Ubah informasi kurir" + RESET);
            System.out.println();

            System.out.println("       " + RED + BOLD + "4." + RESET + "  " + BOLD + "Hapus Kurir" + RESET);
            System.out.println("           " + GRAY + "→ Hapus kurir dari sistem" + RESET);
            System.out.println();

            System.out.println("       " + YELLOW + BOLD + "5." + RESET + "  " + BOLD + "Cari Kurir" + RESET);
            System.out.println("           " + GRAY + "→ Cari berdasarkan ID/nama" + RESET);
            System.out.println();

            System.out.println("       " + BLUE + BOLD + "6." + RESET + "  " + BOLD + "Kembali ke Dashboard" + RESET);
            System.out.println("           " + GRAY + "→ Menu admin utama" + RESET);
            System.out.println();

            System.out.println("    " + CYAN + "═══════════════════════════════════════════════════════════════════" + RESET);
            System.out.print("    " + YELLOW + BOLD + "⚡ Pilihan Anda [1-6]: " + RESET);

            String input = scanner.nextLine();

            try {
                int pilihan = Integer.parseInt(input);

                switch (pilihan) {
                    case 1:
                        tambahKurir();
                        break;
                    case 2:
                        lihatDaftarKurir();
                        break;
                    case 3:
                        editKurir();
                        break;
                    case 4:
                        hapusKurir();
                        break;
                    case 5:
                        cariKurir();
                        break;
                    case 6:
                        return;
                    default:
                        showMessage("❌ Pilihan harus antara 1-6! Silakan coba lagi.", "error");
                }
            } catch (NumberFormatException e) {
                showMessage("❌ Input harus berupa angka (1-6)! Silakan coba lagi.", "error");
            }
        }
    }

    // Update method tambahKurir() untuk styling yang lebih baik
    public void tambahKurir() {
        clearScreen();
        System.out.println();
        System.out.println(GREEN + BOLD + "                      ➕ TAMBAH KURIR BARU" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();

        System.out.println("     " + CYAN + "📝 Masukkan data kurir baru:" + RESET);
        System.out.println();

        System.out.print("     👤 Nama Lengkap      : ");
        String nama = scanner.nextLine();

        System.out.print("     🆔 ID Kurir          : ");
        String idKurir = scanner.nextLine();

        System.out.print("     🔑 Password          : ");
        String password = scanner.nextLine();

        System.out.println();
        System.out.println("     " + BLUE + "📍 Wilayah Kurir:" + RESET);
        System.out.print("     🏙️  Kota              : ");
        String kotaKurir = scanner.nextLine();

        System.out.print("     🏘️  Kecamatan         : ");
        String kecamatanKurir = scanner.nextLine();

        System.out.print("     🏠 Kelurahan         : ");
        String kelurahanKurir = scanner.nextLine();

        Wilayah wilayahBaru = new Wilayah(kotaKurir, kecamatanKurir, kelurahanKurir);

        if (isWilayahSudahAda(wilayahBaru)) {
            System.out.println();
            showMessage("❌ Gagal menambahkan kurir: Sudah ada kurir di wilayah " +
                    kotaKurir + ", " + kecamatanKurir + ", " + kelurahanKurir, "error");
            return;
        }

        System.out.println();
        System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();

        // Konfirmasi data
        System.out.println("     " + YELLOW + "🔍 KONFIRMASI DATA:" + RESET);
        System.out.println("        Nama     : " + BOLD + nama + RESET);
        System.out.println("        ID       : " + BOLD + idKurir + RESET);
        System.out.println("        Password : " + BOLD + "*".repeat(password.length()) + RESET);
        System.out.println("        Wilayah  : " + BOLD + kotaKurir + ", " + kecamatanKurir + ", " + kelurahanKurir + RESET);
        System.out.println();

        System.out.print("     " + GREEN + "✓ Simpan data kurir ini? [y/n]: " + RESET);
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("y") || konfirmasi.equalsIgnoreCase("yes")) {
            // Tambahkan kurir baru
            Kurir kurirBaru = new Kurir(nama, idKurir, password, wilayahBaru);
            Admin.getDaftarKurir().add(kurirBaru);
            showMessage("✅ Kurir " + nama + " berhasil ditambahkan ke sistem!", "success");
        } else {
            showMessage("⚠️ Penambahan kurir dibatalkan.", "warning");
        }
    }


    private void lihatDaftarKurir() {
        clearScreen();
        System.out.println();
        System.out.println(CYAN + BOLD + "                       📋 DAFTAR KURIR" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + CYAN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();

        if (daftarKurir.isEmpty()) {
            System.out.println("     " + GRAY + "📭 Belum ada kurir yang terdaftar dalam sistem." + RESET);
            System.out.println();
            pauseScreen();
            return;
        }

        System.out.println("     " + BLUE + "📊 Total: " + daftarKurir.size() + " kurir terdaftar" + RESET);
        System.out.println();

        // Tampilkan daftar kurir dengan styling yang lebih baik
        int counter = 1;
        for (Kurir k : daftarKurir) {
            System.out.println("     " + CYAN + "📋 Kurir " + counter + ":" + RESET);
            System.out.println("        👤 Nama    : " + WHITE + BOLD + k.getNama() + RESET);
            System.out.println("        🆔 ID      : " + YELLOW + BOLD + k.getIdKurir() + RESET);
            System.out.println("        📍 Wilayah : " + GREEN + k.getWilayah().getKota() + ", " +
                    k.getWilayah().getKecamatan() + ", " + k.getWilayah().getKelurahan() + RESET);
            System.out.println("        📊 Status  : " + GREEN + "Aktif" + RESET);
            System.out.println();
            counter++;
        }

        System.out.println("    " + CYAN + "═══════════════════════════════════════════════════════════════════" + RESET);
        pauseScreen();
    }

    private void pauseScreen() {
        System.out.println("    " + DIM + "Tekan ENTER untuk kembali ke menu..." + RESET);
        scanner.nextLine();
    }

    private void editKurir() {
        clearScreen();
        System.out.println();
        System.out.println(PURPLE + BOLD + "                      ✏️ EDIT DATA KURIR" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + PURPLE + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();

        System.out.print("     " + WHITE + "🆔 Masukkan ID Kurir yang akan diedit: " + RESET);
        String idKurir = scanner.nextLine();

        Kurir kurir = cariKurirById(idKurir);
        if (kurir == null) {
            showMessage("❌ Kurir dengan ID '" + idKurir + "' tidak ditemukan!", "error");
            return;
        }

        System.out.println();
        System.out.println("     " + GREEN + "✅ Kurir ditemukan:" + RESET);
        System.out.println("        👤 Nama    : " + BOLD + kurir.getNama() + RESET);
        System.out.println("        🆔 ID      : " + BOLD + kurir.getIdKurir() + RESET);
        System.out.println("        📍 Wilayah : " + BOLD + kurir.getWilayah().getKota() + ", " +
                kurir.getWilayah().getKecamatan() + ", " + kurir.getWilayah().getKelurahan() + RESET);
        System.out.println();

        System.out.println("     " + CYAN + "📝 Masukkan data baru (kosongkan jika tidak ingin mengubah):" + RESET);
        System.out.println();

        System.out.print("     👤 Nama baru          : ");
        String namaBaru = scanner.nextLine();
        if (!namaBaru.isEmpty()) {
            kurir.setNama(namaBaru);
        }

        System.out.print("     🔑 Password baru      : ");
        String passwordBaru = scanner.nextLine();
        if (!passwordBaru.isEmpty()) {
            kurir.setPassword(passwordBaru);
        }

        System.out.println();
        System.out.println("     " + BLUE + "📍 Wilayah Baru Kurir:" + RESET);
        System.out.print("     🏙️  Kota (Baru)       : ");
        String kotaKurirBaru = scanner.nextLine();

        System.out.print("     🏘️  Kecamatan (Baru)  : ");
        String kecamatanKurirBaru = scanner.nextLine();

        System.out.print("     🏠 Kelurahan (Baru)  : ");
        String kelurahanKurirBaru = scanner.nextLine();

        // Buat objek wilayah baru untuk validasi
        Wilayah wilayahBaru = new Wilayah(
                kotaKurirBaru.isEmpty() ? kurir.getWilayah().getKota() : kotaKurirBaru,
                kecamatanKurirBaru.isEmpty() ? kurir.getWilayah().getKecamatan() : kecamatanKurirBaru,
                kelurahanKurirBaru.isEmpty() ? kurir.getWilayah().getKelurahan() : kelurahanKurirBaru
        );

        // Validasi wilayah baru tidak boleh sama dengan wilayah kurir lain
        if (!wilayahBaru.equals(kurir.getWilayah()) && isWilayahSudahAda(wilayahBaru)) {
            showMessage("❌ Gagal mengedit: Sudah ada kurir lain di wilayah " +
                    wilayahBaru.getKota() + ", " + wilayahBaru.getKecamatan() + ", " + wilayahBaru.getKelurahan(), "error");
            return;
        }

        // Jika validasi berhasil, update wilayah
        if (!kotaKurirBaru.isEmpty()) {
            kurir.getWilayah().setKota(kotaKurirBaru);
        }
        if (!kecamatanKurirBaru.isEmpty()) {
            kurir.getWilayah().setKecamatan(kecamatanKurirBaru);
        }
        if (!kelurahanKurirBaru.isEmpty()) {
            kurir.getWilayah().setKelurahan(kelurahanKurirBaru);
        }

        showMessage("✅ Data kurir berhasil diperbarui!", "success");
    }

        // Method untuk mengecek apakah wilayah sudah ada
    private boolean isWilayahSudahAda(Wilayah wilayah) {
        for (Kurir kurir : daftarKurir) {
            Wilayah w = kurir.getWilayah();
            if (w.getKota().equalsIgnoreCase(wilayah.getKota()) &&
                w.getKecamatan().equalsIgnoreCase(wilayah.getKecamatan()) &&
                w.getKelurahan().equalsIgnoreCase(wilayah.getKelurahan())) {
                return true;
            }
        }
        return false;
    }

    private void hapusKurir() {
        clearScreen();
        System.out.println();
        System.out.println(RED + BOLD + "                      🗑️ HAPUS KURIR" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + RED + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();

        System.out.println("     " + YELLOW + "⚠️ PERINGATAN: Tindakan ini tidak dapat dibatalkan!" + RESET);
        System.out.println();

        System.out.print("     " + WHITE + "🆔 Masukkan ID Kurir yang akan dihapus: " + RESET);
        String idKurir = scanner.nextLine();

        Kurir kurir = cariKurirById(idKurir);
        if (kurir == null) {
            showMessage("❌ Kurir dengan ID '" + idKurir + "' tidak ditemukan!", "error");
            return;
        }

        System.out.println();
        System.out.println("     " + RED + "🎯 Kurir yang akan dihapus:" + RESET);
        System.out.println("        👤 Nama    : " + BOLD + kurir.getNama() + RESET);
        System.out.println("        🆔 ID      : " + BOLD + kurir.getIdKurir() + RESET);
        System.out.println("        📍 Wilayah : " + BOLD + kurir.getWilayah().getKota() + ", " +
                kurir.getWilayah().getKecamatan() + ", " + kurir.getWilayah().getKelurahan() + RESET);
        System.out.println();

        while (true) {
            System.out.print("     " + RED + "❓ Yakin ingin menghapus kurir ini? [y/n]: " + RESET);
            String konfirmasi = scanner.nextLine().trim().toUpperCase();

            if (konfirmasi.equals("Y")) {
                daftarKurir.remove(kurir);
                showMessage("✅ Kurir " + kurir.getNama() + " berhasil dihapus dari sistem!", "success");
                return;
            } else if (konfirmasi.equals("N")) {
                showMessage("⚠️ Penghapusan kurir dibatalkan.", "warning");
                return;
            } else {
                System.out.println("     " + RED + "❌ Input tidak valid! Masukkan Y atau N." + RESET);
            }
        }
    }

    private void cariKurir() {
        while (true) {
            clearScreen();
            System.out.println();
            System.out.println(YELLOW + BOLD + "                        🔍 CARI KURIR" + RESET);
            System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
            System.out.println("    " + YELLOW + "═══════════════════════════════════════════════════════════════════" + RESET);
            System.out.println();

            System.out.println("     " + CYAN + "🔎 Cari berdasarkan:" + RESET);
            System.out.println("        " + GREEN + "1." + RESET + " ID Kurir");
            System.out.println("        " + BLUE + "2." + RESET + " Nama Kurir");
            System.out.println("        " + RED + "3." + RESET + " Kembali");
            System.out.println();

            System.out.print("     " + YELLOW + "Pilihan [1-3]: " + RESET);
            String inputPilihan = scanner.nextLine();

            try {
                int pilihan = Integer.parseInt(inputPilihan);

                switch (pilihan) {
                    case 1:
                        cariBerdasarkanId();
                        break;
                    case 2:
                        cariBerdasarkanNama();
                        break;
                    case 3:
                        return;
                    default:
                        showMessage("❌ Pilihan harus antara 1-3! Silakan coba lagi.", "error");
                }
            } catch (NumberFormatException e) {
                showMessage("❌ Input harus berupa angka (1-3)! Silakan coba lagi.", "error");
            }
        }
    }

    private void cariBerdasarkanId() {
        System.out.println();
        System.out.print("     " + WHITE + "🆔 Masukkan ID Kurir: " + RESET);
        String idKurir = scanner.nextLine().trim();

        if (idKurir.isEmpty()) {
            showMessage("❌ ID Kurir tidak boleh kosong!", "error");
            return;
        }

        Kurir kurirById = cariKurirById(idKurir);

        if (kurirById != null) {
            System.out.println();
            System.out.println("     " + GREEN + "✅ Kurir ditemukan:" + RESET);
            tampilkanDetailKurir(kurirById);
        } else {
            showMessage("❌ Kurir dengan ID '" + idKurir + "' tidak ditemukan!", "error");
        }
        pauseScreen();
    }

    // Update method cariBerdasarkanNama() untuk styling yang lebih baik
    private void cariBerdasarkanNama() {
        System.out.println();
        System.out.print("     " + WHITE + "👤 Masukkan nama Kurir: " + RESET);
        String namaKurir = scanner.nextLine().trim();

        if (namaKurir.isEmpty()) {
            showMessage("❌ Nama Kurir tidak boleh kosong!", "error");
            return;
        }

        List<Kurir> hasilCari = cariKurirByNama(namaKurir);

        if (!hasilCari.isEmpty()) {
            System.out.println();
            System.out.println("     " + GREEN + "✅ Hasil pencarian (" + hasilCari.size() + " ditemukan):" + RESET);
            for (int i = 0; i < hasilCari.size(); i++) {
                System.out.println();
                System.out.println("     " + CYAN + "📋 Hasil " + (i + 1) + ":" + RESET);
                tampilkanDetailKurir(hasilCari.get(i));
            }
        } else {
            showMessage("❌ Tidak ditemukan kurir dengan nama '" + namaKurir + "'", "error");
        }
        pauseScreen();
    }

    private Kurir cariKurirById(String idKurir) {
        if (daftarKurir == null || daftarKurir.isEmpty()) {
            System.out.println("Daftar kurir kosong!");
            return null;
        }
        
        for (Kurir k : daftarKurir) {
            if (k != null && k.getIdKurir() != null && k.getIdKurir().equalsIgnoreCase(idKurir)) {
                return k;
            }
        }
        return null;
    }

    private List<Kurir> cariKurirByNama(String nama) {
        List<Kurir> hasil = new ArrayList<>();
        for (Kurir k : daftarKurir) {
            if (k.getNama().toLowerCase().contains(nama.toLowerCase())) {
                hasil.add(k);
            }
        }
        return hasil;
    }

    private void tampilkanDetailKurir(Kurir kurir) {
        System.out.println("        👤 Nama      : " + WHITE + BOLD + kurir.getNama() + RESET);
        System.out.println("        🆔 ID        : " + YELLOW + BOLD + kurir.getIdKurir() + RESET);
        System.out.println("        📍 Wilayah   : " + GREEN + kurir.getWilayah().getKota() + ", " +
                kurir.getWilayah().getKecamatan() + ", " + kurir.getWilayah().getKelurahan() + RESET);
        System.out.println("        📊 Aktivitas : " + BLUE + kurir.getRiwayatAktivitas().size() + " kegiatan" + RESET);
    }

    private void showMessage(String message, String type) {
        String icon = "";
        String color = "";

        switch (type.toLowerCase()) {
            case "success":
                icon = "✅";
                color = GREEN;
                break;
            case "error":
                icon = "❌";
                color = RED;
                break;
            case "warning":
                icon = "⚠️";
                color = YELLOW;
                break;
            case "info":
                icon = "ℹ️";
                color = BLUE;
                break;
        }

        System.out.println();
        System.out.println("    " + color + BOLD + icon + " " + message + RESET);
        System.out.println();

        if (!type.equals("info")) {
            System.out.println("    " + DIM + "Tekan ENTER untuk melanjutkan..." + RESET);
            scanner.nextLine();
        }
    }

    // Tambahkan method helper untuk clear screen (jika belum ada)
    private void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    }

    public void buatLaporan() {
        while (true) {
            clearScreen();
            System.out.println();
            System.out.println(BLUE + BOLD + "                       📊 BUAT LAPORAN" + RESET);
            System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
            System.out.println("    " + BLUE + "═══════════════════════════════════════════════════════════════════" + RESET);
            System.out.println();
            
            System.out.println("     " + CYAN + "📈 Pilih jenis laporan yang ingin dibuat:" + RESET);
            System.out.println();
            
            System.out.println("       " + GREEN + BOLD + "1." + RESET + "  " + WHITE + BOLD + "Laporan Harian" + RESET);
            System.out.println("           " + GRAY + "→ Data pengiriman 24 jam terakhir" + RESET);
            System.out.println();
            
            System.out.println("       " + YELLOW + BOLD + "2." + RESET + "  " + WHITE + BOLD + "Laporan Mingguan" + RESET);
            System.out.println("           " + GRAY + "→ Data pengiriman 7 hari terakhir" + RESET);
            System.out.println();
            
            System.out.println("       " + PURPLE + BOLD + "3." + RESET + "  " + WHITE + BOLD + "Laporan Bulanan" + RESET);
            System.out.println("           " + GRAY + "→ Data pengiriman 30 hari terakhir" + RESET);
            System.out.println();
            
            System.out.println("       " + RED + BOLD + "4." + RESET + "  " + WHITE + BOLD + "Kembali" + RESET);
            System.out.println("           " + GRAY + "→ Kembali ke menu admin" + RESET);
            System.out.println();
            
            System.out.println("    " + BLUE + "═══════════════════════════════════════════════════════════════════" + RESET);
            System.out.print("    " + YELLOW + BOLD + "⚡ Pilihan Anda [1-4]: " + RESET);
            
            String input = scanner.nextLine();
            
            try {
                int pilihan = Integer.parseInt(input);
                
                if (pilihan < 1 || pilihan > 4) {
                    showMessage("❌ Pilihan harus antara 1-4! Silakan coba lagi.", "error");
                    continue;
                }
                
                if (pilihan == 4) {
                    return;
                }
                
                // Generate laporan berdasarkan pilihan
                generateLaporan(pilihan);
                
            } catch (NumberFormatException e) {
                showMessage("❌ Input harus berupa angka (1-4)! Silakan coba lagi.", "error");
            }
        }
    }

    // Method untuk generate laporan
    private void generateLaporan(int jenis) {
        clearScreen();
        System.out.println();
        
        String namaLaporan = "";
        int hari = 0;
        
        switch (jenis) {
            case 1:
                namaLaporan = "HARIAN";
                hari = 1;
                break;
            case 2:
                namaLaporan = "MINGGUAN";
                hari = 7;
                break;
            case 3:
                namaLaporan = "BULANAN";
                hari = 30;
                break;
        }
        
        System.out.println(PURPLE + BOLD + "                    📋 LAPORAN " + namaLaporan + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + PURPLE + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();
        
        // Loading animation
        System.out.print("     " + GRAY + "🔄 Mengumpulkan data");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(300);
                System.out.print(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(" " + GREEN + "✓ Selesai!" + RESET);
        System.out.println();
        
        // Generate laporan
        Calendar cal = Calendar.getInstance();
        Date akhir = new Date();
        cal.add(Calendar.DAY_OF_MONTH, -hari);
        Date awal = cal.getTime();
        
        Laporan laporan = new Laporan(awal, akhir, new PengelolaPengiriman(Main.getDaftarPengiriman()));
        
        // Tampilkan header laporan
        System.out.println("     " + BLUE + "📊 RINGKASAN LAPORAN:" + RESET);
        System.out.println("    " + BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        
        // Tampilkan periode
        System.out.println("        📅 Periode      : " + BOLD + formatTanggal(awal) + " - " + formatTanggal(akhir) + RESET);
        System.out.println("        📋 Jenis        : " + BOLD + "Laporan " + namaLaporan + RESET);
        System.out.println("        🕐 Dibuat       : " + BOLD + formatTanggal(new Date()) + RESET);
        System.out.println();
        
        // Tampilkan isi laporan dengan styling
        String isiLaporan = laporan.tampilkanLaporan();
        String[] lines = isiLaporan.split("\n");
        
        for (String line : lines) {
            if (line.contains("Total Pengiriman:")) {
                System.out.println("        📦 " + GREEN + BOLD + line + RESET);
            } else if (line.contains("Total Pendapatan:")) {
                System.out.println("        💰 " + YELLOW + BOLD + line + RESET);
            } else if (line.contains("Pengiriman Berhasil:")) {
                System.out.println("        ✅ " + GREEN + line + RESET);
            } else if (line.contains("Pengiriman Dalam Proses:")) {
                System.out.println("        🔄 " + BLUE + line + RESET);
            } else if (line.contains("Pengiriman Gagal:")) {
                System.out.println("        ❌ " + RED + line + RESET);
            } else if (!line.trim().isEmpty()) {
                System.out.println("        " + line);
            }
        }
        
        System.out.println();
        System.out.println("    " + PURPLE + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();
        
        // Menu opsi setelah laporan
        System.out.println("     " + CYAN + "📎 Opsi Laporan:" + RESET);
        System.out.println("        " + GREEN + "1." + RESET + " Cetak Laporan");
        System.out.println("        " + BLUE + "2." + RESET + " Export ke File");
        System.out.println("        " + YELLOW + "3." + RESET + " Buat Laporan Lain");
        System.out.println("        " + RED + "4." + RESET + " Kembali ke Menu");
        System.out.println();
        
        System.out.print("     " + YELLOW + "Pilihan [1-4]: " + RESET);
        String pilihan = scanner.nextLine();
        
        switch (pilihan) {
            case "1":
                showMessage("🖨️ Laporan sedang dicetak... (fitur dalam pengembangan)", "info");
                pauseScreen();
                break;
            case "2":
                showMessage("💾 Laporan sedang di-export... (fitur dalam pengembangan)", "info");
                pauseScreen();
                break;
            case "3":
                return; // Kembali ke menu pilih laporan
            case "4":
                return; // Keluar dari method buatLaporan
            default:
                showMessage("❌ Pilihan tidak valid!", "error");
        }
    }

    private String formatTanggal(Date tanggal) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(tanggal);
    
    return String.format("%02d/%02d/%d", 
                        cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.MONTH) + 1,
                        cal.get(Calendar.YEAR));
    }



@Override
    public void tampilkanMenu() {
        while (true) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Kelola Kurir");
            System.out.println("2. Buat Pengiriman");
            System.out.println("3. Buat Laporan");
            System.out.println("4. Logout");
            System.out.print("Pilih menu: ");
            
            String input = scanner.nextLine();
            
            try {
                int pilihan = Integer.parseInt(input);
                
                switch (pilihan) {
                    case 1:
                        kelolaKurir();
                        break;
                    case 2:
                        buatPengiriman();
                        break;
                    case 3:
                        buatLaporan();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Pilihan harus antara 1-4! Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka (1-4)! Silakan coba lagi.");
            }
        }
    }

    // Method untuk membuat pengiriman baru
    public void buatPengiriman() {
    clearScreen();
    System.out.println();
    System.out.println(GREEN + BOLD + "                     📦 BUAT PENGIRIMAN BARU" + RESET);
    System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
    System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
    System.out.println();
    
    try {
        // Input data pengirim
        System.out.println("     " + BLUE + "📤 DATA PENGIRIM:" + RESET);
        System.out.println("    " + BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        
        System.out.print("     👤 Nama Lengkap      : ");
        String namaPengirim = scanner.nextLine();
        
        System.out.print("     🏠 Alamat            : ");
        String alamatPengirim = scanner.nextLine();
        
        System.out.print("     📱 No. Kontak        : ");
        String kontakPengirim = scanner.nextLine();
        
        System.out.print("     📝 Catatan Khusus    : ");
        String catatanPengirim = scanner.nextLine();
        
        System.out.println();
        
        // Input data penerima
        System.out.println();
        System.out.println("     " + PURPLE + "📥 DATA PENERIMA:" + RESET);
        System.out.println("    " + PURPLE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        
        System.out.print("     👤 Nama Lengkap      : ");
        String namaPenerima = scanner.nextLine();
        
        System.out.print("     📱 No. Kontak        : ");
        String kontakPenerima = scanner.nextLine();
        
        System.out.println();
        System.out.println("     " + CYAN + "📍 Alamat Tujuan:" + RESET);
        System.out.print("     🏙️  Kota              : ");
        String kotaTujuan = scanner.nextLine();
        
        System.out.print("     🏘️  Kecamatan         : ");
        String kecamatanTujuan = scanner.nextLine();
        
        System.out.print("     🏠 Kelurahan         : ");
        String kelurahanTujuan = scanner.nextLine();
        
        System.out.println();
        
        // Input data pengiriman
        System.out.println("     " + YELLOW + "📋 DATA PENGIRIMAN:" + RESET);
        System.out.println("    " + YELLOW + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        
        System.out.print("     📦 Jenis Barang      : ");
        String jenisBarang = scanner.nextLine();

        // Input berat dengan validasi
        float berat = inputBerat();
        
        // Input jenis layanan
        String jenisLayanan = inputJenisLayanan();
        
        // Input status pembayaran
        String statusPembayaran = inputStatusPembayaran();
        
        System.out.println();
        System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();
        
        // Generate ID Resi otomatis
        String idResi = "RESI" + System.currentTimeMillis();
        
        // Tampilkan ringkasan
        tampilkanRingkasanPengiriman(idResi, namaPengirim, namaPenerima, kotaTujuan, 
                                   kecamatanTujuan, kelurahanTujuan, jenisBarang, 
                                   berat, jenisLayanan, statusPembayaran);
        
        System.out.print("     " + GREEN + "✓ Buat pengiriman ini? [y/n]: " + RESET);
        String konfirmasi = scanner.nextLine();
        
        if (konfirmasi.equalsIgnoreCase("y") || konfirmasi.equalsIgnoreCase("yes")) {
            // Buat objek-objek yang diperlukan
            Pengirim pengirim = new Pengirim(namaPengirim, alamatPengirim, kontakPengirim, catatanPengirim);
            Penerima penerima = new Penerima(namaPenerima, kontakPenerima);
            Wilayah tujuan = new Wilayah(kotaTujuan, kecamatanTujuan, kelurahanTujuan);
            
            // Hitung biaya pengiriman
            double biayaPengiriman = hitungBiayaPengiriman(berat, jenisLayanan);
            
            // Cara 1: Jika menggunakan constructor dengan Tarif
            Tarif tarif = new Tarif(berat, jenisLayanan);
            Pengiriman pengirimanBaru = new Pengiriman(
                idResi,
                new Date(),
                berat,
                jenisBarang,
                jenisLayanan,
                pengirim,
                penerima,
                statusPembayaran,
                "Dalam Proses",
                tarif,
                alamatPengirim,  // asal sebagai String
                tujuan
            );
            
            Main.getDaftarPengiriman().add(pengirimanBaru);
            
            // Tampilkan konfirmasi sukses
            tampilkanKonfirmasiPengiriman(idResi, berat, jenisLayanan);
            
        } else {
            showMessage("⚠️ Pembuatan pengiriman dibatalkan.", "warning");
        }
        
    } catch (Exception e) {
        showMessage("❌ Terjadi kesalahan: " + e.getMessage(), "error");
    }
}

    // Method helper untuk input berat
    private float inputBerat() {
        float berat = 0;
        boolean beratValid = false;
        
        while (!beratValid) {
            System.out.print("     ⚖️  Berat (kg)         : ");
            String inputBerat = scanner.nextLine();
            
            try {
                berat = Float.parseFloat(inputBerat);
                if (berat <= 0) {
                    System.out.println("     " + RED + "❌ Berat harus lebih dari 0! Silakan coba lagi." + RESET);
                } else {
                    beratValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("     " + RED + "❌ Input harus berupa angka! Silakan coba lagi." + RESET);
            }
        }
        return berat;
    }

    private String inputJenisLayanan() {
    String jenisLayanan = "";
    boolean jenisLayananValid = false;

        while (!jenisLayananValid) {
            System.out.println();
            System.out.println("     " + CYAN + "🚀 Jenis Layanan:" + RESET);
            System.out.println("        " + GREEN + "1." + RESET + " Ekonomis  " + GRAY + "(2-3 hari, hemat)" + RESET);
            System.out.println("        " + BLUE + "2." + RESET + " Reguler   " + GRAY + "(1-2 hari, standar)" + RESET);
            System.out.println("        " + RED + "3." + RESET + " Ekspres   " + GRAY + "(same day, cepat)" + RESET);
            System.out.print("     Pilih layanan [1-3]: ");

            String input = scanner.nextLine();
            
            try {
                int pilihan = Integer.parseInt(input);
                switch (pilihan) {
                    case 1:
                        jenisLayanan = "ekonomis";
                        jenisLayananValid = true;
                        break;
                    case 2:
                        jenisLayanan = "reguler";
                        jenisLayananValid = true;
                        break;
                    case 3:
                        jenisLayanan = "ekspres";
                        jenisLayananValid = true;
                        break;
                    default:
                        System.out.println("     " + RED + "❌ Pilihan harus 1, 2, atau 3! Silakan coba lagi." + RESET);
                }
            } catch (NumberFormatException e) {
                    System.out.println("     " + RED + "❌ Input harus berupa angka (1-3)! Silakan coba lagi." + RESET);
                }
        }
        return jenisLayanan;
    }

    // Method helper untuk input status pembayaran
    private String inputStatusPembayaran() {
        String statusPembayaran = "";
        boolean pembayaranValid = false;
        
        while (!pembayaranValid) {
            System.out.println();
            System.out.println("     " + YELLOW + "💳 Status Pembayaran:" + RESET);
            System.out.println("        " + GREEN + "1." + RESET + " Sudah Dibayar");
            System.out.println("        " + RED + "2." + RESET + " Belum Dibayar");
            System.out.print("     Pilih status [1-2]: ");
            
            String input = scanner.nextLine();
            
            try {
                int pilihan = Integer.parseInt(input);
                if (pilihan == 1) {
                    statusPembayaran = "Sudah Dibayar";
                    pembayaranValid = true;
                } else if (pilihan == 2) {
                    statusPembayaran = "Belum Dibayar";
                    pembayaranValid = true;
                } else {
                    System.out.println("     " + RED + "❌ Pilihan harus 1 atau 2! Silakan coba lagi." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println("     " + RED + "❌ Input harus berupa angka (1 atau 2)! Silakan coba lagi." + RESET);
            }
        }
        return statusPembayaran;
    }

    private void tampilkanRingkasanPengiriman(String idResi, String namaPengirim, String namaPenerima,
                                         String kota, String kecamatan, String kelurahan,
                                         String jenisBarang, float berat, String jenisLayanan,
                                         String statusPembayaran) {
        System.out.println("     " + CYAN + "📋 RINGKASAN PENGIRIMAN:" + RESET);
        System.out.println();
        System.out.println("        🆔 ID Resi     : " + BOLD + idResi + RESET);
        System.out.println("        📤 Pengirim    : " + BOLD + namaPengirim + RESET);
        System.out.println("        📥 Penerima    : " + BOLD + namaPenerima + RESET);
        System.out.println("        📍 Tujuan      : " + BOLD + kota + ", " + kecamatan + ", " + kelurahan + RESET);
        System.out.println("        📦 Barang      : " + BOLD + jenisBarang + RESET);
        System.out.println("        ⚖️  Berat       : " + BOLD + berat + " kg" + RESET);
        System.out.println("        🚀 Layanan     : " + BOLD + jenisLayanan.toUpperCase() + RESET);
        System.out.println("        💳 Pembayaran  : " + BOLD + statusPembayaran + RESET);
    
        // Hitung estimasi biaya dengan penanganan error
        try {
            double biaya = hitungBiayaPengiriman(berat, jenisLayanan);
            System.out.println("        💰 Biaya       : " + GREEN + BOLD + "Rp " + String.format("%,d", (int)biaya) + RESET);
        } catch (Exception e) {
            System.out.println("        💰 Biaya       : " + RED + "Error menghitung biaya" + RESET);
        }
        System.out.println();
    }

    private double hitungBiayaPengiriman(float berat, String jenisLayanan) {
        double biayaPerKg = 0;
        double biayaLayanan = 0;
        
        // Biaya berdasarkan jenis layanan
        switch (jenisLayanan.toLowerCase()) {
            case "ekonomis":
                biayaPerKg = 5000; // Rp 5,000 per kg
                biayaLayanan = 2000; // Biaya tambahan layanan
                break;
            case "reguler":
                biayaPerKg = 8000; // Rp 8,000 per kg
                biayaLayanan = 5000; // Biaya tambahan layanan
                break;
            case "ekspres":
                biayaPerKg = 12000; // Rp 12,000 per kg
                biayaLayanan = 10000; // Biaya tambahan layanan
                break;
            default:
                biayaPerKg = 8000; // Default ke reguler
                biayaLayanan = 5000;
        }
        
        // Hitung total biaya
        double totalBiaya = (berat * biayaPerKg) + biayaLayanan;
        
        // Minimum biaya Rp 10,000
        if (totalBiaya < 10000) {
            totalBiaya = 10000;
        }
        
        return totalBiaya;
    }

    // Method untuk menampilkan konfirmasi pengiriman berhasil
    private void tampilkanKonfirmasiPengiriman(String idResi, float berat, String jenisLayanan) {
        clearScreen();
        System.out.println();
        System.out.println(GREEN + BOLD + "                    ✅ PENGIRIMAN BERHASIL DIBUAT!" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
        System.out.println();
        
        System.out.println("     " + GREEN + "🎉 Pengiriman telah berhasil didaftarkan dalam sistem!" + RESET);
        System.out.println();
        System.out.println("        🆔 ID Resi    : " + YELLOW + BOLD + idResi + RESET);
        
        // Hitung biaya dengan method yang sudah dibuat
        double biaya = hitungBiayaPengiriman(berat, jenisLayanan);
        System.out.println("        💰 Total Biaya: " + GREEN + BOLD + "Rp " + String.format("%,d", (int)biaya) + RESET);
        System.out.println("        📅 Tanggal    : " + WHITE + new Date() + RESET);
        System.out.println("        📊 Status     : " + BLUE + BOLD + "Dalam Proses" + RESET);
        System.out.println();
        
        System.out.println("     " + CYAN + "📱 Informasi untuk pelanggan:" + RESET);
        System.out.println("        • Simpan ID Resi untuk tracking");
        System.out.println("        • Paket akan diproses dalam 1x24 jam");
        System.out.println("        • Cek status melalui sistem tracking");
        System.out.println();
        
        System.out.println("    " + GREEN + "═══════════════════════════════════════════════════════════════════" + RESET);
        pauseScreen();
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}