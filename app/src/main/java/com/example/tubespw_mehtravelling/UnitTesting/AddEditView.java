package com.example.tubespw_mehtravelling.UnitTesting;

public interface AddEditView {

    //    TODO 5: silahkan salin code ProfilView

    String getNamaDestinasi();
    void showNamaDestinasiError(String message);

    String getNamaPengguna();
    void showNamaPenggunaError(String message);

    String getPenilaian();
    void showPenilaianError(String message);

    String getAlasan();
    void showAlasanError(String message);

    void startMainAddEdit();

    void showSurveyError(String message);
    void showErrorSurveyResponse(String message);

}
