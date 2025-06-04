package Pelanggan;

public abstract class Pelanggan {

    // Atribut
    String nama;
    String noKontak;

    // Constructor default
    public Pelanggan() {
        this.nama = "";
        this.noKontak = "";
    }

    // Constructor dengan parameter
    public Pelanggan(String nama, String noKontak) {
        this.nama = nama;
        this.noKontak = noKontak;
    }

    // Getter
    public String getNama() {
        return nama;
    }
    public String getNoKontak() {
        return noKontak;
    }

    // Setter
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNoKontak(String noKontak) {
        this.noKontak = noKontak;
    }

    // Update Kontak Method
    public void updateKontak(String kontakBaru){
        this.noKontak = kontakBaru;
        System.out.println("Kontak berhasil diupdate menjadi: " + kontakBaru);
    };

}
