package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.dao.JenisKegiatanDao;
import id.ac.tazkia.dosen.dao.KategoriKegiatanDao;
import id.ac.tazkia.dosen.dao.KegiatanDosenDao;
import id.ac.tazkia.dosen.dao.SatuanHasilKegiatanDao;
import id.ac.tazkia.dosen.dao.UserDao;
import id.ac.tazkia.dosen.entity.BuktiKinerja;
import id.ac.tazkia.dosen.entity.BuktiPenugasan;
import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.KategoriKegiatan;
import id.ac.tazkia.dosen.entity.KegiatanDosen;
import id.ac.tazkia.dosen.service.ImageService;
import java.io.File;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ivans
 */
@Controller
@RequestMapping("/kegiatan")
public class KegiatanDosenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KegiatanDosenController.class);

    @Autowired
    private KegiatanDosenDao kegiatanDosenDao;

    @Autowired
    private KategoriKegiatanDao kategoriKegiatanDao;

    @Autowired
    private JenisKegiatanDao jenisKegiatanDao;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DosenDao dosenDao;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private SatuanHasilKegiatanDao satuanHKDao;

    private final List<String> FILE_EXTENSION = Arrays.asList("png", "jpg", "jpeg");

    @RequestMapping("/{kegiatan}/list")
    public String kegiatanList(@PathVariable String kegiatan, @PageableDefault(size = 10) Pageable pageable,
            ModelMap mm, Principal principal, Authentication authentication) {
        PageRequest page = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "periode");

        KategoriKegiatan kk = kategoriKegiatanDao.findOneByNamaIgnoreCase(kegiatan);
        if (kk == null) {
            return "redirect:/";
        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL"))) {
            mm.addAttribute("data", kegiatanDosenDao.findByKategoriKegiatan(kk, pageable));
        } else {
            Dosen dosen = dosenDao.findOneByEmail(principal.getName());
            mm.addAttribute("data", kegiatanDosenDao.findByDosenAndKategoriKegiatan(dosen, kk, pageable));
        }

        mm.addAttribute("kegiatan", kegiatan);
        mm.addAttribute("title", getTitle(kegiatan));
        return "kegiatan/list";
    }

    @GetMapping("/{kegiatan}/delete")
    public String delete(@PathVariable String kegiatan, @RequestParam String id,
            ModelMap mm, Principal principal, Authentication authentication) {
        KegiatanDosen kd = new KegiatanDosen();
        if (id != null && !id.isEmpty()) {
            kd = kegiatanDosenDao.findOne(id);
            if (validasiDosen(principal.getName(), authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL")), kd.getDosen().getId())) {
                return "redirect:/kegiatan/" + kegiatan + "/list";
            }
            kegiatanDosenDao.delete(kd);
        }

        return "redirect:/kegiatan/" + kegiatan + "/list";
    }

    @GetMapping("/{kegiatan}/form")
    public String tampilkanForm(@PathVariable String kegiatan, @RequestParam(required = false) String id,
            ModelMap mm, Principal principal, Authentication authentication) {
        KategoriKegiatan kk = kategoriKegiatanDao.findOneByNamaIgnoreCase(kegiatan);
        if (kk == null) {
            return "redirect:/";
        }
        mm.addAttribute("listJenisKegiatan", jenisKegiatanDao.findByKategoriKegiatan(kk));
        mm.addAttribute("kegiatan", kegiatan);
        mm.addAttribute("title", getTitle(kegiatan));

        KegiatanDosen kd = new KegiatanDosen();
        if (id != null && !id.isEmpty()) {
            kd = kegiatanDosenDao.findOne(id);
            if (validasiDosen(principal.getName(), authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL")), kd.getDosen().getId())) {
                return "redirect:/kegiatan/" + kegiatan + "/list";
            }
        } else {
            BuktiKinerja buktiKinerja = new BuktiKinerja();
            BuktiPenugasan buktiPenugasan = new BuktiPenugasan();
//            kd.setBuktiKinerja(buktiKinerja);
//            kd.setBuktiPenugasan(buktiPenugasan);
            kd.setKategoriKegiatan(kk);
        }

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL"))) {
            mm.addAttribute("listDosen", dosenDao.findAll());
        } else {
            Dosen dosen = dosenDao.findOneByEmail(principal.getName());
            kd.setDosen(dosen);
        }

        mm.addAttribute("listSatuan", satuanHKDao.findAll());
        mm.addAttribute("kinerja", kd);
        return "kegiatan/form";
    }

    @PostMapping("/{kegiatan}/form")
    public String prosesForm(@PathVariable String kegiatan, @Valid KegiatanDosen kinerja, ModelMap mm, BindingResult errors,
            MultipartFile filePenugasan, MultipartFile fileKinerja,
            HttpServletRequest request, Principal principal, Authentication authentication) {

        if (kinerja.getId() != null) {
            LOGGER.info("ID [{}]", kinerja.getId());
        }

        if (validasiDosen(principal.getName(), authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_KEGIATAN_ALL")), kinerja.getDosen().getId())) {
            return "redirect:/kegiatan/" + kegiatan + "/list";
        }

//        if (filePenugasan != null && !filePenugasan.isEmpty()) {
//            if (filePenugasan.getSize() > 2097152) {
//                LOGGER.info("UPLOAD GAGAL");
//                LOGGER.info("BESAR FILE YANG DI UPLOAD === [{}]", filePenugasan.getSize());
//                LOGGER.info("MAXIMUM BESAR FILE === [{}]", 2097152);
//
//                errors.addError(new FieldError("buktiPenugasan.nama", "buktiPenugasan.nama", "File terlalu besar, max 2mb"));
//            } else {
//                String extention = tokenizer(filePenugasan.getOriginalFilename(), ".");
//                if (FILE_EXTENSION.contains(extention.toLowerCase())) {
//                    File file = imageService.moveFile(filePenugasan, "bukti-penugasan", extention);
//                    kinerja.getBuktiPenugasan().setUrl(file.getName());
//                } else {
//                    errors.addError(new FieldError("buktiPenugasan.nama", "buktiPenugasan.nama", "File yang diperbolehkan png, jpg, jpeg"));
//                }
//            }
//        }

//        if (fileKinerja != null && !fileKinerja.isEmpty()) {
//            if (fileKinerja.getSize() > 2097152) {
//                LOGGER.info("UPLOAD GAGAL");
//                LOGGER.info("BESAR FILE YANG DI UPLOAD === [{}]", fileKinerja.getSize());
//                LOGGER.info("MAXIMUM BESAR FILE === [{}]", 2097152);
//
//                errors.addError(new FieldError("buktiKinerja.nama", "buktiKinerja.nama", "File terlalu besar, max 2mb"));
//            } else {
//                String extention = tokenizer(fileKinerja.getOriginalFilename(), ".");
//                if (FILE_EXTENSION.contains(extention.toLowerCase())) {
//                    File file = imageService.moveFile(fileKinerja, "bukti-kinerja", extention);
//                    kinerja.getBuktiKinerja().setUrl(file.getName());
//                } else {
//                    errors.addError(new FieldError("buktiKinerja.nama", "buktiKinerja.nama", "File yang diperbolehkan png, jpg, jpeg"));
//                }
//            }
//        }

        if (errors.getErrorCount() > 0) {
            mm.addAttribute("kinerja", kinerja);
            KategoriKegiatan kk = kategoriKegiatanDao.findOneByNamaIgnoreCase(kegiatan);
            mm.addAttribute("listJenisKegiatan", jenisKegiatanDao.findByKategoriKegiatan(kk));
            mm.addAttribute("kegiatan", kegiatan);
            mm.addAttribute("title", getTitle(kegiatan));
            return "kegiatan/form";
        }

        kegiatanDosenDao.save(kinerja);
        return "redirect:/kegiatan/" + kegiatan + "/list";
    }

    private String tokenizer(String originalFilename, String token) {
        StringTokenizer tokenizer = new StringTokenizer(originalFilename, token);
        String result = "";
        while (tokenizer.hasMoreTokens()) {
            result = tokenizer.nextToken();
        }
        return result;
    }

    private String getTitle(String kegiatan) {
        String title = "Kegiatan Bidang Pendidikan";
        if (kegiatan.equalsIgnoreCase("penelitian")) {
            title = "Kegiatan Bidang Penelitian";
        } else if (kegiatan.equalsIgnoreCase("pengabdian")) {
            title = "Kegiatan Bidang Pengabdian";
        } else if (kegiatan.equalsIgnoreCase("penunjang")) {
            title = "Kegiatan Bidang Penunjang";
        }
        return title;
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
