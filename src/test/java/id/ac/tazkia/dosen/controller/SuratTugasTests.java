package id.ac.tazkia.dosen.controller;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

public class SuratTugasTests extends BaseSeleniumTests {

    private WebDriver webDriver = new HtmlUnitDriver();

    private Faker faker = new Faker();
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

        webDriver.findElement(By.name("noSurat")).submit();
        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Edit Surat Tugas");
        assertThat(webDriver.getPageSource())
                .contains("may not be null")
                .contains("may not be empty")
                .contains("size must be between 3 and 255");

    }
}
