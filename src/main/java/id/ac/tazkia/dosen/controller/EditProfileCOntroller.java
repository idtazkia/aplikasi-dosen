package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.constant.StatusDokumenPengajuan;
import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.DosenDokumenProfileDao;
import id.ac.tazkia.dosen.dao.FakultasDao;
import id.ac.tazkia.dosen.dao.JabatanDao;
import id.ac.tazkia.dosen.dao.JenisDokumenPengajuanDao;
import id.ac.tazkia.dosen.dao.JenisPengajuanDokumenProfileDao;
import id.ac.tazkia.dosen.dao.PengajuanDosenDokumenDao;
import id.ac.tazkia.dosen.dao.PengajuanDosenProfileDao;
import id.ac.tazkia.dosen.dao.ProgramStudiDao;
import id.ac.tazkia.dosen.dao.ProvinsiDao;
import id.ac.tazkia.dosen.dao.RoleDao;
import id.ac.tazkia.dosen.dao.UserDao;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.DosenDokumenProfile;
import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import id.ac.tazkia.dosen.entity.JenisPengajuanDokumenProfile;
import id.ac.tazkia.dosen.entity.PengajuanDosenDokumen;
import id.ac.tazkia.dosen.entity.PengajuanDosenProfile;
import id.ac.tazkia.dosen.entity.Role;
import id.ac.tazkia.dosen.entity.User;
import id.ac.tazkia.dosen.entity.UserPassword;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
    private JenisPengajuanDokumenProfileDao jenisPengajuanDokumenProfileDao;
    
    @Autowired
    private DosenDokumenProfileDao dosenDokumenProfileDao;

    @Autowired
    private JenisDokumenPengajuanDao jenisDokumenPengajuanDao;
    @Autowired
    private PengajuanDosenProfileDao pengajuanDosenDao;
    @Autowired
    private PengajuanDosenDokumenDao pengajuanDosenDokumenDao;
    @Autowired
    private FakultasDao fakultasDao;
    @Autowired
    private JabatanDao jabatanDao; 
    
    private final Logger logger = LoggerFactory.getLogger(EditProfileCOntroller.class);
    
    @GetMapping(value = "/editprofile")
    public String tampilkanProfile(Principal principal, Model model) {
        User user = userDao.findByUsername(principal.getName());
//        Dosen dosen = new Dosen();
        if (user.getId() != null && !user.getId().isEmpty()) {
            Dosen dosen = dosenDao.findByUserId(user.getId());
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
            mm.addAttribute("profile", dosen);
            mm.addAttribute("listJabatan", jabatanDao.findAll());
            mm.addAttribute("listProvinsi", provinsidao.findAll());
            mm.addAttribute("listFakultas", fakultasDao.findAll());
            return "edit_profile";
        }

        if (dosen.getId() != null && !dosen.getId().isEmpty()) {
            User user = userDao.findOne(dosen.getUser().getId());
            user.setUsername(dosen.getEmail());
            userDao.save(user);

            dosen.setUser(user);
        } 
        
        dosenDao.save(dosen);

        return "redirect:/";
    }
    
    private Boolean validasiDosen(String email, Boolean isAdmin, String idDosen) {
        if (!isAdmin) {
            Dosen dosen = dosenDao.findOneByEmail(email);
            if (dosen == null || !dosen.getId().equalsIgnoreCase(idDosen)) {
                return true;
            }
        }
        return false;
    }
    
    private Boolean checkStatusDokumen(Dosen pengajuan) {
        if(pengajuan == null || !StringUtils.hasText(pengajuan.getId())){
            return Boolean.FALSE;
        }
        
        Iterable<JenisPengajuanDokumenProfile> listTipeDokumen = jenisPengajuanDokumenProfileDao.findByRequired(Boolean.TRUE);

        List<PengajuanDosenDokumen> listDokumen = new ArrayList<>();
        Boolean isLengkap = Boolean.TRUE;
        for (JenisPengajuanDokumenProfile jenis : listTipeDokumen) {
            DosenDokumenProfile dokumen = dosenDokumenProfileDao.findByDosenAndJenisPengajuanDokumen(pengajuan, jenis);
            if(dokumen == null || !dokumen.getStatusDokumen().equals(StatusDokumenPengajuan.APPROVED)){
                isLengkap = Boolean.FALSE;
                break;
            }
        }
        return isLengkap;
    }
}
