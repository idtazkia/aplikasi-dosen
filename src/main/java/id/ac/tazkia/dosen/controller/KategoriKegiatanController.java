package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.KategoriKegiatanDao;
import id.ac.tazkia.dosen.entity.KategoriKegiatan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;

@Controller
public class KategoriKegiatanController {

    @Autowired
    private KategoriKegiatanDao kategoriKegiatanDao;

    public KategoriKegiatanController(KategoriKegiatanDao KategoriKegiatanDao) {this.kategoriKegiatanDao = KategoriKegiatanDao;
    }

    @GetMapping("/kategorikegiatan/list")
    public String jabatan(@PageableDefault(size = 10) Pageable pageable, Model model,
            @RequestParam(name = "value", required = false) String value){
        if(value != null){
            model.addAttribute("key",value);
            model.addAttribute("data",kategoriKegiatanDao.findByNamaContainingIgnoreCase(value, pageable));
        }else{
            model.addAttribute("data", kategoriKegiatanDao.findAll(pageable));
        }
        return "/kategorikegiatan/list";
    }

    @GetMapping("/kategorikegiatan/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) KategoriKegiatan kategorikegiatan) {
        if (kategorikegiatan == null) {
            kategorikegiatan = new KategoriKegiatan();
        }
        return new ModelMap("kategoriKegiatan", kategorikegiatan);
    }

    @PostMapping("/kategorikegiatan/form")
    public String simpan(@ModelAttribute @Valid KategoriKegiatan kategoriKegiatan, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/kategorikegiatan/form";
        }
        kategoriKegiatanDao.save(kategoriKegiatan);
        status.setComplete();
        return "redirect:/kategorikegiatan/list";
    }

    @GetMapping("/kategorikegiatan/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) KategoriKegiatan kategorikegiatan) {
        return new ModelMap("kategorikegiatan", kategorikegiatan);
    }

    @PostMapping("/kategorikegiatan/delete")
    public Object delete(@ModelAttribute KategoriKegiatan kategorikegiatan, SessionStatus status) {
        try{
            kategoriKegiatanDao.delete(kategorikegiatan);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", kategorikegiatan.getNama())
                    .addObject("entityName", "KategoriKegiatan")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/kategorikegiatan/list");
        }
        status.setComplete();
        return "redirect:/kategorikegiatan/list";
    }
}
