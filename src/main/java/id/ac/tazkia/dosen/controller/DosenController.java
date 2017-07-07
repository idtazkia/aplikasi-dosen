package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.JabatanDao;
import id.ac.tazkia.dosen.dao.ProvinsiDao;
import id.ac.tazkia.dosen.dao.RoleDao;
import id.ac.tazkia.dosen.dao.UserDao;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.Role;
import id.ac.tazkia.dosen.entity.User;
import id.ac.tazkia.dosen.entity.UserPassword;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DosenController {

    @Autowired
    private DosenDao dosenDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ProvinsiDao provinsidao;
    
    @Autowired
    private JabatanDao jabatanDao;

    @RequestMapping("/dosen/list")
    public void daftarDosen(Model m, @PageableDefault(size = 10) Pageable pageable) {
        m.addAttribute("data", dosenDao.findAll(pageable));
    }

    @GetMapping(value = "/dosen/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) String id, Model model) {
        Dosen dosen = new Dosen();
        if (id != null && !id.isEmpty()) {
            dosen = dosenDao.findOne(id);
        }else{
            dosen.setUser(new User());
        }

        model.addAttribute("listJabatan", jabatanDao.findAll());
        model.addAttribute("dosen", dosen);
        model.addAttribute("listProvinsi", provinsidao.findAll());
        return "/dosen/form";
    }

    @RequestMapping(value = "/dosen/form", method = RequestMethod.POST)
    public String simpan(@Valid Dosen dosen, ModelMap mm, BindingResult errors) {
        if (errors.hasErrors()) {
            return "dosen/form";
        }
        
        User user = new User();
        if(dosen.getId() != null && !dosen.getId().isEmpty()){
            user = userDao.findOne(dosen.getUser().getId());
            user.setUsername(dosen.getEmail());
        }else{
            Role role = roleDao.findOne("DOSEN");
            UserPassword password = new UserPassword();
            password.setUser(user);
            password.setPassword("$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6");
            
            user.setActive(Boolean.TRUE);
            user.setRole(role);
            user.setUsername(dosen.getEmail());
            user.setUserPassword(password);
        }
        userDao.save(user);
        
        dosen.setUser(user);
        dosenDao.save(dosen);
        
        return "redirect:/dosen/list";
    }

    @RequestMapping("/dosen/delete")
    public String hapus(@RequestParam("id") String id) {
        Dosen dosen = dosenDao.findOne(id);
        if (dosen != null) {
            User u = userDao.findByUsername(dosen.getEmail());
            dosenDao.delete(id);
            userDao.delete(u);
        }
        return "redirect:/dosen/list";
    }
}
