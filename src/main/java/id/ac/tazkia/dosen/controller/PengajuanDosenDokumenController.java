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
import id.ac.tazkia.dosen.service.ImageService;
import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    private ImageService imageService;
    @Autowired
    private DosenDao dosenDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(PengajuanDosenDokumenController.class);
    private final List<String> FILE_EXTENSION = Arrays.asList("png", "jpg", "jpeg", "pdf");

    @GetMapping("/list")
    public String tampilkanForm(@RequestParam(required = true) String id,
            ModelMap mm, Principal principal, Authentication authentication) {

        PengajuanDosenProfile pengajuan = pengajuanDosenProfileDao.findOne(id);
        if (pengajuan == null) {
            throw new NullPointerException("Data Pengajuan PAK Tidak Ditemukan");
        }

        Dosen dosen = new Dosen();
        Boolean isAdmin = Boolean.TRUE;
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            isAdmin = Boolean.FALSE;
            dosen = dosenDao.findOneByEmail(principal.getName());
            if (dosen == null || !pengajuan.getDosen().getId().equalsIgnoreCase(dosen.getId())) {
                throw new NullPointerException("Data Dosen Tidak Ditemukan");
            }
        }

        Iterable<JenisPengajuanDokumen> listTipeDokumen = jenisDokumenPengajuanDao.findAll();

        List<PengajuanDosenDokumen> listDokumen = new ArrayList<>();
        int docApproved = 0;
        for (JenisPengajuanDokumen jenis : listTipeDokumen) {
            PengajuanDosenDokumen dokumen = pengajuanDosenDokumenDao.findByPengajuanDosenAndJenisPengajuanDokumen(pengajuan, jenis);
            if (dokumen == null) {
                dokumen = new PengajuanDosenDokumen();
                dokumen.setFilename(null);
                dokumen.setJenisPengajuanDokumen(jenis);
                dokumen.setPengajuanDosen(pengajuan);
                dokumen.setStatusDokumen(StatusDokumenPengajuan.PENDING);
            } else {
                if (dokumen.getStatusDokumen().equals(StatusDokumenPengajuan.APPROVED)) {
                    docApproved += 1;
                }
            }

            listDokumen.add(dokumen);
        }

        Map<String, Object> progressDocument = new HashMap<>();
        progressDocument.put("totalDoc", "Dari " + listDokumen.size() + " dokumen.");
        progressDocument.put("approvDoc", docApproved);
        progressDocument.put("percent", (docApproved * 100) / listDokumen.size());

        mm.addAttribute("progressBar", progressDocument);
        mm.addAttribute("pengajuan", pengajuan);
        mm.addAttribute("listDokumen", listDokumen);
        mm.addAttribute("isAdmin", isAdmin);
        return "pengajuan/dokumen/list";
    }

    @GetMapping("/approve/{id}")
    public String approveDokumen(@PathVariable String id,
            Principal principal, Authentication authentication) throws Exception {

        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            throw new Exception("Anda Tidak Memiliki Access Approval");
        }

        PengajuanDosenDokumen dokumen = pengajuanDosenDokumenDao.findOne(id);
        if (dokumen == null) {
            throw new Exception("Dokumen Pengajuan Tidak Ditemukan");
        }
        dokumen.setStatusDokumen(StatusDokumenPengajuan.APPROVED);
        pengajuanDosenDokumenDao.save(dokumen);
        LOGGER.info("DOKUMEN : " + dokumen.getStatusDokumen());

        return "redirect:/pengajuan/dokumen/list?id=" + dokumen.getPengajuanDosen().getId();
    }

    @GetMapping("/reject/{id}")
    public String rejectDokumen(@PathVariable String id,
            Principal principal, Authentication authentication) throws Exception {

        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            throw new Exception("Anda Tidak Memiliki Access Approval");
        }

        PengajuanDosenDokumen dokumen = pengajuanDosenDokumenDao.findOne(id);
        if (dokumen == null) {
            throw new Exception("Dokumen Pengajuan Tidak Ditemukan");
        }
        dokumen.setStatusDokumen(StatusDokumenPengajuan.REJECTED);
        pengajuanDosenDokumenDao.save(dokumen);
        
        return "redirect:/pengajuan/dokumen/list?id=" + dokumen.getPengajuanDosen().getId();
    }

    @Transactional
    @PostMapping("/upload")
    public String uploadDokumen(@RequestParam String idJenisDokumen, @RequestParam String idPengajuan, MultipartFile fileDokumen, HttpServletRequest request,
            Principal principal, Authentication authentication) throws Exception {

        JenisPengajuanDokumen jenisDokumen = jenisDokumenPengajuanDao.findOne(idJenisDokumen);
        if (jenisDokumen == null) {
            throw new Exception("Jenis Dokumen Tidak Ditemukan");
        }

        PengajuanDosenProfile pengajuan = pengajuanDosenProfileDao.findOne(idPengajuan);
        if (pengajuan == null) {
            throw new Exception("Profile Pengajuan Tidak Ditemukan");
        }

        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
            Dosen dosen = dosenDao.findOneByEmail(principal.getName());
            if (dosen == null || !pengajuan.getDosen().getId().equalsIgnoreCase(dosen.getId())) {
                throw new NullPointerException("Data Dosen Tidak Ditemukan");
            }
        }

        LOGGER.info("JENIS : [{}]", jenisDokumen.getNama());

        PengajuanDosenDokumen dokumen = pengajuanDosenDokumenDao.findByPengajuanDosenAndJenisPengajuanDokumen(pengajuan, jenisDokumen);

        if (dokumen == null) {
            dokumen = new PengajuanDosenDokumen();
            dokumen.setJenisPengajuanDokumen(jenisDokumen);
            dokumen.setPengajuanDosen(pengajuan);
            dokumen.setFilename(null);
            dokumen.setStatusDokumen(StatusDokumenPengajuan.PENDING);
        }

        if (fileDokumen != null && !fileDokumen.isEmpty()) {
            if (fileDokumen.getSize() > 2097152) {
                LOGGER.info("UPLOAD GAGAL");
                LOGGER.info("BESAR FILE YANG DI UPLOAD === [{}]", fileDokumen.getSize());
                LOGGER.info("MAXIMUM BESAR FILE === [{}]", 2097152);

                throw new Exception("UPLOAD GAGAL, MAXIMUM BESAR FILE 2 MB");
            } else {
                String extention = tokenizer(fileDokumen.getOriginalFilename(), ".");
                if (FILE_EXTENSION.contains(extention.toLowerCase())) {
                    File file = imageService.moveFile(fileDokumen, "dokumen-pengajuan", extention);
                    LOGGER.info("Save Filename");
                    dokumen.setFilename(file.getName());
                    dokumen.setStatusDokumen(StatusDokumenPengajuan.PENDING);
                } else {
                    throw new Exception("File yang diperbolehkan png, jpg, jpeg, dan pdf");
                }
            }
        }
        pengajuanDosenDokumenDao.save(dokumen);

        return "redirect:/pengajuan/dokumen/list?id=" + pengajuan.getId();
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
