package Pelanggan;

public class Penerima extends Pelanggan {


    // Constructor default
    public Penerima() {
        super(); // memanggil constructor parent
    }

    // Constructor dengan parameter lengkap
    public Penerima(String nama, String noKontak) {
        super(nama, noKontak); // memanggil constructor parent
    }
} 

