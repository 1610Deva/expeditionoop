package Pengiriman;
import java.util.Date;

public class StatusPengiriman {
    private String statusSaatIni;
    private Date waktuUpdate;
    
    public StatusPengiriman(String statusSaatIni, Date waktuUpdate){
        this.statusSaatIni = statusSaatIni;
        this.waktuUpdate = waktuUpdate;
    }

    public String getStatusSaatIni() {
        return statusSaatIni;
    }
    public void setStatusSaatIni(String statusSaatIni) {
        this.statusSaatIni = statusSaatIni;
    }
    public Date getWaktuUpdate() {
        return waktuUpdate;
    }
    public void setWaktuUpdate(Date waktuUpdate) {
        this.waktuUpdate = waktuUpdate;
    }
    public void updateStatus(String statusBaru) {
        this.statusSaatIni = statusBaru;
        this.waktuUpdate = new Date(); // Set waktu update ke waktu saat ini
    }
}
