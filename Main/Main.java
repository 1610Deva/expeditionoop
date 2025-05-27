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

    private static void inisialisasiDataDummy() {
        // Data Wilayah
        Wilayah jakarta = new Wilayah("Jakarta", Arrays.asList("Kecamatan A", "Kecamatan B"));
        Wilayah bandung = new Wilayah("Bandung", Arrays.asList("Kecamatan C", "Kecamatan D"));
        Wilayah surabaya = new Wilayah("Surabaya", Arrays.asList("Kecamatan E", "Kecamatan F"));

        // Data Pelanggan
        Pengirim pengirim1 = new Pengirim("Budi", "Jl. Merdeka No.1", "08123456789", "Fragile", false);
        Penerima penerima1 = new Penerima("Andi", "Jl. Sudirman No.2", "08987654321", "Antar di pagi hari", false);

        // Data Tarif
        Tarif tarifReguler = new Tarif(12000, 5000, "reguler");
        Tarif tarifEkspres = new Tarif(15000, 7000, "ekspres");
        Tarif tarifEkonomi = new Tarif(8000, 3000, "ekonomi");

        // Data Kurir - tambahkan ke Admin
        Admin.getDaftarKurir().add(new Kurir("Joko", "KUR001", "Joko123", jakarta));


        // Data Pengiriman
        daftarPengiriman.add(new Pengiriman(
            "RESI001", new Date(), 2.5, "Elektronik", "reguler",
            pengirim1, penerima1, "Belum Dibayar", "Dalam Proses",
            tarifReguler, jakarta, bandung, 120.5
        ));

        daftarPengiriman.add(new Pengiriman(
            "RESI001", new Date(), 2.5, "Elektronik", "reguler",
            pengirim1, penerima1, "Belum Dibayar", "Dalam Proses",
            tarifEkspres, jakarta, bandung, 120.5
        ));

        // Data Kurir
        daftarKurir.add(new Kurir("Joko", "KUR001", "Joko123", jakarta));
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
            System.out.println("Login berhasil sebagai " + username);
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
            System.out.println("3. Lihat Riwayat");
            System.out.println("4. Logout");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    lihatPengirimanWilayah(kurir.getWilayah());
                    break;
                case 2:
                    updateStatusPengiriman(kurir);
                    break;
                case 3:
                    kurir.lihatRiwayat();
                    break;
                case 4:
                    userLogin = null;
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void lihatPengirimanWilayah(Wilayah wilayah) {
        System.out.println("\n=== PENGIRIMAN DI WILAYAH " + wilayah.getNamaWilayah() + " ===");
        boolean found = false;
        
        for (Pengiriman p : daftarPengiriman) {
            if (p.getTujuan().getNamaWilayah().equalsIgnoreCase(wilayah.getNamaWilayah())) {
                System.out.println("ID Resi: " + p.getIdResi());
                System.out.println("Penerima: " + p.getPenerima().getNama());
                System.out.println("Alamat: " + p.getPenerima().getAlamat());
                System.out.println("Status: " + p.getStatusPengiriman());
                System.out.println("-----------------------------");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Tidak ada pengiriman di wilayah ini.");
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
}