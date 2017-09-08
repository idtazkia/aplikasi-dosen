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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public String tampilkanForm(@RequestParam(required = true) String id,
            ModelMap mm, Principal principal, Authentication authentication) {

        PengajuanDosenProfile pengajuan = pengajuanDosenProfileDao.findOne(id);
        if (pengajuan == null) {
            throw new NullPointerException("Data Pengajuan PAK Tidak Ditemukan");
        }

        Dosen dosen = new Dosen();

        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PENGAJUAN_ALL"))) {
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
        progressDocument.put("percent", (docApproved*100)/listDokumen.size());

        mm.addAttribute("progressBar", progressDocument);
        mm.addAttribute("pengajuan", pengajuan);
        mm.addAttribute("listDokumen", listDokumen);
        return "pengajuan/dokumen/list";
    }
}
