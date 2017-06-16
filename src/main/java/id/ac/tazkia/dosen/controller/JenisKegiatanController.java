package id.ac.tazkia.dosen.controller;

/**
 * Created by yogi on 03/05/2017. Updated by Razi on 08/06/2017.
 */
import id.ac.tazkia.dosen.dao.JenisKegiatanDao;
import id.ac.tazkia.dosen.entity.JenisKegiatan;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JenisKegiatanController {

    @Autowired
    private JenisKegiatanDao jenisKegiatanDao;

    @RequestMapping("/jeniskegiatan/list")
    public String jenisKegiatan(Model model) {
        model.addAttribute("jenisKegiatanList", jenisKegiatanDao.findAll());
        return "/jeniskegiatan/list";
    }

    @GetMapping("/jeniskegiatan/form")
    public ModelMap tampilkanForms(@RequestParam(value = "id", required = false) JenisKegiatan jeniskegiatan) {
        if (jeniskegiatan == null) {
            jeniskegiatan = new JenisKegiatan();
        }
        return new ModelMap("jenisKegiatan", jeniskegiatan);
    }

    @PostMapping("/jeniskegiatan/form")
    public String simpan(@ModelAttribute @Valid JenisKegiatan jenisKegiatan, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/jeniskegiatan/form";
        }
        jenisKegiatanDao.save(jenisKegiatan);
        status.setComplete();
        return "redirect:/jeniskegiatan/list";
    }

    @GetMapping("/jeniskegiatan/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) JenisKegiatan jeniskegiatan) {
        return new ModelMap("jeniskegiatan", jeniskegiatan);
    }

    @PostMapping("/jeniskegiatan/delete")
    public Object delete(@ModelAttribute JenisKegiatan jeniskegiatan, SessionStatus status) {
        try{
            jenisKegiatanDao.delete(jeniskegiatan);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", jeniskegiatan.getNama())
                    .addObject("entityName", "JenisKegiatan")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/jeniskegiatan/list");
        }
        status.setComplete();
        return "redirect:/jeniskegiatan/list";
    }
}