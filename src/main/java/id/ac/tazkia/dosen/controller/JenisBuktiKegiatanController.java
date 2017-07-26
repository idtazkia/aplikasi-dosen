package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.JenisBuktiKegiatanDao;
import id.ac.tazkia.dosen.entity.JenisBuktiKegiatan;
import id.ac.tazkia.dosen.entity.KegiatanBelajarMengajar;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by yogi on 05/05/2017.
 */
@Controller
public class JenisBuktiKegiatanController {

    @Autowired
    private JenisBuktiKegiatanDao jenisBuktiKegiatanDao;

    @GetMapping("/jenisbuktikegiatan/list")
    public String jenisBuktiKegiatanList(Model m, @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(name = "value", required = false) String value) {
        if (value != null) {
            m.addAttribute("key", value);
            m.addAttribute("data", jenisBuktiKegiatanDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            m.addAttribute("data", jenisBuktiKegiatanDao.findAll(pageable));
        }
        return "/jenisbuktikegiatan/list";
    }

    @PostMapping("/jenisbuktikegiatan/form")
    public String simpan(@ModelAttribute @Valid JenisBuktiKegiatan jenisBuktiKegiatan, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/jenisbuktikegiatan/form";
        }
        jenisBuktiKegiatanDao.save(jenisBuktiKegiatan);
        status.setComplete();
        return "redirect:/jenisbuktikegiatan/list";
    }

    @GetMapping("/jenisbuktikegiatan/form")
    public String form(Model model, @RequestParam(name = "id", required = false) JenisBuktiKegiatan jenisBuktiKegiatan) {
        if (jenisBuktiKegiatan == null) {
            model.addAttribute("jenisBuktiKegiatan", new JenisBuktiKegiatan());
        } else {
            model.addAttribute("jenisBuktiKegiatan", jenisBuktiKegiatan);
        }
        return "/jenisbuktikegiatan/form";
    }

    @GetMapping("/jenisbuktikegiatan/delete")
    public String delete(@RequestParam String id) {
        JenisBuktiKegiatan kegiatan = new JenisBuktiKegiatan();
        if (id != null && !id.isEmpty()) {
            kegiatan = jenisBuktiKegiatanDao.findOne(id);
            if(kegiatan!= null){
                jenisBuktiKegiatanDao.delete(kegiatan);
            }
        }
        return "redirect:/jenisbuktikegiatan/list";
    }
}
