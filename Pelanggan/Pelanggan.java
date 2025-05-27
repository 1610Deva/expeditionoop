package Pelanggan;

public abstract class Pelanggan {

    // Atribut
    String nama;
    String alamat;
    String noKontak;

    // Constructor default
    public Pelanggan() {
        this.nama = "";
        this.alamat = "";
        this.noKontak = "";
    }

    // Constructor dengan parameter
    public Pelanggan(String nama, String alamat, String noKontak) {
        this.nama = nama;
        this.alamat = alamat;
        this.noKontak = noKontak;
    }

    // Getter
    public String getNama() {
        return nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public String getNoKontak() {
        return noKontak;
    }

    // Setter
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public void setNoKontak(String noKontak) {
        this.noKontak = noKontak;
    }

    // Abstract Method
    public abstract void terimaNotifikasi(String pesan);

    // Tampilkan info
    public void tampilkanInfo(){
        System.out.println("=== INFORMASI PELANGGAN ===");
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("No Kontak: " + noKontak);
        System.out.println("============================");
    }

    // Update Kontak Method
    public void updateKontak(String kontakBaru){
        this.noKontak = kontakBaru;
        System.out.println("Kontak berhasil diupdate menjadi: " + kontakBaru);
    };

}
