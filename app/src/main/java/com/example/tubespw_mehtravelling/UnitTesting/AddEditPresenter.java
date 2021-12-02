package com.example.tubespw_mehtravelling.UnitTesting;

    //    TODO 9: double click ProfilPresenter -> klik kanan, show context actions, create test,
    //     JUnit4, pilih setup/@Before , dan pilih lokasi 2 (lokasi 1 untuk instrumented test)

    //    TODO 10: nanti akan terbuat kelas ProfilPresenterTest.

//TODO 11: [TODO INI PINDAHKAN KE KELAS ProfilPresenterTest];
// Jangan lupa perhatikan diatas public clas blabla ada tambahan code, code deklarasi, dan di void SetUp(),
// Kemudian pindahkan code selanjutnya untuk melakukan pengujian

//TODO 12: [TODO INI PINDAHKAN KE KELAS ProfilPresenterTest];
// Kalian boleh coba pengujian secara menyeluruh dengan pilih icon play di public class ProfilPresenterTest
// Atau uji salah satu prosedur (lihat contohnya di modul)


import com.example.tubespw_mehtravelling.survey.models.Survey;

public class AddEditPresenter {

    //    TODO 8: silahkan salin code ProfilPresenter
    private AddEditView view;
    private AddEditService service;
    private AddEditCallback callback;
    private Survey survey;
    public AddEditPresenter(AddEditView view, AddEditService service) {
        this.view = view;
        this.service = service;
    }
    public void onSurveyClicked() {
//        String regex = "[1-9]+";
//        String regex1 = "[a-zA-Z]";

        if (view.getNamaDestinasi().isEmpty()) {
            view.showNamaDestinasiError("Nama Destinasi tidak boleh kosong");
            return;
        } else if (view.getNamaPengguna().isEmpty()) {
            view.showNamaPenggunaError("Nama Pengguna tidak boleh kosong");
            return;
        } else if(view.getPenilaian().isEmpty()){
            view.showPenilaianError("Penilaian tidak boleh kosong");
            return;
        } else if(view.getAlasan().isEmpty()){
            view.showAlasanError("Alasan tidak boleh kosong");
            return;
        } else {
            service.survey(view, survey, new AddEditCallback() {
                @Override
                public void onSuccess(boolean value, Survey survey)
                {
                    view.startMainAddEdit();
                }
                @Override
                public void onError() {
                }
            });
            return;
        }
    }
}
