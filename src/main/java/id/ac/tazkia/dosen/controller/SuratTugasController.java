package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.JenisSuratDao;
import id.ac.tazkia.dosen.dao.SuratTugasDao;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.JenisSurat;
import id.ac.tazkia.dosen.entity.SuratTugas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Controller
public class SuratTugasController {

    @Autowired
    private SuratTugasDao suratTugasDao;
    @Autowired
    private JenisSuratDao jenisSuratDao;
    @Autowired
    private DosenDao dosenDao;

    @ModelAttribute("daftarJenisSurat")
    public Iterable<JenisSurat> daftarJenisSurat() {
        return jenisSuratDao.findAll();
    }

    @ModelAttribute("daftarDosen")
    public Iterable<Dosen> daftarDosen() {
        return dosenDao.findAll();
    }

    @RequestMapping("/surattugas/list")
    public String SuratTugas(Model model, Pageable pageable) {
        Page<SuratTugas> suratTugasPage = suratTugasDao.findAll(pageable);
        PageWrapper<SuratTugas> page = new PageWrapper<>(suratTugasPage, "/surattugas/list");
        model.addAttribute("suratTugas", page.getContent());
        model.addAttribute("page", page);
        return "/surattugas/list";
    }

    @RequestMapping(value = "/surattugas/form", method = RequestMethod.GET)
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) SuratTugas suratTugas) {
        if (suratTugas == null) {
            suratTugas = new SuratTugas();
        }
        return new ModelMap("suratTugas", suratTugas);
    }

    @RequestMapping(value = "/surattugas/form", method = RequestMethod.POST)
    public String prosesForm(@ModelAttribute @Valid SuratTugas suratTugas, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/surattugas/form";
        }
        suratTugasDao.save(suratTugas);
        status.setComplete();
        return "redirect:/surattugas/list";
    }

    @RequestMapping(value = "/surattugas/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) String id, SessionStatus status) {
        suratTugasDao.delete(id);
        status.setComplete();
        return "redirect:/surattugas/list";
    }

}

