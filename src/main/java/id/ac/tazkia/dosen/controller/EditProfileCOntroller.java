package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.FakultasDao;
import id.ac.tazkia.dosen.dao.JabatanDao;
import id.ac.tazkia.dosen.dao.ProgramStudiDao;
import id.ac.tazkia.dosen.dao.ProvinsiDao;
import id.ac.tazkia.dosen.dao.RoleDao;
import id.ac.tazkia.dosen.dao.UserDao;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.Role;
import id.ac.tazkia.dosen.entity.User;
import id.ac.tazkia.dosen.entity.UserPassword;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ari
 */
@Controller
public class EditProfileCOntroller {
   @Autowired
    private DosenDao dosenDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ProvinsiDao provinsidao;

    @Autowired
    private ProgramStudiDao programStudiDao;
    @Autowired
    private FakultasDao fakultasDao;
    @Autowired
    private JabatanDao jabatanDao; 
    
    private final Logger logger = LoggerFactory.getLogger(EditProfileCOntroller.class);
    
    @GetMapping(value = "/editprofile")
    public String tampilkanProfile(Principal principal, Model model) {
        User user = userDao.findByUsername(principal.getName());
        logger.info("user ditemukan :" +user.getId());
        logger.info("user ditemukan :" +user.getUsername());
//        Dosen dosen = new Dosen();
        if (user.getId() != null && !user.getId().isEmpty()) {
            Dosen dosen = dosenDao.findByUserId(user.getId());
            logger.debug("id dosen :" +dosen.getId());
            model.addAttribute("profile", dosen);
        }
        
        model.addAttribute("listJabatan", jabatanDao.findAll());
        model.addAttribute("listProvinsi", provinsidao.findAll());
        model.addAttribute("listFakultas", fakultasDao.findAll());
        return "edit_profile";
    }
    
    @RequestMapping(value = "/editprofile/save", method = RequestMethod.POST)
    public String editProfile(@Valid Dosen dosen, BindingResult errors, ModelMap mm, HttpSession session) {
        if (errors.hasErrors()) {
            logger.error("ini error ke satu");
            mm.addAttribute("profile", dosen);
            mm.addAttribute("listJabatan", jabatanDao.findAll());
            mm.addAttribute("listProvinsi", provinsidao.findAll());
            mm.addAttribute("listFakultas", fakultasDao.findAll());
            return "edit_profile";
        }

        logger.info("id :" +dosen.getUser().getId());
        logger.info("id :" +dosen.getAlamatPt());
        logger.info("id :" +dosen.getProgramStudi().getNama());
        if (dosen.getId() != null && !dosen.getId().isEmpty()) {
            User user = userDao.findOne(dosen.getUser().getId());
            user.setUsername(dosen.getEmail());
            userDao.save(user);

            dosen.setUser(user);
        } 
        
        dosenDao.save(dosen);

        return "redirect:/";
    }
}
