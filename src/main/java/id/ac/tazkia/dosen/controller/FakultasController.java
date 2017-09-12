package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.FakultasDao;
import id.ac.tazkia.dosen.entity.Fakultas;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
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
public class FakultasController {
    
    @Autowired
    private FakultasDao fakultasDao;
    
    @GetMapping("/fakultas/list")
    public ModelMap fakultas(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("fakultas", fakultasDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("fakultas", fakultasDao.findAll(pageable));
        }
    }

    @GetMapping("/fakultas/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) Fakultas fakultas) {
        if (fakultas == null) {
            fakultas = new Fakultas();
        }
        return new ModelMap("fakultas", fakultas);
    }

    @PostMapping("/fakultas/form")
    public String simpan(@ModelAttribute @Valid Fakultas fakultas, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/fakultas/form";
        }
        fakultasDao.save(fakultas);
        status.setComplete();
        return "redirect:/fakultas/list";
    }

    @GetMapping("/fakultas/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Fakultas fakultas) {
        return new ModelMap("fakultas", fakultas);
    }

    @PostMapping("/fakultas/delete")
    public Object delete(@ModelAttribute Fakultas fakultas, SessionStatus status) {
        try{
            fakultasDao.delete(fakultas);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", fakultas.getNama())
                    .addObject("entityName", "Fakultas")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/fakultas/list");
        }
        status.setComplete();
        return "redirect:/fakultas/list";
    }
}
