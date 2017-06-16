package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.JabatanDao;
import id.ac.tazkia.dosen.dao.JenisKegiatanDao;
import id.ac.tazkia.dosen.dao.JenisSuratDao;
import id.ac.tazkia.dosen.dao.PoinKegiatanDao;
import id.ac.tazkia.dosen.entity.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PoinKegiatanController {

    @Autowired
    private JabatanDao jabatanDao;
    
    @Autowired
    private PoinKegiatanDao poinKegiatanDao;

    @Autowired
    private JenisKegiatanDao jenisKegiatanDao;
    
    @RequestMapping("/poinkegiatan/list")
    public String poinKegiatan(Model model) {
        model.addAttribute("poinKegiatanList", poinKegiatanDao.findAll());
        return "/poinkegiatan/list";
    }
    
    @GetMapping("/poinkegiatan/form")
    public String tampilkanForm(@RequestParam(value = "id", required = false) PoinKegiatan poinKegiatan, Model m) {
        if (poinKegiatan == null) {
            poinKegiatan = new PoinKegiatan();
        }
        m.addAttribute("poinKegiatan", poinKegiatan);
        m.addAttribute("listJabatan", jabatanDao.findAll());
        m.addAttribute("listJenisKegiatan", jenisKegiatanDao.findAll());
        return "/poinkegiatan/form";
    }
    
    @PostMapping("/poinkegiatan/form")
    public String simpan(@ModelAttribute @Valid PoinKegiatan poinKegiatan, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/poinkegiatan/form";
        }
        poinKegiatanDao.save(poinKegiatan);
        status.setComplete();
        return "redirect:/poinkegiatan/list";
    }
    
    @GetMapping("/poinkegiatan/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) PoinKegiatan poinkegiatan) {
        return new ModelMap("poinkegiatan", poinkegiatan);
    }

    @PostMapping("/poinkegiatan/delete")
    public Object delete(@ModelAttribute PoinKegiatan poinkegiatan, SessionStatus status) {
        try {
            poinKegiatanDao.delete(poinkegiatan);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", poinkegiatan.getId())
                    .addObject("entityName", "PoinKegiatan")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/poinkegiatan/list");
        }
        status.setComplete();
        return "redirect:/poinkegiatan/list";
    }
    
//    @ModelAttribute("daftarJabatan")
//    public Iterable<Jabatan> daftarJabatan() {
//        return jabatanDao.findAll();
//    }
//
//    @ModelAttribute("daftaJenisKegiatan")
//    public Iterable<JenisKegiatan> daftarJenisKegiatan() {
//        return jenisKegiatanDao.findAll();
//    }
//
//    @GetMapping("/poinkegiatan/list")
//    public void list(){
//
//    }
//
    
//
//
//
//    @PostMapping("/poinkegiatan/form")
//    public String prosesForm(){
//        return "redirect:/poinkegiatan/list";
//    }
}
