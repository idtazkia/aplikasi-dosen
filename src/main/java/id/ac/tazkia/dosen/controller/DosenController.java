package id.ac.tazkia.dosen.controller;

/**
 * Created by yogi on 30/03/2017.
 */

import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.KecamatanDao;
import id.ac.tazkia.dosen.dao.KotaDao;
import id.ac.tazkia.dosen.dao.ProvinsiDao;
import id.ac.tazkia.dosen.entity.Dosen;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class DosenController {

    @Autowired
    private DosenDao dosendao;
    
    @Autowired
    private ProvinsiDao provinsidao;
    
    @Autowired
    private KotaDao kotadao;
    
    @Autowired
    private KecamatanDao kecamatandao;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/dosen/list")
    public void daftarDosen(Model m) {
        m.addAttribute("daftarDosen", dosendao.findAll());
    }
    
    @GetMapping(value = "/dosen/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) Dosen dosen, Model model) {
        if (dosen == null) {
            dosen = new Dosen();
        }
        
        model.addAttribute("dosen", dosen);
        model.addAttribute("listProvinsi", provinsidao.findAll());
        
        model.addAttribute("listKota", kotadao.findAll());

        model.addAttribute("listKecamatan", kecamatandao.findAll());
        
        return "/dosen/form";
    }

    @RequestMapping(value = "/dosen/form", method = RequestMethod.POST)
    public String simpan(@ModelAttribute @Valid Dosen doSen, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "dosen/form";
        }
        dosendao.save(doSen);
        status.setComplete();
        return "redirect:/dosen/list";
    }
    @RequestMapping("/dosen/delete")
    public String hapus(@RequestParam("id") String id) {
        dosendao.delete(id);
        return "redirect:/dosen/list";
    }
}
