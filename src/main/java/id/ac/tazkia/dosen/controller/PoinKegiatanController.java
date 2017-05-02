package id.ac.tazkia.dosen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PoinKegiatanController {

    @GetMapping("/poinkegiatan/list")
    public void list(){

    }

    @GetMapping("/poinkegiatan/form")
    public void tampilkanForm(){

    }

    @PostMapping("/poinkegiatan/form")
    public String prosesForm(){
        return "redirect:/poinkegiatan/list";
    }
}
