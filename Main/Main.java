package Main;

import User.*;
import Pengiriman.*;
import Pelanggan.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Pengiriman> daftarPengiriman = new ArrayList<>();
    private static List<Kurir> daftarKurir = new ArrayList<>();
    private static User userLogin = null;

    public static void main(String[] args) {
        inisialisasiDataDummy();
        tampilkanMenuUtama();
    }

    public static List<Pengiriman> getDaftarPengiriman() {
            return daftarPengiriman;
        }

    private static List<Wilayah> daftarWilayah = new ArrayList<>();

    private static void inisialisasiDataDummy() {
    daftarWilayah.add(new Wilayah("Jakarta"));
    daftarWilayah.add(new Wilayah("Bandung"));
    daftarWilayah.add(new Wilayah("Surabaya"));
    daftarWilayah.add(new Wilayah("Madura"));

    
    // Contoh data dummy pengiriman:
    Wilayah jakarta = getWilayahByName("Jakarta");
    Wilayah bandung = getWilayahByName("Bandung");
    Wilayah surabaya = getWilayahByName("Surabaya");
    Wilayah madura = getWilayahByName("Madura");

    
    Pengirim pengirim1 = new Pengirim("Budi", "Jl. Merdeka", "08123", "-", false);
    Penerima penerima1 = new Penerima("Andi", "Jl. Sudirman", "08222", "-", false);
    Tarif tarif = new Tarif(1.5f, "reguler");
    
    daftarPengiriman.add(new Pengiriman(
        "RESI001", new Date(), 1.5, "Elektronik", "reguler",
        pengirim1, penerima1, "Belum Dibayar", "Dalam Proses",
        tarif, jakarta, bandung
    ));
}

    // Getter untuk daftar wilayah
    public static List<Wilayah> getDaftarWilayah() {
        return daftarWilayah;
    }

    // Method untuk memeriksa apakah wilayah valid
    public static boolean isWilayahValid(String namaWilayah) {
        for (Wilayah w : daftarWilayah) {
            if (w.getNamaWilayah().equalsIgnoreCase(namaWilayah)) {
                return true;
            }
        }
        return false;
    }

    // Method untuk mendapatkan objek Wilayah berdasarkan nama
    public static Wilayah getWilayahByName(String namaWilayah) {
        for (Wilayah w : daftarWilayah) {
            if (w.getNamaWilayah().equalsIgnoreCase(namaWilayah)) {
                return w;
            }
        }
        return null;
    }


    private static void tampilkanMenuUtama() {
        while (true) {
            System.out.println("\n=== SISTEM PENGIRIMAN PAKET ===");
            System.out.println("1. Login Admin");
            System.out.println("2. Login Kurir");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    loginKurir();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void loginAdmin() {
        System.out.println("\n=== LOGIN ADMIN ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            Admin admin = new Admin(username);
            userLogin = admin;
            System.out.println("Login berhasil sebagai Admin " + username);
            menuAdmin();
        } else {
            System.out.println("Username atau password salah!");
        }
    }

    private static void loginKurir() {
        System.out.println("\n=== LOGIN KURIR ===");
        System.out.print("ID Kurir: ");
        String idKurir = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Gunakan daftar kurir dari Admin
        List<Kurir> daftarKurir = Admin.getDaftarKurir();

        for (Kurir kurir : daftarKurir) {
            if (idKurir.equals(kurir.getIdKurir()) && password.equals(kurir.getPassword())) {
                userLogin = kurir;
                System.out.println("Login berhasil sebagai Kurir " + kurir.getNama());
                menuKurir();
                return;
            }
        }
        System.out.println("ID Kurir atau password salah!");
    }

    private static void menuAdmin() {
        Admin admin = (Admin) userLogin;
        
        while (true) {
            admin.tampilkanMenu();
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    admin.kelolaKurir();
                    break;
                case 2:
                    admin.buatPengiriman();
                    break;
                case 3:
                    admin.buatLaporan();
                    break;
                case 4:
                    userLogin = null;
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void menuKurir() {
        Kurir kurir = (Kurir) userLogin;
        
        while (true) {
            System.out.println("\n=== MENU KURIR ===");
            System.out.println("1. Lihat Pengiriman di Wilayah Saya");
            System.out.println("2. Update Status Pengiriman");
            System.out.println("2. Update Status Pembayaran");
            System.out.println("3. Lihat Riwayat");
            System.out.println("4. Logout");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    kurir.lihatPengirimanWilayah(getDaftarPengiriman());
                    break;
                case 2:
                    updateStatusPengiriman(kurir);
                    break;
                case 3:
                    updateStatusPembayaran(kurir);
                    break;
                case 4:
                    kurir.lihatRiwayat();
                    break;
                case 5:
                    userLogin = null;
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void updateStatusPengiriman(Kurir kurir) {
        System.out.println("\n=== UPDATE STATUS PENGIRIMAN ===");
        System.out.print("Masukkan ID Resi: ");
        String resi = scanner.nextLine();
        
        Pengiriman target = null;
        for (Pengiriman p : daftarPengiriman) {
            if (p.getIdResi().equals(resi)) {
                target = p;
                break;
            }
        }
        
        if (target == null) {
            System.out.println("Pengiriman tidak ditemukan!");
            return;
        }
        
        System.out.println("\nStatus saat ini: " + target.getStatusPengiriman());
        System.out.println("1. Dalam Proses");
        System.out.println("2. Dalam Pengantaran");
        System.out.println("3. Sudah Diterima");
        System.out.print("Pilih status baru: ");
        
        int status = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        switch (status) {
            case 1:
                kurir.updateStatus(target, "Dalam Proses");
                break;
            case 2:
                kurir.updateStatus(target, "Dalam Pengantaran");
                break;
            case 3:
                kurir.updateStatus(target, "Sudah Diterima");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

private static void updateStatusPembayaran(Kurir kurir) {
        System.out.println("\n=== UPDATE STATUS PEMBAYARAN ===");
        System.out.print("Masukkan ID Resi: ");
        String resi = scanner.nextLine();
        
        Pengiriman target = null;
        for (Pengiriman p : daftarPengiriman) {
            if (p.getIdResi().equals(resi)) {
                target = p;
                break;
            }
        }
        
        if (target == null) {
            System.out.println("Pengiriman tidak ditemukan!");
            return;
        }
        
        System.out.println("\nStatus saat ini: " + target.getStatusPengiriman());
        System.out.println("1. Sudah Dibayar");
        System.out.println("2. Belum Dibayar");
        System.out.print("Pilih status baru: ");
        
        int status = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        switch (status) {
            case 1:
                kurir.updateStatus(target, "Sudah Dibayar");
                break;
            case 2:
                kurir.updateStatus(target, "Belum Dibayar");
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
}