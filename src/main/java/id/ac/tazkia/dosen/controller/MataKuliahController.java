/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.MataKuliahDao;
import id.ac.tazkia.dosen.entity.MataKuliah;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class MataKuliahController {

    @Autowired
    private MataKuliahDao mataKuliahDao;

    @RequestMapping("/matakuliah/list")
    public String mataKuliah(Model model) {
        model.addAttribute("mataKuliahList", mataKuliahDao.findAll());
        return "/matakuliah/list";
    }

    @GetMapping("/matakuliah/form")
    public ModelMap tampilkanForms(@RequestParam(value = "id", required = false) MataKuliah matakuliah) {
        if (matakuliah == null) {
            matakuliah = new MataKuliah();
        }
        return new ModelMap("mataKuliah", matakuliah);
    }

    @PostMapping("/matakuliah/form")
    public String simpan(@ModelAttribute @Valid MataKuliah mataKuliah, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/matakuliah/form";
        }
        mataKuliahDao.save(mataKuliah);
        status.setComplete();
        return "redirect:/matakuliah/list";
    }

    @GetMapping("/matakuliah/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) MataKuliah matakuliah) {
        return new ModelMap("matakuliah", matakuliah);
    }

    @PostMapping("/matakuliah/delete")
    public Object delete(@ModelAttribute MataKuliah matakuliah, SessionStatus status) {
        try {
            mataKuliahDao.delete(matakuliah);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", matakuliah.getNama())
                    .addObject("entityName", "MataKuliah")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/matakuliah/list");
        }
        status.setComplete();
        return "redirect:/matakuliah/list";
    }
}
