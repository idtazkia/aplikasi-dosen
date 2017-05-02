package id.ac.tazkia.dosen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JabatanController {

    @GetMapping("/jabatan/list")
    public void list(){

    }

    @GetMapping("/jabatan/form")
    public void tampilkanForm(){

    }

    @PostMapping("/jabatan/form")
    public String prosesForm(){
        return "redirect:/jabatan/list";
    }
}
