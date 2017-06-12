package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.ProgramStudiDao;
import id.ac.tazkia.dosen.entity.ProgramStudi;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProgramStudiController {

    @Autowired
    private ProgramStudiDao psd;

    @RequestMapping("/programstudi/list")
    public void daftarProgramStudi(Model m) {
        m.addAttribute("daftarProgramStudi", psd.findAll());
    }

    @RequestMapping("/programstudi/delete")
    public String hapus(@RequestParam("id") String id) {
        psd.delete(id);
        return "redirect:/programstudi/list";
    }

    @RequestMapping(value = "/programstudi/form", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "id", required = false) String id,
            Model m) {

        ProgramStudi programstudi = new ProgramStudi();
        m.addAttribute("programstudi", programstudi);

        if (id != null && !id.isEmpty()) {
            programstudi = psd.findOne(id);
            if (programstudi != null) {
                m.addAttribute("programstudi", programstudi);
                return "programstudi/form";
            } else {
                return "programstudi/form";
            }
        }

        return "programstudi/form";
    }

    @RequestMapping(value = "/programstudi/form", method = RequestMethod.POST)
    public String prosesForm(@Valid ProgramStudi ps, BindingResult errors) throws Exception {
        if (errors.hasErrors()) {
            return "programstudi/form";
        } else {
        psd.save(ps);
        return "redirect:/programstudi/list";
    }
}
}
