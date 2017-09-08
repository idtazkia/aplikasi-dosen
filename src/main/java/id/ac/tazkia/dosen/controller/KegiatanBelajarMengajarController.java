package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.MataKuliahDao;
import id.ac.tazkia.dosen.entity.BuktiKinerja;
import id.ac.tazkia.dosen.entity.BuktiPenugasan;
import id.ac.tazkia.dosen.entity.KegiatanBelajarMengajar;
import id.ac.tazkia.dosen.service.ImageService;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import id.ac.tazkia.dosen.dao.KegiatanBelajarMengajarDao;
import id.ac.tazkia.dosen.dao.SatuanHasilKegiatanDao;
import id.ac.tazkia.dosen.entity.Dosen;
import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Controller
@RequestMapping("/kegiatan/kbm")
public class KegiatanBelajarMengajarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KegiatanBelajarMengajarController.class);

    @Autowired
    private KegiatanBelajarMengajarDao kegiatanPendidikanDao;

    @Autowired
    private MataKuliahDao mataKuliahDao;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DosenDao dosenDao;
    
    @Autowired
    private SatuanHasilKegiatanDao satuanHKDao;

    private final List<String> FILE_EXTENSION = Arrays.asList("png", "jpg", "jpeg");

    @GetMapping("/list")
    public String kegiatanPendidikanList(@PageableDefault(size = 10) Pageable pageable,
            ModelMap mm, Principal principal, Authentication authentication) {
        PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "periode");

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL"))) {
            mm.addAttribute("data", kegiatanPendidikanDao.findAll(page));
        } else {
            Dosen dosen = dosenDao.findOneByEmail(principal.getName());
            mm.addAttribute("data", kegiatanPendidikanDao.findByDosen(dosen, pageable));
        }

        return "kegiatan/kbm/list";
    }

    @GetMapping("/form")
    public String tampilkanForm(@RequestParam(required = false) String id,
            ModelMap mm, Principal principal, Authentication authentication) {
        mm.addAttribute("listMatkul", mataKuliahDao.findAll());
        KegiatanBelajarMengajar kegiatan = new KegiatanBelajarMengajar();
        if (id != null && !id.isEmpty()) {
            kegiatan = kegiatanPendidikanDao.findOne(id);
            if (validasiDosen(principal.getName(), authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL")), kegiatan.getDosen().getId())) {
                return "redirect:/kegiatan/kbm/list";
            }
        } else {
            BuktiKinerja buktiKinerja = new BuktiKinerja();
            BuktiPenugasan buktiPenugasan = new BuktiPenugasan();
            buktiKinerja.setUrl("dummy");
            buktiPenugasan.setUrl("dummy");
//            kegiatan.setBuktiKinerja(buktiKinerja);
//            kegiatan.setBuktiPenugasan(buktiPenugasan);
        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL"))) {
            mm.addAttribute("listDosen", dosenDao.findAll());
        } else {
            Dosen dosen = dosenDao.findOneByEmail(principal.getName());
            kegiatan.setDosen(dosen);
        }
        
        mm.addAttribute("listSatuan", satuanHKDao.findAll());
        mm.addAttribute("kinerja", kegiatan);
        return "kegiatan/kbm/form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, ModelMap mm,
            Principal principal, Authentication authentication) {
        KegiatanBelajarMengajar kegiatan = new KegiatanBelajarMengajar();
        if (id != null && !id.isEmpty()) {
            kegiatan = kegiatanPendidikanDao.findOne(id);
            if (validasiDosen(principal.getName(), authentication.getAuthorities().contains(new SimpleGrantedAuthority("KEGIATAN_ALL")), kegiatan.getDosen().getId())) {
                return "redirect:/kegiatan/kbm/list";
            }
            kegiatanPendidikanDao.delete(kegiatan);
        }

        return "redirect:/kegiatan/kbm/list";
    }

    @PostMapping("/form")
    public String prosesForm(@Valid KegiatanBelajarMengajar kinerja, ModelMap mm, BindingResult errors,
            MultipartFile filePenugasan, MultipartFile fileKinerja, HttpServletRequest request,
            Principal principal, Authentication authentication) {

        if (errors.hasErrors()) {
            LOGGER.error("masuk ke sini");
            mm.addAttribute("kinerja", kinerja);
            mm.addAttribute("listMatkul", mataKuliahDao.findAll());
            return "/kegiatan/kbm/form";
        }

        if (validasiDosen(principal.getName(), authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL")), kinerja.getDosen().getId())) {
            return "redirect:/kegiatan/kbm/list";
        }

        if (kinerja.getId() != null) {
            LOGGER.info("ID [{}]", kinerja.getId());
        }
        
        kegiatanPendidikanDao.save(kinerja);
        return "redirect:/kegiatan/kbm/list";
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
}
