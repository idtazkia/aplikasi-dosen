package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.JabatanDao;
import id.ac.tazkia.dosen.dao.JenisSuratDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JabatanController {


    private JabatanDao jabatanDao;

    public JabatanController(JabatanDao jabatanDao) {
        this.jabatanDao = jabatanDao;
    }

    @GetMapping("/jabatan/list")
    public ModelMap jabatan(){
        return new ModelMap().addAttribute("jabatan", jabatanDao.findAll());
    }


    @GetMapping("/jabatan/form")
    public void tampilkanForm(){

    }

    @PostMapping("/jabatan/form")
    public String prosesForm(){
        return "redirect:/jabatan/list";
    }
}
