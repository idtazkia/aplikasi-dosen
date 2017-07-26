package id.ac.tazkia.dosen.controller;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeTests extends BaseSeleniumTests {
    private String urlView = "/";
    private String titleView = "Aplikasi Dosen :: Home";

//    @Test
    public void testListView() throws Exception {
        webDriver.get(baseUrl + urlView);
        assertThat(webDriver.getTitle()).isEqualTo(titleView);
    }

}
