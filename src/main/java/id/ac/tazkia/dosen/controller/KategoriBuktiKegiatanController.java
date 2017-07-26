/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.KategoriBuktiKegiatanDao;
import id.ac.tazkia.dosen.entity.KategoriBuktiKegiatan;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author razi
 */
@Controller
public class KategoriBuktiKegiatanController {

    @Autowired
    private KategoriBuktiKegiatanDao kategoriBuktiKegiatanDao;

    @RequestMapping("/kategoribuktikegiatan/list")

    public String kategoriBuktiKegiatan(Model model, @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(name = "value",required = false) String value){
        
        if(value != null){
            model.addAttribute("key", value);
            model.addAttribute("data",kategoriBuktiKegiatanDao.findByNamaContainingIgnoreCase(value, pageable));
        }else{
            model.addAttribute("data", kategoriBuktiKegiatanDao.findAll(pageable));
        }
        return "/kategoribuktikegiatan/list";
    }

    @GetMapping("/kategoribuktikegiatan/form")
    public ModelMap tampilkanForms(@RequestParam(value = "id", required = false) KategoriBuktiKegiatan kategoribuktikegiatan) {
        if (kategoribuktikegiatan == null) {
            kategoribuktikegiatan = new KategoriBuktiKegiatan();
        }
        return new ModelMap("kategoriBuktiKegiatan", kategoribuktikegiatan);
    }

    @PostMapping("/kategoribuktikegiatan/form")
    public String simpan(@ModelAttribute @Valid KategoriBuktiKegiatan kategoriBuktiKegiatan, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/kategoribuktikegiatan/form";
        }
        kategoriBuktiKegiatanDao.save(kategoriBuktiKegiatan);
        status.setComplete();
        return "redirect:/kategoribuktikegiatan/list";
    }

    @GetMapping("/kategoribuktikegiatan/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) KategoriBuktiKegiatan kategoribuktikegiatan) {
        return new ModelMap("kategoribuktikegiatan", kategoribuktikegiatan);
    }

    @PostMapping("/kategoribuktikegiatan/delete")
    public Object delete(@ModelAttribute KategoriBuktiKegiatan kategoribuktikegiatan, SessionStatus status) {
        try{
            kategoriBuktiKegiatanDao.delete(kategoribuktikegiatan);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", kategoribuktikegiatan.getNama())
                    .addObject("entityName", "KategoriBuktiKegiatan")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/kategoribuktikegiatan/list");
        }
        status.setComplete();
        return "redirect:/kategoribuktikegiatan/list";
    }
}
    
