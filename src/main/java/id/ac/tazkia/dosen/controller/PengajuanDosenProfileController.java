package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.constant.StatusDokumenPengajuan;
import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.FakultasDao;
import id.ac.tazkia.dosen.dao.JenisDokumenPengajuanDao;
import id.ac.tazkia.dosen.dao.MataKuliahDao;
import id.ac.tazkia.dosen.dao.PengajuanDosenDokumenDao;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.PengajuanDosenProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import id.ac.tazkia.dosen.dao.PengajuanDosenProfileDao;
import id.ac.tazkia.dosen.dao.ProgramStudiDao;
import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import id.ac.tazkia.dosen.entity.PengajuanDosenDokumen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pengajuan/profile")
public class PengajuanDosenProfileController {


    @Autowired
    private JenisDokumenPengajuanDao jenisDokumenPengajuanDao;
    @Autowired
    private PengajuanDosenProfileDao pengajuanDosenDao;
    @Autowired
    private PengajuanDosenDokumenDao pengajuanDosenDokumenDao;
    @Autowired
    private DosenDao dosenDao;

    @Autowired
    private FakultasDao fakultasDao;

    @Autowired
    private ProgramStudiDao programStudiDao;

    @Autowired
    private MataKuliahDao mataKuliahDao;

    @GetMapping("/list")
    public String tampilkanFormPilihDosen(@PageableDefault(size = 10) Pageable pageable,
            ModelMap mm, Principal principal, Authentication authentication) {
        PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "tanggalSurat");
        Page<PengajuanDosenProfile> result = new PageImpl<>(Collections.<PengajuanDosenProfile>emptyList());
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            mm.addAttribute("listDosen", dosenDao.findAll());
            result = pengajuanDosenDao.findAll(pageable);
        } else {
            Dosen dosen = dosenDao.findOneByEmail(principal.getName());
            result = pengajuanDosenDao.findByDosen(dosen, pageable);
        }

        for(PengajuanDosenProfile p : result.getContent()){
            p.setLampiranLengkap(checkStatusDokumen(p));
        }
        
        mm.addAttribute("data", result);
        return "pengajuan/profile/list";
    }

    @GetMapping("/form")
    public String tampilkanForm(@RequestParam(required = false) String id, @RequestParam(required = false) String idDosen,
            ModelMap mm, Principal principal, Authentication authentication) {

        PengajuanDosenProfile pengajuan = new PengajuanDosenProfile();
        Dosen dosen = null;
        if (StringUtils.hasText(id)) {
            pengajuan = pengajuanDosenDao.findOne(id);
            if (pengajuan != null) {
                dosen = pengajuan.getDosen();
            }
        }
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            if (!StringUtils.hasText(idDosen) && dosen == null) {
                mm.addAttribute("listDosen", dosenDao.findAll());
            } else if (StringUtils.hasText(idDosen) && dosen == null) {
                dosen = dosenDao.findOne(idDosen);
                pengajuan.setDosen(dosen);
            }
        } else {
            dosen = dosenDao.findOneByEmail(principal.getName());
            if (dosen == null
                    || (pengajuan.getDosen() != null && !pengajuan.getDosen().getId().equalsIgnoreCase(dosen.getId()))) {
                throw new NullPointerException("Data Dosen Tidak Ditemukan");
            }

            pengajuan.setDosen(dosen);
        }

        pengajuan.setLampiranLengkap(checkStatusDokumen(pengajuan));
        
        mm.addAttribute("listFakultas", fakultasDao.findAll());
        mm.addAttribute("listProdi", programStudiDao.findAll());
        mm.addAttribute("listMatkul", mataKuliahDao.findAll());
        mm.addAttribute("pengajuan", pengajuan);
        return "pengajuan/profile/form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute("pengajuan") @Valid PengajuanDosenProfile pengajuan, BindingResult errors,
            ModelMap mm, Principal principal, Authentication authentication) {

        if (errors.hasErrors()) {
            mm.addAttribute("listFakultas", fakultasDao.findAll());
            mm.addAttribute("listProdi", programStudiDao.findAll());
            mm.addAttribute("listMatkul", mataKuliahDao.findAll());
            mm.addAttribute("pengajuan", pengajuan);
            return "pengajuan/profile/form";
        }

        if (validasiDosen(principal.getName(), authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL")), pengajuan.getDosen().getId())) {
            return "redirect:/pengajuan/profile/list";
        }

        pengajuanDosenDao.save(pengajuan);

        return "redirect:/pengajuan/profile/list";
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

    @RequestMapping("/delete")
    public String hapus(@RequestParam("id") String id) {
        PengajuanDosenProfile pengajuan = pengajuanDosenDao.findOne(id);
        if (pengajuan != null) {
            pengajuanDosenDao.delete(pengajuan);
        }
        return "redirect:/pengajuan/profile/list";
    }

    private Boolean checkStatusDokumen(PengajuanDosenProfile pengajuan) {
        if(pengajuan == null || !StringUtils.hasText(pengajuan.getId())){
            return Boolean.FALSE;
        }
        
        Iterable<JenisPengajuanDokumen> listTipeDokumen = jenisDokumenPengajuanDao.findByRequired(Boolean.TRUE);

        List<PengajuanDosenDokumen> listDokumen = new ArrayList<>();
        Boolean isLengkap = Boolean.TRUE;
        for (JenisPengajuanDokumen jenis : listTipeDokumen) {
            PengajuanDosenDokumen dokumen = pengajuanDosenDokumenDao.findByPengajuanDosenAndJenisPengajuanDokumen(pengajuan, jenis);
            if(dokumen == null || !dokumen.getStatusDokumen().equals(StatusDokumenPengajuan.APPROVED)){
                isLengkap = Boolean.FALSE;
                break;
            }
        }
        return isLengkap;
    }
}
