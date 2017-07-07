package id.ac.tazkia.dosen.controller;

/**
 * Created by yogi on 03/04/2017.
 */
import id.ac.tazkia.dosen.dao.JenisSuratDao;
import id.ac.tazkia.dosen.entity.JenisSurat;
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
public class JenisSuratController {

    private final JenisSuratDao jenisSuratDao;

    public JenisSuratController(JenisSuratDao jenisSuratDao) {
        this.jenisSuratDao = jenisSuratDao;
    }

    @GetMapping("/jenissurat/list")
    public ModelMap jenissurat(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model) {
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("jenisSurat", jenisSuratDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("jenisSurat", jenisSuratDao.findAll(pageable));
        }
    }

    @GetMapping("/jenissurat/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) JenisSurat jenisSurat) {
        if (jenisSurat == null) {
            jenisSurat = new JenisSurat();
        }
        return new ModelMap("jenisSurat", jenisSurat);
    }

    @PostMapping("/jenissurat/form")
    public String simpan(@ModelAttribute @Valid JenisSurat jenisSurat, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/jenissurat/form";
        }
        jenisSuratDao.save(jenisSurat);
        status.setComplete();
        return "redirect:/jenissurat/list";
    }

    @GetMapping("/jenissurat/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) JenisSurat jenisSurat) {
        return new ModelMap("jenisSurat", jenisSurat);
    }

    @PostMapping("/jenissurat/delete")
    public Object delete(@ModelAttribute JenisSurat jenisSurat, SessionStatus status) {
        try {
            jenisSuratDao.delete(jenisSurat);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", jenisSurat.getNama())
                    .addObject("entityName", "Jenis Surat")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/jenissurat/list");
        }
        status.setComplete();
        return "redirect:/jenissurat/list";
    }
}
