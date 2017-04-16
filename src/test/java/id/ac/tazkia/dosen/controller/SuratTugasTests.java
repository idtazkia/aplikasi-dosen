package id.ac.tazkia.dosen.controller;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

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

        webDriver.findElement(By.name("noSurat")).submit();
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

        webDriver.findElement(By.name("noSurat")).sendKeys(faker.letterify("SK-???"));
        webDriver.findElement(By.name("tanggalMulai")).sendKeys(dateFormat.format(faker.date().future(1, TimeUnit.DAYS)));
        webDriver.findElement(By.name("tanggalSelesai")).sendKeys(dateFormat.format(faker.date().future(100, TimeUnit.DAYS)));
        new Select(webDriver.findElement(By.name("penerima")))
                .selectByVisibleText("Dosen 2");
        new Select(webDriver.findElement(By.name("jenisSurat")))
                .selectByVisibleText("Jenis Surat 1");

        webDriver.findElement(By.name("noSurat")).submit();
        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Surat Tugas");
    }
}
