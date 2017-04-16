package id.ac.tazkia.dosen.controller;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuratTugasTests {

    private WebDriver webDriver = new HtmlUnitDriver();

    @LocalServerPort
    private int serverPort;

    private Faker faker = new Faker();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testListView() throws Exception {
        String baseUrl = String.format("http://localhost:%d", serverPort);
        webDriver.get(baseUrl + "/surattugas/list");
        assertThat(webDriver.getTitle()).isEqualTo("Aplikasi Dosen :: Surat Tugas");
    }

    @Test
    public void testSubmitFormGagal() throws Exception {
        String baseUrl = String.format("http://localhost:%d", serverPort);
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
