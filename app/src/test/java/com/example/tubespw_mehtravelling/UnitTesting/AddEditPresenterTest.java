package com.example.tubespw_mehtravelling.UnitTesting;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddEditPresenterTest {

    @Mock
    private AddEditView view;
    @Mock
    private AddEditService service;
    private AddEditPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new AddEditPresenter(view, service);

    }
    @Test
    public void shouldShowErrorMessageWhenNamaDestinasiIsEmpty() throws Exception {
        when(view.getNamaDestinasi()).thenReturn("");
        System.out.println("Testing Pertama: Inputan Nama Destinasi Kosong");
        System.out.println("Nama Destinasi : " + view.getNamaDestinasi());
        presenter.onSurveyClicked();
        verify(view).showNamaDestinasiError("Nama Destinasi tidak boleh kosong");
    }
    @Test
    public void shouldShowErrorMessageWhenNamaPenggunaIsEmpty() throws Exception {
        System.out.println( "\n\n" + "Testing Kedua: Inputan Nama Pengguna Kosong");
        when(view.getNamaDestinasi()).thenReturn("Malioboro");
        System.out.println("Nama Destinasi : "+ view.getNamaDestinasi());
        when(view.getNamaPengguna()).thenReturn("");
        System.out.println("Nama Pengguna : "+view.getNamaPengguna());
        presenter.onSurveyClicked();
        verify(view).showNamaPenggunaError("Nama Pengguna tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenPenilaianIsEmpty() throws Exception {
        System.out.println( "\n\n" + "Testing ketiga: Inputan Penilaian Kosong");
        when(view.getNamaDestinasi()).thenReturn("Malioboro");
        System.out.println("Nama Destinasi : "+ view.getNamaDestinasi());
        when(view.getNamaPengguna()).thenReturn("Marsha");
        System.out.println("Nama Pengguna : "+view.getNamaPengguna());
        when(view.getPenilaian()).thenReturn("");
        System.out.println("Penilaian : "+view.getPenilaian());
        presenter.onSurveyClicked();
        verify(view).showPenilaianError("Penilaian tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenAlasanIsEmpty() throws Exception {
        System.out.println( "\n\n" + "Testing keempat: Inputan Alasan tidak boleh kosong");
        when(view.getNamaDestinasi()).thenReturn("Malioboro");
        System.out.println("Nama Destinasi : "+ view.getNamaDestinasi());
        when(view.getNamaPengguna()).thenReturn("Marsha");
        System.out.println("Nama Pengguna : "+view.getNamaPengguna());
        when(view.getPenilaian()).thenReturn("6");
        System.out.println("Penilaian : "+view.getPenilaian());
        when(view.getAlasan()).thenReturn("");
        System.out.println("Alasan : "+view.getAlasan());
        presenter.onSurveyClicked();
        verify(view).showAlasanError("Alasan tidak boleh kosong");
    }
}