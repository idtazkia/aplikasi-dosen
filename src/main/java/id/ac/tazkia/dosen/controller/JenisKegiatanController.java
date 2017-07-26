package id.ac.tazkia.dosen.controller;

/**
 * Created by yogi on 03/05/2017. Updated by Razi on 08/06/2017.
 */
import id.ac.tazkia.dosen.dao.JenisKegiatanDao;
import id.ac.tazkia.dosen.dao.KategoriKegiatanDao;
import id.ac.tazkia.dosen.entity.JenisKegiatan;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JenisKegiatanController {

    @Autowired
    private JenisKegiatanDao jenisKegiatanDao;
    
    @Autowired
    private KategoriKegiatanDao kategoriKegiatanDao;

    @RequestMapping("/jeniskegiatan/list")
    public String jenisKegiatan(Model model, @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(name = "value",required = false) String value) {
        
        if(value != null){
            model.addAttribute("key",value);
            model.addAttribute("data",jenisKegiatanDao.findByNamaContainingIgnoreCase(value, pageable));
        }else{
            model.addAttribute("data", jenisKegiatanDao.findAll(pageable));
        }
        return "/jeniskegiatan/list";
    }

    @GetMapping("/jeniskegiatan/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) JenisKegiatan jeniskegiatan, Model m) {
        if (jeniskegiatan == null) {
            jeniskegiatan = new JenisKegiatan();
        }
        m.addAttribute("jenisKegiatan", jeniskegiatan);
        m.addAttribute("listKategoriKegiatan", kategoriKegiatanDao.findAll());
        return "/jeniskegiatan/form";
    }

    @PostMapping("/jeniskegiatan/form")
    public String simpan(@ModelAttribute @Valid JenisKegiatan jenisKegiatan, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/jeniskegiatan/form";
        }
        jenisKegiatanDao.save(jenisKegiatan);
        status.setComplete();
        return "redirect:/jeniskegiatan/list";
    }

    @GetMapping("/jeniskegiatan/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) JenisKegiatan jeniskegiatan) {
        return new ModelMap("jeniskegiatan", jeniskegiatan);
    }

    @PostMapping("/jeniskegiatan/delete")
    public Object delete(@ModelAttribute JenisKegiatan jeniskegiatan, SessionStatus status) {
        try{
            jenisKegiatanDao.delete(jeniskegiatan);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", jeniskegiatan.getNama())
                    .addObject("entityName", "JenisKegiatan")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/jeniskegiatan/list");
        }
        status.setComplete();
        return "redirect:/jeniskegiatan/list";
    }
}