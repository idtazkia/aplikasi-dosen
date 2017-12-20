package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.BuktiPenugasanKegiatanDao;
import id.ac.tazkia.dosen.dao.KegiatanDosenDao;
import id.ac.tazkia.dosen.entity.BuktiPenugasanKegiatan;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jimmy
 */
@Controller
@RequestMapping("/kegiatan/buktipenugasan")
public class BuktiPenugasanKegiatanController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BuktiPenugasanKegiatanController.class);

    @Autowired
    private BuktiPenugasanKegiatanDao buktiPenugasanKegiatanDao;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private KegiatanDosenDao kegiatanDosenDao;
    
    private final List<String> FILE_EXTENSION = Arrays.asList("png", "jpg", "jpeg");

    @GetMapping("/form")
    public String tampilkanForm(@RequestParam String idKeg, @PageableDefault(size = 10) Pageable pageable,
            ModelMap mm, Principal principal, Authentication authentication) {
        mm.addAttribute("listBukti", buktiPenugasanKegiatanDao.findByKegiatanDosenId(idKeg, pageable));
        mm.addAttribute("buktiPenugasanKegiatan", new BuktiPenugasanKegiatan(idKeg));
        mm.addAttribute("idKeg", idKeg);

        return "kegiatan/buktipenugasan/form";
    }

    @PostMapping("/form")
    public String prosesForm(@Valid BuktiPenugasanKegiatan buktiPenugasanKegiatan, 
            BindingResult errors, ModelMap mm,
            MultipartFile filePenugasan, HttpServletRequest request,
            Principal principal, Authentication authentication) {

        LOGGER.debug("ID Kegiatan [{}]", buktiPenugasanKegiatan);
        
        KegiatanDosen kegiatanDosen = kegiatanDosenDao.findOne(buktiPenugasanKegiatan.getKegiatanDosen().getId());
        if(kegiatanDosen == null) {
            LOGGER.error("ID Kegiatan Dosen tidak ditemukan");
            return "redirect:/";
        }
        
        if (errors.hasErrors()) {
            mm.addAttribute("listBukti", buktiPenugasanKegiatanDao.findByKegiatanDosenId(kegiatanDosen.getId(), new PageRequest(0, 10)));
            mm.addAttribute("buktiPenugasanKegiatan", buktiPenugasanKegiatan);
            mm.addAttribute("idKeg", buktiPenugasanKegiatan.getKegiatanDosen().getId());
            return "kegiatan/buktipenugasan/form";
        }
        
        if (filePenugasan != null && !filePenugasan.isEmpty()) {
            if (filePenugasan.getSize() > 2097152) {
                LOGGER.info("UPLOAD GAGAL");
                LOGGER.info("BESAR FILE YANG DI UPLOAD === [{}]", filePenugasan.getSize());
                LOGGER.info("MAXIMUM BESAR FILE === [{}]", 2097152);

                errors.addError(new FieldError("nama", "nama", "File terlalu besar, max 2mb"));
                mm.addAttribute("listBukti", buktiPenugasanKegiatanDao.findByKegiatanDosenId(kegiatanDosen.getId(), new PageRequest(0, 10)));
                mm.addAttribute("buktiPenugasanKegiatan", buktiPenugasanKegiatan);
                mm.addAttribute("idKeg", kegiatanDosen.getId());
                return "kegiatan/buktipenugasan/form";
            } else {
                String extention = tokenizer(filePenugasan.getOriginalFilename(), ".");
                if (FILE_EXTENSION.contains(extention.toLowerCase())) {
                    File file = imageService.moveFile(filePenugasan, "bukti-penugasan", extention);
                    buktiPenugasanKegiatan.setUrl(file.getName());
                } else {
                    errors.addError(new FieldError("nama", "nama", "File yang diperbolehkan png, jpg, jpeg"));
                    mm.addAttribute("listBukti", buktiPenugasanKegiatanDao.findByKegiatanDosenId(kegiatanDosen.getId(), new PageRequest(0, 10)));
                    mm.addAttribute("buktiPenugasanKegiatan", buktiPenugasanKegiatan);
                    mm.addAttribute("idKeg", kegiatanDosen.getId());
                    return "kegiatan/buktipenugasan/form";
                }
            }
        }else{
            errors.addError(new FieldError("url", "url", "File tidak boleh kosong"));
            mm.addAttribute("listBukti", buktiPenugasanKegiatanDao.findByKegiatanDosenId(kegiatanDosen.getId(), new PageRequest(0, 10)));
            mm.addAttribute("buktiPenugasanKegiatan", buktiPenugasanKegiatan);
            mm.addAttribute("idKeg", kegiatanDosen.getId());
            return "kegiatan/buktipenugasan/form";
        }
        
        buktiPenugasanKegiatan.setKegiatanDosen(kegiatanDosen);
        buktiPenugasanKegiatanDao.save(buktiPenugasanKegiatan);
        return "redirect:/kegiatan/buktipenugasan/form?idKeg=" + kegiatanDosen.getId();
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam String id, @RequestParam String idKeg, ModelMap mm,
            Principal principal, Authentication authentication) {
        if (id != null && !id.isEmpty()) {
            BuktiPenugasanKegiatan buktiPenugasanKegiatan = buktiPenugasanKegiatanDao.findOne(id);
            buktiPenugasanKegiatanDao.delete(buktiPenugasanKegiatan);
            
            // remove file from resource
            imageService.removeFile("bukti-penugasan", buktiPenugasanKegiatan.getUrl());
        }

        return "redirect:/kegiatan/buktipenugasan/form?idKeg=" + idKeg;
    }
    
    private String tokenizer(String originalFilename, String token) {
        StringTokenizer tokenizer = new StringTokenizer(originalFilename, token);
        String result = "";
        while (tokenizer.hasMoreTokens()) {
            result = tokenizer.nextToken();
        }
        return result;
    }
    
}
