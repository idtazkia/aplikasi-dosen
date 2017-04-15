package id.ac.tazkia.dosen;

import com.github.javafaker.Faker;
import id.ac.tazkia.dosen.controller.SuratTugasController;
import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.JenisSuratDao;
import id.ac.tazkia.dosen.dao.SuratTugasDao;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.JenisSurat;
import id.ac.tazkia.dosen.entity.SuratTugas;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SuratTugasController.class)
public class SuratTugasTests {
    @Autowired private MockMvc mockMvc;

    @MockBean private DosenDao dosenDao;
    @MockBean private JenisSuratDao jenisSuratDao;
    @MockBean private SuratTugasDao suratTugasDao;

    private Faker faker = new Faker();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Dosen dx = new Dosen();
    private JenisSurat jx = new JenisSurat();

    @Before
    public void prepareSampleData(){

        List<Dosen> daftarDosen = new ArrayList<>();

        dx.setId(UUID.randomUUID().toString());
        dx.setNama(faker.name().fullName());
        daftarDosen.add(dx);

        for (int i = 0; i < 10; i++) {
            Dosen d = new Dosen();
            d.setId(UUID.randomUUID().toString());
            d.setNama(faker.name().fullName());
            daftarDosen.add(d);
        }

        List<JenisSurat> daftarJenisSurat = new ArrayList<>();
        jx.setId(UUID.randomUUID().toString());
        jx.setNama(faker.name().fullName());
        daftarJenisSurat.add(jx);
        for (int i = 0; i < 3; i++) {
            JenisSurat j = new JenisSurat();
            j.setId(UUID.randomUUID().toString());
            j.setNama(faker.name().fullName());
            daftarJenisSurat.add(j);
        }

        List<SuratTugas> daftarSurat = new ArrayList<>();
        given(suratTugasDao.findAll()).willReturn(daftarSurat);


        given(dosenDao.findAll()).willReturn(daftarDosen);
        given(dosenDao.findOne(dx.getId())).willReturn(dx);
        given(jenisSuratDao.findAll()).willReturn(daftarJenisSurat);
        given(jenisSuratDao.findOne(jx.getId())).willReturn(jx);
    }

    @Test
    public void testListView() throws Exception {
        mockMvc.perform(get("/surattugas/list").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Aplikasi Dosen :: Surat Tugas")));
    }

    @Test
    public void testSubmitFormGagal() throws Exception {
        mockMvc.perform(get("/surattugas/form").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Aplikasi Dosen :: Edit Surat Tugas")));

        mockMvc.perform(post("/surattugas/form")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Aplikasi Dosen :: Edit Surat Tugas")))
                .andExpect(content().string(Matchers.containsString("may not be null")))
                .andExpect(content().string(Matchers.containsString("may not be empty")));
    }
}
