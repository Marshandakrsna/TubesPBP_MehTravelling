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
    private ProfilView view;
    @Mock
    private ProfilService service;
    private ProfilPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ProfilPresenter(view, service);

    }
    @Test
    public void shouldShowErrorMessageWhenFirstNameIsEmpty() throws Exception {
        when(view.getFirstName()).thenReturn("");
        System.out.println("Testing Pertama: Inputan Nama Depan Kosong");
        System.out.println("Nama Depan : " + view.getFirstName());
        presenter.onProfilClicked();
        verify(view).showFirstNameError("Nama Depan tidak boleh kosong");
    }
    @Test
    public void shouldShowErrorMessageWhenLastNameIsEmpty() throws Exception {
        System.out.println( "\n\n" + "Testing Kedua: Inputan Nama Belakang Kosong");
        when(view.getFirstName()).thenReturn("Her");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("");
        System.out.println("Nama Belakang : "+view.getLastName());
        presenter.onProfilClicked();
        verify(view).showLastNameError("Nama Belakang tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenSemesterIsEmpty() throws Exception {
        System.out.println( "\n\n" + "Testing ketiga: Inputan Semester Kosong");
        when(view.getFirstName()).thenReturn("Saya");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("Admin");
        System.out.println("Nama Belakang : "+view.getLastName());
        when(view.getSemester()).thenReturn("");
        System.out.println("Semester : "+view.getSemester());
        presenter.onProfilClicked();
        verify(view).showSemesterError("Semester tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenSemesterIsNotNumber() throws Exception {
        System.out.println( "\n\n" + "Testing keempat: Inputan Semester tidak angka dan tidak sesuai format");
        when(view.getFirstName()).thenReturn("Saya");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("Admin");
        System.out.println("Nama Belakang : "+view.getLastName());
        when(view.getSemester()).thenReturn("qwert");
        System.out.println("Semester : "+view.getSemester());
        presenter.onProfilClicked();
        verify(view).showSemesterError("Format Semester harus berupa angka 1 sampai 9");
    }

    @Test
    public void shouldShowErrorMessageWhenSemesterIsNumberButNotInRange() throws Exception {
        System.out.println( "\n\n" + "Testing kelima: Inputan Semester angka dan tidak sesuai dengan format");
        when(view.getFirstName()).thenReturn("Saya");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("Admin");
        System.out.println("Nama Belakang : "+view.getLastName());
        when(view.getSemester()).thenReturn("0");
        System.out.println("Semester : "+view.getSemester());
        presenter.onProfilClicked();
        verify(view).showSemesterError("Format Semester harus berupa angka 1 sampai 9");
    }

    @Test
    public void shouldShowErrorMessageWhenSemesterIsNumberButNotInRange2() throws Exception {
        System.out.println( "\n\n" + "Testing keenam: Inputan Semester angka dan tidak sesuai dengan format");
        when(view.getFirstName()).thenReturn("Saya");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("Admin");
        System.out.println("Nama Belakang : "+view.getLastName());
        when(view.getSemester()).thenReturn("10");
        System.out.println("Semester : "+view.getSemester());
        presenter.onProfilClicked();
        verify(view).showSemesterError("Format Semester harus berupa angka 1 sampai 9");
    }

    @Test
    public void shouldShowErrorMessageWhenTanggalLahirIsEmpty() throws Exception {
        System.out.println( "\n\n" + "Testing ketujuh: Inputan Tanggal Lahir kurang dari 6");
        when(view.getFirstName()).thenReturn("Saya");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("Admin");
        System.out.println("Nama Belakang : "+view.getLastName());
        when(view.getSemester()).thenReturn("6");
        System.out.println("Semester : "+view.getSemester());
        when(view.getTanggalLahir()).thenReturn("");
        System.out.println("Tanggal Lahir : "+view.getTanggalLahir());
        presenter.onProfilClicked();
        verify(view).showTanggalLahirError("Tanggal Lahir tidak boleh kosong");
    }

    @Test
    public void shouldShowErrorMessageWhenTanggalLahirIsLess6() throws Exception {
        System.out.println( "\n\n" + "Testing kedelapan: Inputan Tanggal Lahir kurang dari 6");
        when(view.getFirstName()).thenReturn("Saya");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("Admin");
        System.out.println("Nama Belakang : "+view.getLastName());
        when(view.getSemester()).thenReturn("6");
        System.out.println("Semester : "+view.getSemester());
        when(view.getTanggalLahir()).thenReturn("123");
        System.out.println("Tanggal Lahir : "+view.getTanggalLahir());
        presenter.onProfilClicked();
        verify(view).showTanggalLahirError("Tanggal Lahir tidak boleh kurang dari 6 digit");
    }

    @Test
    public void shouldShowErrorMessageWhenTanggalLahirIsMore6() throws Exception {
        System.out.println( "\n\n" + "Testing kedelapan: Inputan Tanggal Lahir lebih dari 6");
        when(view.getFirstName()).thenReturn("Saya");
        System.out.println("Nama Depan : "+ view.getFirstName());
        when(view.getLastName()).thenReturn("Admin");
        System.out.println("Nama Belakang : "+view.getLastName());
        when(view.getSemester()).thenReturn("6");
        System.out.println("Semester : "+view.getSemester());
        when(view.getTanggalLahir()).thenReturn("1234567");
        System.out.println("Tanggal Lahir : "+view.getTanggalLahir());
        presenter.onProfilClicked();
        verify(view).showTanggalLahirError("Tanggal Lahir tidak boleh lebih dari 6 digit");
    }
}