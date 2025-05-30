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

    public void tambahKurir() {
        System.out.println("\n=== TAMBAH KURIR BARU ===");
        
        System.out.print("Nama Kurir: ");
        String nama = scanner.nextLine();
        System.out.print("ID Kurir: ");
        String idKurir = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Input wilayah kurir dengan validasi
        Wilayah wilayah = null;
        while (wilayah == null) {
            System.out.print("Wilayah: ");
            String namaWilayah = scanner.nextLine();
            wilayah = Main.getWilayahByName(namaWilayah);
            if (wilayah == null) {
                System.out.println("Wilayah tidak valid! Silakan input ulang.");
            }
        }

        // Tambahkan kurir baru
        Kurir kurirBaru = new Kurir(nama, idKurir, password, wilayah);
        Admin.getDaftarKurir().add(kurirBaru);
        System.out.println("Kurir berhasil ditambahkan!");
    }

    // Method untuk menambah kurir baru
    public void kelolaKurir() {
        System.out.println("\n=== KELOLA KURIR ===");
        System.out.println("1. Tambah Kurir");
        System.out.println("2. Lihat Daftar Kurir");
        System.out.println("3. Kembali");
        System.out.print("Pilih menu: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        switch (pilihan) {
            case 1:
                tambahKurir();
                break;
            case 2:
                System.out.println("\nDAFTAR KURIR:");
                if (daftarKurir.isEmpty()) {
                    System.out.println("Tidak ada kurir yang terdaftar.");
                    return;
                }
                for (Kurir k : daftarKurir) {
                    System.out.println("- " + k.getNama() + " (ID: " + k.getIdKurir() + 
                                     ", Wilayah: " + k.getWilayah().getNamaWilayah() + ")");
                }
                break;
                
            case 3:
                return;
                
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    // Method untuk membuat laporan
    public void buatLaporan() {
        System.out.println("\n=== BUAT LAPORAN ===");
        System.out.println("1. Laporan Harian");
        System.out.println("2. Laporan Mingguan");
        System.out.println("3. Laporan Bulanan");
        System.out.println("4. Kembali");
        System.out.print("Pilih jenis laporan: ");
        
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        
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
            case 4:
                return;
            default:
                System.out.println("Pilihan tidak valid!");
                return;
        }
        
        Date awal = cal.getTime();
        Laporan laporan = new Laporan(awal, akhir, new PengelolaPengiriman(Main.getDaftarPengiriman()));
        System.out.println(laporan.tampilkanLaporan());
    }

    @Override
    public void tampilkanMenu() {
        System.out.println("\n=== MENU ADMIN ===");
        System.out.println("1. Kelola Kurir");
        System.out.println("2. Buat Pengiriman");
        System.out.println("3. Buat Laporan");
        System.out.println("4. Logout");
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
            System.out.print("Alamat: ");
            String alamatPenerima = scanner.nextLine();
            System.out.print("No. Kontak: ");
            String kontakPenerima = scanner.nextLine();
            System.out.print("Catatan Penerimaan: ");
            String catatanPenerima = scanner.nextLine();
            
            // Input data pengiriman
            System.out.println("\nData Pengiriman:");
            System.out.print("Jenis Barang: ");
            String jenisBarang = scanner.nextLine();
            System.out.print("Berat (kg): ");
            float berat = scanner.nextFloat();
            scanner.nextLine();
            System.out.print("Jenis Layanan (reguler/ekspres): ");
            String jenisLayanan = scanner.nextLine();
            Wilayah asal = null;
            while (asal == null) {
                System.out.print("Wilayah Asal: ");
                String wilayahAsal = scanner.nextLine();
                asal = Main.getWilayahByName(wilayahAsal);
                if (asal == null) {
                    System.out.println("Pelayanan tidak tersedia untuk wilayah anda! Silakan input ulang.");
                }
            }

            // Input wilayah tujuan dengan validasi
            Wilayah tujuan = null;
            while (tujuan == null) {
                System.out.print("Wilayah Tujuan: ");
                String wilayahTujuan = scanner.nextLine();
                tujuan = Main.getWilayahByName(wilayahTujuan);
                if (tujuan == null) {
                    System.out.println("Pelayanan tidak tersedia untuk wilayah anda! Silakan input ulang.");
                }
            }
                
            // Generate ID Resi otomatis
            String idResi = "RESI" + System.currentTimeMillis();
                
            // Buat objek-objek yang diperlukan
            Pengirim pengirim = new Pengirim(namaPengirim, alamatPengirim, kontakPengirim, catatanPengirim, false);
            Penerima penerima = new Penerima(namaPenerima, alamatPenerima, kontakPenerima, catatanPenerima, false);
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
                "Belum Dibayar",
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