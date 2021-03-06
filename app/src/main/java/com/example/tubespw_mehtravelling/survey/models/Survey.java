package com.example.tubespw_mehtravelling.survey.models;

public class Survey {
    private Long id;
    private String namaPengguna;
    private String namaDestinasi;
    private String penilaian;
    private String alasan;

    public Survey(String namaDestinasi, String namaPengguna, String alasan, String penilaian) {
        this.namaDestinasi = namaDestinasi;
        this.namaPengguna = namaPengguna;
        this.alasan = alasan;
        this.penilaian = penilaian;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getNamaDestinasi() {
        return namaDestinasi;
    }

    public void setNamaDestinasi(String namaDestinasi) {
        this.namaDestinasi = namaDestinasi;
    }

    public String getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(String penilaian) {
        this.penilaian = penilaian;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }
}
