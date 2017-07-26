package id.ac.tazkia.dosen.controller;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DosenTests extends BaseSeleniumTests {

    private String urlListView = "/dosen/list";
    private String titleListView = "Aplikasi Dosen :: Daftar Dosen";
    private String urlFormView = "/dosen/form";
    private String titleFormView = "Aplikasi Dosen :: Edit Data Dosen";

//    @Test
    public void testListView() throws Exception {
        webDriver.get(baseUrl + urlListView);
        assertThat(webDriver.getTitle()).isEqualTo(titleListView);
    }

//    @Test
    public void testFormView() throws Exception {
        webDriver.get(baseUrl + urlFormView);
        assertThat(webDriver.getTitle()).isEqualTo(titleFormView);
    }
}
