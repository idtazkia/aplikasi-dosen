package id.ac.tazkia.dosen.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DosenController.class)
public class DosenTests {
    @Autowired
    private MockMvc mockMvc;

    private String urlListView = "/dosen/list";
    private String titleListView = "Aplikasi Dosen :: Daftar Dosen";
    private String urlFormView = "/dosen/form";
    private String titleFormView = "Aplikasi Dosen :: Edit Data Dosen";

    @Test
    public void testListView() throws Exception {
        mockMvc.perform(get(urlListView).accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(titleListView)));
    }

    @Test
    public void testFormView() throws Exception {
        mockMvc.perform(get(urlFormView).accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString(titleFormView)));
    }
}
