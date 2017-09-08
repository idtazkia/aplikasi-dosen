package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.JenisDokumenPengajuanDao;
import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jenisDokumenPengajuan")
public class JenisDokumenPengajuanController {

    private final static String LIST = "jenisdokumenpengajuan/list";
    private final static String LIST_URL = "/jenisDokumenPengajuan/list";
    private final static String FORM = "jenisdokumenpengajuan/form";
    private static final Logger LOGGER = LoggerFactory.getLogger(JenisDokumenPengajuanController.class);

    @Autowired
    private JenisDokumenPengajuanDao jenisDokumenPengajuanDao;

    @GetMapping("/list")
    public String showList(Model m, @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(name = "value", required = false) String value) {
        if (value != null) {
            m.addAttribute("key", value);
            m.addAttribute("data", jenisDokumenPengajuanDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            m.addAttribute("data", jenisDokumenPengajuanDao.findAll(pageable));
        }
        return LIST;
    }

    @GetMapping("/form")
    public String form(@RequestParam(name = "id", required = false) JenisPengajuanDokumen jpd, Model model) {
        if (jpd == null) {
            model.addAttribute("jpd", new JenisPengajuanDokumen());
            return FORM;
        } else {
            model.addAttribute("jpd", jpd);
        }
        return FORM;
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String simpan(@ModelAttribute("jpd") @Valid JenisPengajuanDokumen jpd, BindingResult errors, ModelMap mm) {
        if (errors.hasErrors()) {
            List<FieldError> fields = errors.getFieldErrors();
            mm.addAttribute("jpd", jpd);
            return "jenisdokumenpengajuan/form";
        }
        jenisDokumenPengajuanDao.save(jpd);
        return "redirect:" + LIST_URL;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        JenisPengajuanDokumen dokumen = new JenisPengajuanDokumen();
        if (id != null && !id.isEmpty()) {
            dokumen = jenisDokumenPengajuanDao.findOne(id);
            if (dokumen != null) {
                jenisDokumenPengajuanDao.delete(dokumen);
            }
        }
        return "redirect:" + LIST_URL;
    }

}
