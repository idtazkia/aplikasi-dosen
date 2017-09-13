package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.FakultasDao;
import id.ac.tazkia.dosen.dao.ProgramStudiDao;
import id.ac.tazkia.dosen.entity.Fakultas;
import id.ac.tazkia.dosen.entity.ProgramStudi;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private FakultasDao fakultasDao;

    private static Logger LOGGER = LoggerFactory.getLogger(ProgramStudiController.class);

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
        List<Fakultas> listFakultas = new ArrayList<>();

        if (fakultasDao.count() > 0) {
            Pageable pageable = new PageRequest(0, (int) fakultasDao.count());
            Page<Fakultas> pageFakultas = fakultasDao.findAll(pageable);
            listFakultas = pageFakultas.getContent();
            LOGGER.info("ini id fakultas [{}]", listFakultas.get(1).getId());
        }
        LOGGER.info("ini id fakultas [{}]", listFakultas.get(1).getId());
        m.addAttribute("fakultass", listFakultas);

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
    public String prosesForm(@ModelAttribute @Valid ProgramStudi programStudi, BindingResult err, SessionStatus status, Model model) {
        if (err.hasErrors()) {
            List<Fakultas> listFakultas = new ArrayList<>();
            if (fakultasDao.count() > 0) {
                Pageable pageable = new PageRequest(0, (int) fakultasDao.count());
                Page<Fakultas> pageFakultas = fakultasDao.findAll(pageable);
                listFakultas = pageFakultas.getContent();
            }
            model.addAttribute("fakultass", listFakultas);
            return "/programstudi/form";
        }
        psd.save(programStudi);
        status.setComplete();
        return "redirect:/programstudi/list";
    }
}
