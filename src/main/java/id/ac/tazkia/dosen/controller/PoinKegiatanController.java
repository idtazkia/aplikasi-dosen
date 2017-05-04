package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.JabatanDao;
import id.ac.tazkia.dosen.dao.JenisKegiatanDao;
import id.ac.tazkia.dosen.dao.JenisSuratDao;
import id.ac.tazkia.dosen.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PoinKegiatanController {

    @Autowired
    private JabatanDao jabatanDao;

    @Autowired
    private JenisKegiatanDao jenisKegiatanDao;

    @ModelAttribute("daftarJabatan")
    public Iterable<Jabatan> daftarJabatan() {
        return jabatanDao.findAll();
    }

    @ModelAttribute("daftaJenisKegiatan")
    public Iterable<JenisKegiatan> daftarJenisKegiatan() {
        return jenisKegiatanDao.findAll();
    }

    @GetMapping("/poinkegiatan/list")
    public void list(){

    }

    @GetMapping("/poinkegiatan/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) PoinKegiatan poinKegiatan) {
        if (poinKegiatan == null) {
            poinKegiatan = new PoinKegiatan();
        }
        return new ModelMap("poinKegiatan", poinKegiatan);
    }



    @PostMapping("/poinkegiatan/form")
    public String prosesForm(){
        return "redirect:/poinkegiatan/list";
    }
}
