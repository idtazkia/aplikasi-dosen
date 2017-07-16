package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.BuktiPenugasanDao;
import id.ac.tazkia.dosen.dao.KegiatanBelajarMengajarDao;
import id.ac.tazkia.dosen.entity.BuktiPenugasan;
import id.ac.tazkia.dosen.entity.KegiatanBelajarMengajar;
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
@RequestMapping("/kegiatan/kbm/buktipenugasan")
public class BuktiPenugasanKBMController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuktiPenugasanKBMController.class);

    @Autowired
    private BuktiPenugasanDao buktiPenugasanDao;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private KegiatanBelajarMengajarDao kbmDao;
    
    private final List<String> FILE_EXTENSION = Arrays.asList("png", "jpg", "jpeg");

    @GetMapping("/form")
    public String tampilkanForm(@RequestParam String idKbm, @PageableDefault(size = 10) Pageable pageable,
            ModelMap mm, Principal principal, Authentication authentication) {
        mm.addAttribute("listBukti", buktiPenugasanDao.findByKegiatanBelajarMengajarId(idKbm, pageable));
        mm.addAttribute("buktiPenugasan", new BuktiPenugasan());
        mm.addAttribute("idKbm", idKbm);

        return "kegiatan/kbm/buktipenugasan/form";
    }

    @PostMapping("/form")
    public String prosesForm(@RequestParam String idKbm, @Valid BuktiPenugasan buktiPenugasan, ModelMap mm, BindingResult errors,
            MultipartFile filePenugasan, HttpServletRequest request,
            Principal principal, Authentication authentication) {

        if (errors.hasErrors()) {
            LOGGER.error("masuk ke sini");
            return "/kegiatan/kbm/buktipenugasan/form?idKbm=" + idKbm;
        }

        if (errors.getErrorCount() > 0) {
            return "kegiatan/kbm/buktipenugasan/form?idKbm=" + idKbm;
        }
        
        LOGGER.debug("ID KBM [{}]", idKbm);
        
        KegiatanBelajarMengajar kbm = kbmDao.findOne(idKbm);
        if(kbm == null) {
            LOGGER.error("ID KBM tidak ditemukan");
            return "kegiatan/kbm/buktipenugasan/form?idKbm=" + idKbm;
        }
        
        if (filePenugasan != null && !filePenugasan.isEmpty()) {
            if (filePenugasan.getSize() > 2097152) {
                LOGGER.info("UPLOAD GAGAL");
                LOGGER.info("BESAR FILE YANG DI UPLOAD === [{}]", filePenugasan.getSize());
                LOGGER.info("MAXIMUM BESAR FILE === [{}]", 2097152);

                errors.addError(new FieldError("nama", "nama", "File terlalu besar, max 2mb"));
            } else {
                String extention = tokenizer(filePenugasan.getOriginalFilename(), ".");
                if (FILE_EXTENSION.contains(extention.toLowerCase())) {
                    File file = imageService.moveFile(filePenugasan, "bukti-penugasan", extention);
                    buktiPenugasan.setUrl(file.getName());
                } else {
                    errors.addError(new FieldError("nama", "nama", "File yang diperbolehkan png, jpg, jpeg"));
                }
            }
        }
        
        buktiPenugasan.setKegiatanBelajarMengajar(kbm);
        buktiPenugasanDao.save(buktiPenugasan);
        return "redirect:/kegiatan/kbm/buktipenugasan/form?idKbm=" + idKbm;
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam String id, @RequestParam String idKbm, ModelMap mm,
            Principal principal, Authentication authentication) {
        if (id != null && !id.isEmpty()) {
            BuktiPenugasan buktiPenugasan = buktiPenugasanDao.findOne(id);
            buktiPenugasanDao.delete(buktiPenugasan);
            
            // remove file from resource
            imageService.removeFile("bukti-penugasan", buktiPenugasan.getUrl());
        }

        return "redirect:/kegiatan/kbm/buktipenugasan/form?idKbm=" + idKbm;
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
