package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.BuktiKinerjaDao;
import id.ac.tazkia.dosen.dao.KegiatanBelajarMengajarDao;
import id.ac.tazkia.dosen.entity.BuktiKinerja;
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
@RequestMapping("/kegiatan/kbm/buktikinerja")
public class BuktiKinerjaKBMController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BuktiPenugasanKBMController.class);

    @Autowired
    private BuktiKinerjaDao buktiKinerjaDao;
    
    @Autowired
    private ImageService imageService;
    
    @Autowired
    private KegiatanBelajarMengajarDao kbmDao;
    
    private final List<String> FILE_EXTENSION = Arrays.asList("png", "jpg", "jpeg");

    @GetMapping("/form")
    public String tampilkanForm(@RequestParam String idKbm, @PageableDefault(size = 10) Pageable pageable,
            ModelMap mm, Principal principal, Authentication authentication) {
        mm.addAttribute("listBukti", buktiKinerjaDao.findByKegiatanBelajarMengajarId(idKbm, pageable));
        mm.addAttribute("buktiKinerja", new BuktiKinerja());
        mm.addAttribute("idKbm", idKbm);

        return "kegiatan/kbm/buktikinerja/form";
    }

    @PostMapping("/form")
    public String prosesForm(@RequestParam String idKbm, @Valid BuktiKinerja buktiKinerja, ModelMap mm, BindingResult errors,
            MultipartFile fileKinerja, HttpServletRequest request,
            Principal principal, Authentication authentication) {

        if (errors.hasErrors()) {
            LOGGER.error("masuk ke sini");
            return "/kegiatan/kbm/buktikinerja/form?idKbm=" + idKbm;
        }

        if (errors.getErrorCount() > 0) {
            return "kegiatan/kbm/buktikinerja/form?idKbm=" + idKbm;
        }
        
        LOGGER.debug("ID KBM [{}]", idKbm);
        
        KegiatanBelajarMengajar kbm = kbmDao.findOne(idKbm);
        if(kbm == null) {
            LOGGER.error("ID KBM tidak ditemukan");
            return "kegiatan/kbm/buktikinerja/form?idKbm=" + idKbm;
        }
        
        if (fileKinerja != null && !fileKinerja.isEmpty()) {
            if (fileKinerja.getSize() > 2097152) {
                LOGGER.info("UPLOAD GAGAL");
                LOGGER.info("BESAR FILE YANG DI UPLOAD === [{}]", fileKinerja.getSize());
                LOGGER.info("MAXIMUM BESAR FILE === [{}]", 2097152);

                errors.addError(new FieldError("nama", "nama", "File terlalu besar, max 2mb"));
            } else {
                String extention = tokenizer(fileKinerja.getOriginalFilename(), ".");
                if (FILE_EXTENSION.contains(extention.toLowerCase())) {
                    File file = imageService.moveFile(fileKinerja, "bukti-kinerja", extention);
                    buktiKinerja.setUrl(file.getName());
                } else {
                    errors.addError(new FieldError("nama", "nama", "File yang diperbolehkan png, jpg, jpeg"));
                }
            }
        }
        
        buktiKinerja.setKegiatanBelajarMengajar(kbm);
        buktiKinerjaDao.save(buktiKinerja);
        return "redirect:/kegiatan/kbm/buktikinerja/form?idKbm=" + idKbm;
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam String id, @RequestParam String idKbm, ModelMap mm,
            Principal principal, Authentication authentication) {
        if (id != null && !id.isEmpty()) {
            BuktiKinerja buktiKinerja = buktiKinerjaDao.findOne(id);
            buktiKinerjaDao.delete(buktiKinerja);
            
            // remove file from resource
            imageService.removeFile("bukti-kinerja", buktiKinerja.getUrl());
        }

        return "redirect:/kegiatan/kbm/buktikinerja/form?idKbm=" + idKbm;
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
