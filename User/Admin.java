package User;

import Pengiriman.*;
import Pelanggan.*;
import java.util.*;

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
            System.out.println("\n=== KELOLA KURIR ===");
            System.out.println("1. Tambah Kurir");
            System.out.println("2. Lihat Daftar Kurir");
            System.out.println("3. Edit Data Kurir");
            System.out.println("4. Hapus Kurir");
            System.out.println("5. Cari Kurir");
            System.out.println("6. Kembali");
            System.out.print("Pilih menu: ");
            
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
                        System.out.println("Pilihan harus antara 1-6! Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka (1-6)! Silakan coba lagi.");
            }
        }
    }

        public void tambahKurir() {
        System.out.println("\n=== TAMBAH KURIR BARU ===");
        
        System.out.print("Nama Kurir: ");
        String nama = scanner.nextLine();
        System.out.print("ID Kurir: ");
        String idKurir = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.println("\n Wilayah Kurir:");
        System.out.print("Kota: ");
        String kotaKurir = scanner.nextLine();
        System.out.print("Kecamatan: ");
        String kecamatanKurir = scanner.nextLine();
        System.out.print("Kelurahan: ");
        String kelurahanKurir = scanner.nextLine();

        Wilayah wilayahBaru = new Wilayah(kotaKurir, kecamatanKurir, kelurahanKurir);
        if (isWilayahSudahAda(wilayahBaru)) {
            System.out.println("Gagal menambahkan kurir: Sudah ada kurir di wilayah " + 
                kotaKurir + ", " + kecamatanKurir + ", " + kelurahanKurir);
            return;
    }
        // Tambahkan kurir baru
        Kurir kurirBaru = new Kurir(nama, idKurir, password, wilayahBaru);
        Admin.getDaftarKurir().add(kurirBaru);
        System.out.println("Kurir berhasil ditambahkan!");
    }


    private void lihatDaftarKurir() {
        System.out.println("\nDAFTAR KURIR:");
        if (daftarKurir.isEmpty()) {
            System.out.println("Tidak ada kurir yang terdaftar.");
            return;
        }
        for (Kurir k : daftarKurir) {
            System.out.println("- " + k.getNama() + " (ID: " + k.getIdKurir() + 
                            ", Wilayah: " + k.getWilayah().getKota() + ", " + k.getWilayah().getKecamatan() + ", " + k.getWilayah().getKelurahan() + ")");
        }
    }

    private void editKurir() {
        System.out.println("\n=== EDIT DATA KURIR ===");
        System.out.print("Masukkan ID Kurir yang akan diedit: ");
        String idKurir = scanner.nextLine();
        
        Kurir kurir = cariKurirById(idKurir);
        if (kurir == null) {
            System.out.println("Kurir dengan ID " + idKurir + " tidak ditemukan!");
            return;
        }
        
        System.out.println("\nData saat ini:");
        System.out.println("Nama: " + kurir.getNama());
        System.out.println("ID Kurir: " + kurir.getIdKurir());
        System.out.println("Wilayah: " + kurir.getWilayah().getKota() + ", " + kurir.getWilayah().getKecamatan() + ", " + kurir.getWilayah().getKelurahan());
        
        System.out.println("\nMasukkan data baru (kosongkan jika tidak ingin mengubah):");
        
        System.out.print("Nama baru: ");
        String namaBaru = scanner.nextLine();
        if (!namaBaru.isEmpty()) {
            kurir.setNama(namaBaru);
        }
        
        System.out.print("Password baru: ");
        String passwordBaru = scanner.nextLine();
        if (!passwordBaru.isEmpty()) {
            kurir.setPassword(passwordBaru);
        }
        
        System.out.println("\n Wilayah Baru Kurir:");
        System.out.print("Kota (Baru): ");
        String kotaKurirBaru = scanner.nextLine();

        System.out.print("Kecamatan (Baru): ");
        String kecamatanKurirBaru = scanner.nextLine();

        System.out.print("Kelurahan (Baru): ");
        String kelurahanKurirBaru = scanner.nextLine();
        
        // Buat objek wilayah baru untuk validasi
        Wilayah wilayahBaru = new Wilayah(
            kotaKurirBaru.isEmpty() ? kurir.getWilayah().getKota() : kotaKurirBaru,
            kecamatanKurirBaru.isEmpty() ? kurir.getWilayah().getKecamatan() : kecamatanKurirBaru,
            kelurahanKurirBaru.isEmpty() ? kurir.getWilayah().getKelurahan() : kelurahanKurirBaru
        );
        
        // Validasi wilayah baru tidak boleh sama dengan wilayah kurir lain
        if (!wilayahBaru.equals(kurir.getWilayah()) && isWilayahSudahAda(wilayahBaru)) {
            System.out.println("Gagal mengedit: Sudah ada kurir lain di wilayah " + 
                wilayahBaru.getKota() + ", " + wilayahBaru.getKecamatan() + ", " + wilayahBaru.getKelurahan());
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
        
        System.out.println("Data kurir berhasil diperbarui!");
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
        System.out.println("\n=== HAPUS KURIR ===");
        System.out.print("Masukkan ID Kurir yang akan dihapus: ");
        String idKurir = scanner.nextLine();
        
        Kurir kurir = cariKurirById(idKurir);
        if (kurir == null) {
            System.out.println("Kurir dengan ID " + idKurir + " tidak ditemukan!");
            return;
        }
        
        System.out.println("Apakah Anda yakin ingin menghapus kurir berikut?");
        System.out.println("Nama: " + kurir.getNama());
        System.out.println("ID: " + kurir.getIdKurir());
        System.out.println("Wilayah: " + kurir.getWilayah().getKota() + ", " + kurir.getWilayah().getKecamatan() + ", " + kurir.getWilayah().getKelurahan());
        
        while (true) {
            System.out.print("Konfirmasi (Y/N): ");
            String konfirmasi = scanner.nextLine().trim().toUpperCase();
            
            if (konfirmasi.equals("Y")) {
                daftarKurir.remove(kurir);
                System.out.println("Kurir berhasil dihapus!");
                return;
            } else if (konfirmasi.equals("N")) {
                System.out.println("Penghapusan dibatalkan.");
                return;
            } else {
                System.out.println("Input tidak valid! Masukkan Y atau N.");
            }
        }
    }

    private void cariKurir() {
        while (true) {
            System.out.println("\n=== CARI KURIR ===");
            System.out.println("1. Cari berdasarkan ID");
            System.out.println("2. Cari berdasarkan nama");
            System.out.println("3. Kembali");
            System.out.print("Pilihan: ");
            
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
                        System.out.println("Pilihan harus antara 1-3! Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka (1-3)! Silakan coba lagi.");
            }
        }
    }

    private void cariBerdasarkanId() {
        System.out.print("Masukkan ID Kurir: ");
        String idKurir = scanner.nextLine().trim();
        
        if (idKurir.isEmpty()) {
            System.out.println("ID Kurir tidak boleh kosong!");
            return;
        }
        
        Kurir kurirById = cariKurirById(idKurir);
        
        if (kurirById != null) {
            tampilkanDetailKurir(kurirById);
        } else {
            System.out.println("Kurir dengan ID '" + idKurir + "' tidak ditemukan!");
        }
    }

    private void cariBerdasarkanNama() {
        System.out.print("Masukkan nama Kurir: ");
        String namaKurir = scanner.nextLine().trim();
        
        if (namaKurir.isEmpty()) {
            System.out.println("Nama Kurir tidak boleh kosong!");
            return;
        }
        
        List<Kurir> hasilCari = cariKurirByNama(namaKurir);
        
        if (!hasilCari.isEmpty()) {
            System.out.println("\nHasil pencarian (" + hasilCari.size() + " ditemukan):");
            for (Kurir k : hasilCari) {
                tampilkanDetailKurir(k);
            }
        } else {
            System.out.println("Tidak ditemukan kurir dengan nama '" + namaKurir + "'");
        }
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
        System.out.println("\nDetail Kurir:");
        System.out.println("Nama: " + kurir.getNama());
        System.out.println("ID: " + kurir.getIdKurir());
        System.out.println("Wilayah: " + kurir.getWilayah().getKota() + ", " + kurir.getWilayah().getKecamatan() + ", " + kurir.getWilayah().getKelurahan());
        System.out.println("Jumlah Aktivitas: " + kurir.getRiwayatAktivitas().size());
    }

    public void buatLaporan() {
        while (true) {
            System.out.println("\n=== BUAT LAPORAN ===");
            System.out.println("1. Laporan Harian");
            System.out.println("2. Laporan Mingguan");
            System.out.println("3. Laporan Bulanan");
            System.out.println("4. Kembali");
            System.out.print("Pilih jenis laporan: ");
            
            String input = scanner.nextLine();
            
            try {
                int pilihan = Integer.parseInt(input);
                
                if (pilihan < 1 || pilihan > 4) {
                    System.out.println("Pilihan harus antara 1-4! Silakan coba lagi.");
                    continue;
                }
                
                if (pilihan == 4) {
                    return;
                }
                
                Calendar cal = Calendar.getInstance();
                Date akhir = new Date();
                
                switch (pilihan) {
                    case 1:
                        cal.add(Calendar.DAY_OF_MONTH, -1);
                        break;
                    case 2:
                        cal.add(Calendar.DAY_OF_MONTH, -7);
                        break;
                    case 3:
                        cal.add(Calendar.DAY_OF_MONTH, -30);
                        break;
                }
                
                Date awal = cal.getTime();
                Laporan laporan = new Laporan(awal, akhir, new PengelolaPengiriman(Main.getDaftarPengiriman()));
                System.out.println(laporan.tampilkanLaporan());
                
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka (1-4)! Silakan coba lagi.");
            }
        }
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
        System.out.println("\n=== BUAT PENGIRIMAN BARU ===");
        
        try {
            // Input data pengirim
            System.out.println("\nData Pengirim:");
            System.out.print("Nama: ");
            String namaPengirim = scanner.nextLine();
            System.out.print("Alamat: ");
            String alamatPengirim = scanner.nextLine();
            System.out.print("No. Kontak: ");
            String kontakPengirim = scanner.nextLine();
            System.out.print("Catatan Khusus: ");
            String catatanPengirim = scanner.nextLine();
            
            // Input data penerima
            System.out.println("\nData Penerima:");
            System.out.print("Nama: ");
            String namaPenerima = scanner.nextLine();
            System.out.print("No. Kontak: ");
            String kontakPenerima = scanner.nextLine();
            System.out.println("Alamat:");
            System.out.print("Kota: ");
            String kotaTujuan = scanner.nextLine();
            System.out.print("Kecamatan: ");
            String kecamatanTujuan = scanner.nextLine();
            System.out.print("Kelurahan: ");
            String kelurahanTujuan = scanner.nextLine();
            
            // Input data pengiriman
            System.out.println("\nData Pengiriman:");
            System.out.print("Jenis Barang: ");
            String jenisBarang = scanner.nextLine();

            float berat = 0;
            boolean beratValid = false;
            while (!beratValid) {
                System.out.print("Berat (kg): ");
                String inputBerat = scanner.nextLine();
                
                try {
                    berat = Float.parseFloat(inputBerat);
                    if (berat <= 0) {
                        System.out.println("Berat harus lebih dari 0! Silakan coba lagi.");
                    } else {
                        beratValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka! Silakan coba lagi.");
                }
            }

            String jenisLayanan = "";
            boolean jenisLayananValid = false;

            while (!jenisLayananValid) {
                System.out.println("\nJenis Layanan: ");
                System.out.println("1. Ekonomis");
                System.out.println("2. Reguler");
                System.out.println("3. Ekspres");
                System.out.print("Pilih status pembayaran (1-3): ");

                String input = scanner.nextLine();
                
                try {
                    int pilihan = Integer.parseInt(input);
                    if (pilihan == 1) {
                        jenisLayanan = "ekonomis";
                        jenisLayananValid = true;
                    } else if (pilihan == 2) {
                        jenisLayanan = "reguler";
                        jenisLayananValid = true;
                    } else if (pilihan == 3) {
                        jenisLayanan = "ekspres";
                        jenisLayananValid = true;
                    } else {
                        System.out.println("Pilihan harus 1 atau 2! Silakan coba lagi.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka (1 atau 2)! Silakan coba lagi.");
                }
            }

            String statusPembayaran = "";
            boolean pembayaranValid = false;
            
            while (!pembayaranValid) {
                System.out.println("\nStatus Pembayaran:");
                System.out.println("1. Sudah Dibayar");
                System.out.println("2. Belum Dibayar");
                System.out.print("Pilih status pembayaran (1-2): ");
                
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
                        System.out.println("Pilihan harus 1 atau 2! Silakan coba lagi.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka (1 atau 2)! Silakan coba lagi.");
                }
            }

                
            // Generate ID Resi otomatis
            String idResi = "RESI" + System.currentTimeMillis();
                
            Pengirim pengirim = new Pengirim(namaPengirim, alamatPengirim, kontakPengirim, catatanPengirim);
            Penerima penerima = new Penerima(namaPenerima, kontakPenerima);
            String asal = alamatPengirim;
            Wilayah tujuan = new Wilayah(kotaTujuan, kecamatanTujuan, kelurahanTujuan);
            Tarif tarif = new Tarif(berat, jenisLayanan);
            
            // Buat pengiriman baru
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
                asal,
                tujuan
            );
            
            Main.getDaftarPengiriman().add(pengirimanBaru);
            System.out.println("\nPengiriman berhasil dibuat dengan ID: " + idResi);
            
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}