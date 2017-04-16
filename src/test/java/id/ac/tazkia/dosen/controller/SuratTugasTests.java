package id.ac.tazkia.dosen.controller;

import com.github.javafaker.Faker;
import id.ac.tazkia.dosen.pageobject.SuratTugasFormPage;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SuratTugasTests extends BaseSeleniumTests {

    private Faker faker = new Faker(new Locale("in-ID"));
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testListView() throws Exception {
        webDriver.get(baseUrl + "/surattugas/list");
        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Surat Tugas");
    }

    @Test
    public void testSubmitFormGagal() throws Exception {
        webDriver.get(baseUrl + "/surattugas/form");
        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Edit Surat Tugas");

        SuratTugasFormPage page = PageFactory.initElements(webDriver, SuratTugasFormPage.class);
        page.submitInvalid();
        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Edit Surat Tugas");
        assertThat(webDriver.getPageSource())
                .contains("may not be null")
                .contains("may not be empty")
                .contains("size must be between 3 and 255");

    }

    @Test
    public void testSubmitFormSukses() throws Exception {
        webDriver.get(baseUrl + "/surattugas/form");
        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Edit Surat Tugas");

        SuratTugasFormPage page = PageFactory.initElements(webDriver, SuratTugasFormPage.class);
        page.submitValid(faker.letterify("SK-???"),
                dateFormat.format(faker.date().future(1, TimeUnit.DAYS)),
                dateFormat.format(faker.date().future(100, TimeUnit.DAYS)),
                "Dosen 2",
                "Jenis Surat 1"
        );

        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Surat Tugas");
    }
}
