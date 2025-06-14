package Main;

import User.*;
import Pengiriman.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Pengiriman> daftarPengiriman = new ArrayList<>();
    private static User userLogin = null;

    // ANSI Color Codes - Warna yang lebih soft dan professional
    public static final String RESET = "\033[0m";
    public static final String BOLD = "\033[1m";
    public static final String DIM = "\033[2m";

    // Colors
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";
    public static final String GRAY = "\033[90;1m";

    // Background Colors (subtle)
    public static final String BG_LIGHT_GRAY = "\033[100m";
    public static final String BG_LIGHT_BLUE = "\033[104m";

    private static String formatStatus(String status) {
        switch (status) {
            case "Dalam Proses":
                return YELLOW + "⏳ " + status + RESET;
            case "Dalam Pengantaran":
                return BLUE + "🚚 " + status + RESET;
            case "Sudah Diterima":
                return GREEN + "✓ " + status + RESET;
            case "Sudah Dibayar":
                return GREEN + "💰 " + status + RESET;
            case "Belum Dibayar":
                return RED + "💸 " + status + RESET;
            default:
                return status;
        }
    }

    public static void main(String[] args) {
        tampilkanSampleCredentials();
        tampilkanWelcome();
        tampilkanMenuUtama();
    }

    private static void tampilkanSampleCredentials() {
        clearScreen();
        System.out.println();
        System.out.println(BOLD + BLUE + "                  🔑 KREDENSIAL LOGIN SISTEM" + RESET);
        System.out.println("                    PT SOLUSI KIRIM INDONESIA" + RESET);
        printLine(78, WHITE);
        System.out.println();

        // Admin section - style yang konsisten dengan menu lainnya
        System.out.println("    " + BLUE + BOLD + "👨‍💼 ADMINISTRATOR" + RESET);
        System.out.println("    " + BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.println("       👤 Username  : " + GREEN + BOLD + "admin" + RESET);
        System.out.println("       🔑 Password  : " + GREEN + BOLD + "admin123" + RESET);
        System.out.println();
        System.out.println("       " + WHITE + "✓ Kelola sistem, kurir, dan buat laporan" + RESET);
        System.out.println("       " + WHITE + "✓ Akses penuh dashboard administrator" + RESET);
        System.out.println("       " + WHITE + "✓ Buat dan kelola pengiriman" + RESET);
        System.out.println();
        System.out.println("    " + BLUE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();

        // Info section
        System.out.println("    " + PURPLE + BOLD + "📋 INFORMASI SISTEM" + RESET);
        System.out.println("    " + PURPLE + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.println("       🏢 Kantor Pusat : Jakarta Selatan");
        System.out.println("       📞 Hotline      : (021) 1234-5678");
        System.out.println("       📧 Support      : support@solusikirm.com");
        System.out.println("       🌐 Website      : www.solusikirm.com");
        System.out.println();

        // Security notice
        System.out.println("    " + YELLOW + "⚠️  PENTING:" + RESET);
        System.out.println("        • Gunakan kredensial sesuai dengan peran Anda");
        System.out.println("        • Jaga kerahasiaan password untuk keamanan sistem");
        System.out.println("        • Logout setelah selesai menggunakan sistem");
        System.out.println();

        printLine(78, WHITE);
        System.out.println("     " + CYAN + "💡 TIP:" + RESET + " Data kurir akan ditambahkan melalui menu admin");
        System.out.println("         " + GRAY + "Setiap kurir memiliki wilayah kerja masing-masing" + RESET);
        System.out.println();
        
        System.out.print(DIM + "  Tekan ENTER untuk melanjutkan..." + RESET);
        scanner.nextLine();
    }

    public static List<Pengiriman> getDaftarPengiriman() {
        return daftarPengiriman;
    }

    private static List<Wilayah> daftarWilayah = new ArrayList<>();

    private static void tampilkanWelcome() {
        // Header dengan logo sederhana
        System.out.println(CYAN + BOLD + " ┌─────────────────────────────────────────────────────┐");
    System.out.println(" │                                                     │");
    System.out.println(BOLD + " │           🚚 SISTEM EKSPEDISI TERPADU 📦            │"); // Diperbaiki dari |
    System.out.println(" │                                                     │");
    System.out.println(" │         Sistem Manajemen Pengiriman Paket v1.0      │");
    System.out.println(" │                                                     │");
    System.out.println(" └─────────────────────────────────────────────────────┘" + RESET);

        // System.out.println(GRAY + " Loading sistem" + RESET);
        // for (int i = 0; i < 3; i++) {
        //     try {
        //         Thread.sleep(400);
        //         System.out.print(".");
        //     } catch (InterruptedException e) {
        //         Thread.currentThread().interrupt();
        //     }
        // }

        // Loading animation dengan progress bar
    System.out.println(GRAY + "    🔄 Memuat sistem..." + RESET);
    System.out.print("    [");
    
    

    for (int i = 0; i < 50; i++) {
        try {
            Thread.sleep(50);
            if (i < 12) {
                System.out.print(YELLOW + "█" + RESET);
            } else if (i < 25) {
                System.out.print(YELLOW + "█" + RESET);
            } else if (i < 37) {
                System.out.print(YELLOW + "█" + RESET);
            } else {
                System.out.print(YELLOW + "█" + RESET);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    System.out.println("] " + GREEN + BOLD + "100% ✓ SIAP!" + RESET);
    System.out.println();
    
    // Welcome message dengan efek typing
    String[] messages = {
        "    🎯 Sistem siap digunakan!",
        "    📦 Kelola pengiriman dengan mudah",
        "    🚚 Tracking real-time tersedia",
        "    💼 Dashboard terintegrasi"
    };
    
    for (String msg : messages) {
        typeWriter(msg, 30);
        System.out.println();
    }

    // Method baru untuk efek typing

        System.out.println();
        System.out.print(DIM + "Tekan ENTER untuk melanjutkan..." + RESET);
        scanner.nextLine();
    }

    private static void typeWriter(String text, int delay) {
    for (char c : text.toCharArray()) {
        System.out.print(c);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

    private static void clearScreen() {
        System.out.print("\033[2J\033[H");
        System.out.flush();
    } 

    private static void printLine(int length, String color) {
        System.out.print(color);
        for (int i = 0; i < length; i++) {
            System.out.print("─");
        }
        System.out.println(RESET);
    }

    private static void showMessage(String message, String type) {
        String icon = "";
        String color = "";

        switch (type.toLowerCase()) {
            case "success":
                icon = "✓";
                color = GREEN;
                break;
            case "error":
                icon = "✗";
                color = RED;
                break;
            case "info":
                icon = "ℹ";
                color = BLUE;
                break;
            case "warning":
                icon = "⚠";
                color = YELLOW;
                break;
        }

        System.out.println();
        System.out.println(color + "  " + icon + " " + message + RESET);
        System.out.println();

        if (!type.equals("info")) {
            System.out.print(DIM + "Tekan ENTER untuk melanjutkan..." + RESET);
            scanner.nextLine();
        }
    }


    public static List<Wilayah> getDaftarWilayah() {
        return daftarWilayah;
    }

    private static void tampilkanMenuUtama() {
        while (true) {
            clearScreen();

            // Header dengan judul di tengah
            System.out.println();
            System.out.println();
            System.out.println(CYAN + BOLD + "                          📋 MENU UTAMA" + RESET);
            System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
            printLine(68, GRAY);
            System.out.println();

            // Menu items
            System.out.println("     " + BLUE + "1." + RESET + "  Login Administrator   " + GRAY + "│ Kelola sistem & pengiriman" + RESET);
            System.out.println("     " + GREEN + "2." + RESET + "  Login Kurir          " + GRAY + " │ Update status pengiriman" + RESET);
            System.out.println("     " + RED + "3." + RESET + "  Keluar               " + GRAY + " │ Tutup aplikasi" + RESET);

            System.out.println();
            printLine(68, GRAY);
            System.out.print("  " + YELLOW + "⚡ Pilihan Anda [1-3]: " + RESET);


                   String input = scanner.nextLine();
        
            try {
                int pilihan = Integer.parseInt(input);
                
                switch (pilihan) {
                    case 1:
                        loginAdmin();
                        break;
                    case 2:
                        loginKurir();
                        break;
                    case 3:
                        tampilkanPesanKeluar();
                        System.exit(0);
                    default:
                        showMessage("Pilihan harus antara 1-3! Silakan coba lagi.", "error");
                }
            } catch (NumberFormatException e) {
                showMessage("Input harus berupa angka (1-3)! Silakan coba lagi.", "error");
            }
        }
    }

    private static void tampilkanPesanKeluar() {
        clearScreen();
        System.out.println();
        System.out.println(PURPLE + BOLD + "                    👋 TERIMA KASIH!" + RESET);
        System.out.println(GRAY + "                 PT SOLUSI KIRIM INDONESIA" + RESET);
        printLine(68, GRAY);
        System.out.println();
        System.out.println("     Terima kasih telah menggunakan Expedition System");
        System.out.println("     Sistem pengiriman terpercaya untuk kebutuhan Anda");
        System.out.println();
        System.out.println(GRAY + "     📧 support@expedition.com  │  📞 (021) 1234-5678" + RESET);
        System.out.println();
        printLine(68, GRAY);

        for (int i = 3; i > 0; i--) {
            System.out.print("\r  " + YELLOW + "Sistem akan tertutup dalam " + i + " detik..." + RESET);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    private static void loginAdmin() {
        clearScreen();
        System.out.println();
        System.out.println(BLUE + BOLD + "                       🔐 LOGIN ADMINISTRATOR" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        printLine(68, GRAY);
        System.out.println();

        System.out.print("     Username: ");
        String username = scanner.nextLine();

        System.out.print("     Password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            Admin admin = new Admin(username);
            userLogin = admin;
            showMessage("Login berhasil! Selamat datang, Admin " + username, "success");
            menuAdmin();
        } else {
            showMessage("Username atau password salah!", "error");
        }
    }

    private static void loginKurir() {
        clearScreen();
        System.out.println();
        System.out.println(GREEN + BOLD + "                           🚚 LOGIN KURIR" + RESET);
        System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
        printLine(68, GRAY);
        System.out.println();

        System.out.print("     ID Kurir: ");
        String idKurir = scanner.nextLine();

        System.out.print("     Password: ");
        String password = scanner.nextLine();

        List<Kurir> daftarKurir = Admin.getDaftarKurir();

        for (Kurir kurir : daftarKurir) {
            if (idKurir.equals(kurir.getIdKurir()) && password.equals(kurir.getPassword())) {
                userLogin = kurir;
                showMessage("Login berhasil! Selamat datang, " + kurir.getNama(), "success");
                menuKurir();
                return;
            }
        }

        showMessage("ID Kurir atau password salah!", "error");
    }

    private static void menuAdmin() {
        Admin admin = (Admin) userLogin;

        while (true) {
            clearScreen();
            System.out.println();
            System.out.println(BLUE + BOLD + "                     💼 DASHBOARD ADMINISTRATOR" + RESET);
            System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
            printLine(68, GRAY);
            System.out.println();

            System.out.println("     " + CYAN + "1." + RESET + "  Kelola Kurir         " + GRAY + "│ Tambah & lihat daftar kurir" + RESET);
            System.out.println("     " + GREEN + "2." + RESET + "  Buat Pengiriman      " + GRAY + "│ Input pengiriman baru" + RESET);
            System.out.println("     " + YELLOW + "3." + RESET + "  Buat Laporan         " + GRAY + "│ Generate laporan sistem" + RESET);
            System.out.println("     " + RED + "4." + RESET + "  Logout               " + GRAY + "│ Kembali ke menu utama" + RESET);

            System.out.println();
            printLine(68, GRAY);
            System.out.print("  " + YELLOW + "⚡ Pilihan Anda [1-4]: " + RESET);

            String input = scanner.nextLine();
        
            try {
                int pilihan = Integer.parseInt(input);
                
                switch (pilihan) {
                    case 1:
                        admin.kelolaKurir();
                        pauseScreen();
                        break;
                    case 2:
                        admin.buatPengiriman();
                        pauseScreen();
                        break;
                    case 3:
                        admin.buatLaporan();
                        pauseScreen();
                        break;
                    case 4:
                        showMessage("Logout berhasil! Kembali ke menu utama...", "info");
                        userLogin = null;
                        return;
                    default:
                        showMessage("Pilihan harus antara 1-4! Silakan coba lagi.", "error");
                }
            } catch (NumberFormatException e) {
                showMessage("Input harus berupa angka (1-4)! Silakan coba lagi.", "error");
            }
        }
    }

    private static void menuKurir() {
        Kurir kurir = (Kurir) userLogin;

        while (true) {
            clearScreen();
            System.out.println();
            System.out.println(GREEN + BOLD + "                       🚚 DASHBOARD KURIR" + RESET);
            System.out.println("                   🚚 PT SOLUSI KIRIM INDONESIA 📦" + RESET);
            System.out.println("            " + GRAY + "Halo, " + kurir.getNama() + " | Wilayah: " + kurir.getWilayah().getKelurahan() + RESET);
            printLine(68, GRAY);
            System.out.println();

            System.out.println("     " + CYAN + "1." + RESET + "  Lihat Pengiriman     " + GRAY + "│ Pengiriman di wilayah Anda" + RESET);
            System.out.println("     " + BLUE + "2." + RESET + "  Update Status        " + GRAY + "│ Update status pengiriman" + RESET);
            System.out.println("     " + PURPLE + "3." + RESET + "  Update Pembayaran    " + GRAY + "│ Update status pembayaran" + RESET);
            System.out.println("     " + YELLOW + "4." + RESET + "  Lihat Riwayat        " + GRAY + "│ Riwayat aktivitas Anda" + RESET);
            System.out.println("     " + RED + "5." + RESET + "  Logout               " + GRAY + "│ Kembali ke menu utama" + RESET);

            System.out.println();
            printLine(68, GRAY);
            System.out.print("  " + YELLOW + "⚡ Pilihan Anda [1-5]: " + RESET);

            String input = scanner.nextLine();
        
            try {
                int pilihan = Integer.parseInt(input);
                
                switch (pilihan) {
                    case 1:
                        clearScreen();
                        System.out.println();
                        System.out.println(CYAN + BOLD + "  📋 PENGIRIMAN DI WILAYAH " + kurir.getWilayah().getKelurahan().toUpperCase() + RESET);
                        printLine(68, GRAY);
                        kurir.lihatPengirimanWilayah(getDaftarPengiriman());
                        pauseScreen();
                        break;
                    case 2:
                        updateStatusPengiriman(kurir);
                        break;
                    case 3:
                        updateStatusPembayaran(kurir);
                        break;
                    case 4:
                        clearScreen();
                        kurir.lihatRiwayat();
                        pauseScreen();
                        break;
                    case 5:
                        showMessage("Logout berhasil! Kembali ke menu utama...", "info");
                        userLogin = null;
                        return;
                    default:
                        showMessage("Pilihan harus antara 1-5! Silakan coba lagi.", "error");
                }
            } catch (NumberFormatException e) {
                showMessage("Input harus berupa angka (1-5)! Silakan coba lagi.", "error");
            }
        }
    }

    private static void pauseScreen() {
        System.out.println();
        System.out.print(DIM + "Tekan ENTER untuk kembali ke menu..." + RESET);
        scanner.nextLine();
    }

    private static void updateStatusPengiriman(Kurir kurir) {
        clearScreen();
        System.out.println();
        System.out.println(BLUE + BOLD + "  🔄 UPDATE STATUS PENGIRIMAN" + RESET);
        printLine(68, GRAY);
        System.out.println();

        System.out.print("     ID Resi: ");
        String resi = scanner.nextLine();

        Pengiriman target = null;
        for (Pengiriman p : daftarPengiriman) {
            if (p.getIdResi().equals(resi)) {
                target = p;
                break;
            }
        }

        if (target == null) {
            showMessage("Pengiriman dengan ID '" + resi + "' tidak ditemukan!", "error");
            return;
        }

        System.out.println();
        System.out.println("     Status saat ini: " + BOLD + target.getStatusPengiriman() + RESET);
        System.out.println();
        System.out.println("     " + YELLOW + "1." + RESET + "  Dalam Proses");
        System.out.println("     " + BLUE + "2." + RESET + "  Dalam Pengantaran");
        System.out.println("     " + GREEN + "3." + RESET + "  Sudah Diterima");

        System.out.println();
        printLine(68, GRAY);
        System.out.print("  " + YELLOW + "Pilih status baru [1-3]: " + RESET);

        String input = scanner.nextLine();
        try {
            int status = Integer.parseInt(input);
            scanner.nextLine();

            String statusBaru = "";
            switch (status) {
                case 1:
                    statusBaru = "Dalam Proses";
                    break;
                case 2:
                    statusBaru = "Dalam Pengantaran";
                    break;
                case 3:
                    statusBaru = "Sudah Diterima";
                    break;
                default:
                    showMessage("Pilihan tidak valid! Gunakan angka 1-3.", "error");
                    return;
            }

            kurir.updateStatus(target, statusBaru);
            showMessage("Status pengiriman " + resi + " berhasil diubah menjadi: " + statusBaru, "success");

        } catch (InputMismatchException e) {
            showMessage("Input tidak valid! Masukkan angka saja.", "error");
            scanner.nextLine();
        }
    }

    private static void updateStatusPembayaran(Kurir kurir) {
        clearScreen();
        System.out.println();
        System.out.println(PURPLE + BOLD + "  💳 UPDATE STATUS PEMBAYARAN" + RESET);
        printLine(68, GRAY);
        System.out.println();

        System.out.print("     ID Resi: ");
        String resi = scanner.nextLine();

        Pengiriman target = null;
        for (Pengiriman p : daftarPengiriman) {
            if (p.getIdResi().equals(resi)) {
                target = p;
                break;
            }
        }

        if (target == null) {
            showMessage("Pengiriman dengan ID '" + resi + "' tidak ditemukan!", "error");
            return;
        }

        System.out.println();
        System.out.println("     Status pembayaran saat ini: " + BOLD + target.getStatusPembayaran() + RESET);
        System.out.println();
        System.out.println("     " + GREEN + "1." + RESET + "  Sudah Dibayar");
        System.out.println("     " + RED + "2." + RESET + "  Belum Dibayar");

        System.out.println();
        printLine(68, GRAY);
        System.out.print("  " + YELLOW + "Pilih status baru [1-2]: " + RESET);

        String input = scanner.nextLine();
        try {
            int status = Integer.parseInt(input);
            scanner.nextLine();

            String statusBaru = "";
            switch (status) {
                case 1:
                    statusBaru = "Sudah Dibayar";
                    break;
                case 2:
                    statusBaru = "Belum Dibayar";
                    break;
                default:
                    showMessage("Pilihan tidak valid! Gunakan angka 1-2.", "error");
                    return;
            }

            kurir.updateStatusBayar(target, statusBaru);
            showMessage("Status pembayaran " + resi + " berhasil diubah menjadi: " + statusBaru, "success");

        } catch (InputMismatchException e) {
            showMessage("Input tidak valid! Masukkan angka saja.", "error");
            scanner.nextLine();
        }
    }
}