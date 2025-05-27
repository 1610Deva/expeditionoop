package User;

import Pengiriman.Pengiriman;
import Pengiriman.Wilayah;

public class Kurir extends User {
    private String nama;
    private String idKurir;
    private String password;
    private Wilayah wilayah;

    public Kurir(String nama, String idKurir, String password, Wilayah wilayah)
    {
        this.nama = nama;
        this.idKurir = idKurir;
        this.password = password;
        this.wilayah = wilayah;
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

    public void updateStatus(Pengiriman pengiriman, String status) {
        if (pengiriman != null) {
            pengiriman.updateStatusPengiriman(status);
            System.out.println("Kurir " + this.nama + " mengupdate status pengiriman " + 
                            pengiriman.getIdResi() + " menjadi: " + status);
        }
    }

    public void lihatRiwayat() {
        System.out.println("Riwayat pengiriman untuk kurir " + this.nama + " di wilayah " + 
                        this.wilayah.getNamaWilayah());
        // Logika untuk menampilkan riwayat pengiriman
    }

    public void tampilkanMenu()
    {
        System.out.println("Menu Kurir");
    }
}
