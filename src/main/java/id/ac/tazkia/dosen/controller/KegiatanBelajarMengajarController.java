package id.ac.tazkia.dosen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class KegiatanBelajarMengajarController {
    @GetMapping("/kbm/list")
    public ModelMap kbmList(){
        return new ModelMap("daftarKbm", new ArrayList<Object>());
    }

    @GetMapping("/kbm/form")
    public ModelMap tampilkanForm(){
        return new ModelMap();
    }

    @PostMapping("/kbm/form")
    public String prosesForm(){
        return "redirect:/kbm/list";
    }
}
