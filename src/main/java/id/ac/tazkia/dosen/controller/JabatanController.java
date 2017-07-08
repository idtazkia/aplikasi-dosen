package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.JabatanDao;
import id.ac.tazkia.dosen.entity.Jabatan;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;

@Controller
public class JabatanController {


    private final JabatanDao jabatanDao;

    public JabatanController(JabatanDao jabatanDao) {
        this.jabatanDao = jabatanDao;
    }

    @GetMapping("/jabatan/list")
    public ModelMap jabatan(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("jabatan", jabatanDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("jabatan", jabatanDao.findAll(pageable));
        }
    }

    @GetMapping("/jabatan/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) Jabatan jabatan) {
        if (jabatan == null) {
            jabatan = new Jabatan();
        }
        return new ModelMap("jabatan", jabatan);
    }

    @PostMapping("/jabatan/form")
    public String simpan(@ModelAttribute @Valid Jabatan jabatan, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/jabatan/form";
        }
        jabatanDao.save(jabatan);
        status.setComplete();
        return "redirect:/jabatan/list";
    }

    @GetMapping("/jabatan/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Jabatan jabatan) {
        return new ModelMap("jabatan", jabatan);
    }

    @PostMapping("/jabatan/delete")
    public Object delete(@ModelAttribute Jabatan jabatan, SessionStatus status) {
        try{
            jabatanDao.delete(jabatan);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", jabatan.getNama())
                    .addObject("entityName", "Jabatan")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/jabatan/list");
        }
        status.setComplete();
        return "redirect:/jabatan/list";
    }
}
