package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.ProgramStudiDao;
import id.ac.tazkia.dosen.entity.ProgramStudi;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class ProgramStudiController {

    @Autowired
    private ProgramStudiDao psd;

    @RequestMapping("/programstudi/list")
    public void daftarProgramStudi(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model) {
        if (value != null) {
            model.addAttribute("key", value);
            model.addAttribute("daftarProgramStudi", psd.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            model.addAttribute("daftarProgramStudi", psd.findAll(pageable));
        }
    }

    @RequestMapping("/programstudi/delete")
    public String hapus(@RequestParam("id") String id) {
        psd.delete(id);
        return "redirect:/programstudi/list";
    }

    @RequestMapping(value = "/programstudi/form", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "id", required = false) String id,
            Model m) {

        ProgramStudi programStudi = new ProgramStudi();
        m.addAttribute("programStudi", programStudi);

        if (id != null && !id.isEmpty()) {
            programStudi = psd.findOne(id);
            if (programStudi != null) {
                m.addAttribute("programStudi", programStudi);
                return "programstudi/form";
            } else {
                return "programstudi/form";
            }
        }
        return "programstudi/form";
    }

    @RequestMapping(value = "/programstudi/form", method = RequestMethod.POST)
    public String prosesForm (@ModelAttribute @Valid ProgramStudi programStudi, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/programstudi/form";
        }
        psd.save(programStudi);
        status.setComplete();
        return "redirect:/programstudi/list";
    }
    }
