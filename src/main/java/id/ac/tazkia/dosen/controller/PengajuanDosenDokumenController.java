package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.constant.StatusDokumenPengajuan;
import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.JenisDokumenPengajuanDao;
import id.ac.tazkia.dosen.dao.PengajuanDosenDokumenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import id.ac.tazkia.dosen.dao.PengajuanDosenProfileDao;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import id.ac.tazkia.dosen.entity.PengajuanDosenDokumen;
import id.ac.tazkia.dosen.entity.PengajuanDosenProfile;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pengajuan/dokumen")
public class PengajuanDosenDokumenController {

    @Autowired
    private JenisDokumenPengajuanDao jenisDokumenPengajuanDao;
    @Autowired
    private PengajuanDosenProfileDao pengajuanDosenProfileDao;
    @Autowired
    private PengajuanDosenDokumenDao pengajuanDosenDokumenDao;
    @Autowired
    private DosenDao dosenDao;

    @GetMapping("/list")
    public String tampilkanFormPilihDosen(ModelMap mm, Principal principal, Authentication authentication) {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            return "redirect:/pengajuan/dokumen/form";
        }

        mm.addAttribute("listDosen", dosenDao.findAll());

        return "pengajuan/dokumen/form_select_dosen";
    }

    @GetMapping("/form")
    public String tampilkanForm(@RequestParam(required = false) String id,
            ModelMap mm, Principal principal, Authentication authentication) {

        PengajuanDosenProfile pengajuan = new PengajuanDosenProfile();
        Dosen dosen = new Dosen();

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            if (id == null || id.isEmpty()) {
                return "redirect:/pengajuan/dokumen/form/getDosen";
            }
            dosen = dosenDao.findOne(id);
            if (dosen == null) {
                return "redirect:/pengajuan/dokumen/form/getDosen";
            }
        } else {
            dosen = dosenDao.findOneByEmail(principal.getName());
            if (dosen == null) {
                throw new NullPointerException("Data Dosen Tidak Ditemukan");
            }
        }

        Iterable<JenisPengajuanDokumen> listTipeDokumen = jenisDokumenPengajuanDao.findAll();
        
        
        pengajuan = pengajuanDosenProfileDao.findByDosen(dosen);
        if (pengajuan == null) {
            return "redirect:/pengajuan/profile/form";
        }
        
        List<PengajuanDosenDokumen> listDokumen = new ArrayList<>();
        for(JenisPengajuanDokumen jenis : listTipeDokumen){
            PengajuanDosenDokumen dokumen = pengajuanDosenDokumenDao.findByPengajuanDosenAndJenisPengajuanDokumen(pengajuan, jenis);
            if(dokumen == null){
                dokumen = new PengajuanDosenDokumen();
                dokumen.setFilename(null);
                dokumen.setJenisPengajuanDokumen(jenis);
                dokumen.setPengajuanDosen(pengajuan);
                dokumen.setStatusDokumen(StatusDokumenPengajuan.PENDING);
            }
            listDokumen.add(dokumen);
        }

        mm.addAttribute("listDokumen", listDokumen);
        return "pengajuan/dokumen/form";
    }
}
