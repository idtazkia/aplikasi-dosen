package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.BuktiKinerjaKegiatanDao;
import id.ac.tazkia.dosen.dao.KegiatanDosenDao;
import id.ac.tazkia.dosen.entity.BuktiKinerjaKegiatan;
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
@RequestMapping("/kegiatan/buktikinerja")
public class BuktiKinerjaKegiatanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuktiKinerjaKegiatanController.class);

    @Autowired
    private BuktiKinerjaKegiatanDao buktiKinerjaKegiatanDao;

    @Autowired
    private ImageService imageService;

    @Autowired
    private KegiatanDosenDao kegiatanDosenDao;

    private final List<String> FILE_EXTENSION = Arrays.asList("png", "jpg", "jpeg");

    @GetMapping("/form")
    public String tampilkanForm(@RequestParam String idKeg, @PageableDefault(size = 10) Pageable pageable,
            ModelMap mm, Principal principal, Authentication authentication) {
        mm.addAttribute("listBukti", buktiKinerjaKegiatanDao.findByKegiatanDosenId(idKeg, pageable));
        mm.addAttribute("buktiKinerjaKegiatan", new BuktiKinerjaKegiatan(idKeg));
        mm.addAttribute("idKeg", idKeg);

        return "kegiatan/buktikinerja/form";
    }

    @PostMapping("/form")
    public String prosesForm(@Valid BuktiKinerjaKegiatan buktiKinerjaKegiatan,
            BindingResult errors, ModelMap mm,
            MultipartFile fileKinerja, HttpServletRequest request,
            Principal principal, Authentication authentication) {

        LOGGER.debug("ID Kegiatan [{}]", buktiKinerjaKegiatan);

        KegiatanDosen kegiatanDosen = kegiatanDosenDao.findOne(buktiKinerjaKegiatan.getKegiatanDosen().getId());
        if (kegiatanDosen == null) {
            LOGGER.error("ID Kegiatan Dosen tidak ditemukan");
            return "redirect:/";
        }
        
        if (errors.hasErrors()) {
            mm.addAttribute("listBukti", buktiKinerjaKegiatanDao.findByKegiatanDosenId(kegiatanDosen.getId(), new PageRequest(0, 10)));
            mm.addAttribute("buktiKinerjaKegiatan", buktiKinerjaKegiatan);
            mm.addAttribute("idKeg", buktiKinerjaKegiatan.getKegiatanDosen().getId());
            return "kegiatan/buktikinerja/form";
        }

        if (fileKinerja != null && !fileKinerja.isEmpty()) {
            if (fileKinerja.getSize() > 2097152) {
                LOGGER.info("UPLOAD GAGAL");
                LOGGER.info("BESAR FILE YANG DI UPLOAD === [{}]", fileKinerja.getSize());
                LOGGER.info("MAXIMUM BESAR FILE === [{}]", 2097152);

                errors.addError(new FieldError("url", "url", "File terlalu besar, max 2mb"));
                mm.addAttribute("listBukti", buktiKinerjaKegiatanDao.findByKegiatanDosenId(kegiatanDosen.getId(), new PageRequest(0, 10)));
                mm.addAttribute("buktiKinerjaKegiatan", buktiKinerjaKegiatan);
                mm.addAttribute("idKeg", kegiatanDosen.getId());
                return "kegiatan/buktikinerja/form";
            } else {
                String extention = tokenizer(fileKinerja.getOriginalFilename(), ".");
                if (FILE_EXTENSION.contains(extention.toLowerCase())) {
                    File file = imageService.moveFile(fileKinerja, "bukti-kinerja", extention);
                    buktiKinerjaKegiatan.setUrl(file.getName());
                } else {
                    errors.addError(new FieldError("url", "url", "File yang diperbolehkan png, jpg, jpeg"));
                    mm.addAttribute("listBukti", buktiKinerjaKegiatanDao.findByKegiatanDosenId(buktiKinerjaKegiatan.getId(), new PageRequest(0, 10)));
                    mm.addAttribute("buktiKinerjaKegiatan", buktiKinerjaKegiatan);
                    mm.addAttribute("idKeg", kegiatanDosen.getId());
                    return "kegiatan/buktikinerja/form";
                }
            }
        } else {
            errors.addError(new FieldError("url", "url", "File tidak boleh kosong"));
            mm.addAttribute("listBukti", buktiKinerjaKegiatanDao.findByKegiatanDosenId(buktiKinerjaKegiatan.getId(), new PageRequest(0, 10)));
            mm.addAttribute("buktiKinerjaKegiatan", buktiKinerjaKegiatan);
            mm.addAttribute("idKeg", kegiatanDosen.getId());
            return "kegiatan/buktikinerja/form";
        }

        buktiKinerjaKegiatan.setKegiatanDosen(kegiatanDosen);
        buktiKinerjaKegiatanDao.save(buktiKinerjaKegiatan);
        return "redirect:/kegiatan/buktikinerja/form?idKeg=" + kegiatanDosen.getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id, @RequestParam String idKeg, ModelMap mm,
            Principal principal, Authentication authentication) {
        if (id != null && !id.isEmpty()) {
            BuktiKinerjaKegiatan buktiKinerjaKegiatan = buktiKinerjaKegiatanDao.findOne(id);
            buktiKinerjaKegiatanDao.delete(buktiKinerjaKegiatan);

            // remove file from resource
            imageService.removeFile("bukti-kinerja", buktiKinerjaKegiatan.getUrl());
        }

        return "redirect:/kegiatan/buktikinerja/form?idKeg=" + idKeg;
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
