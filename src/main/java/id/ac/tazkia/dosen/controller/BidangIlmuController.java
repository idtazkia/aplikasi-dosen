package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.BidangIlmuDao;
import id.ac.tazkia.dosen.entity.BidangIlmu;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ari
 */
@Controller
public class BidangIlmuController {
    
    @Autowired
    private BidangIlmuDao bidangIlmuDao;
    
    @GetMapping("/bidangilmu/list")
    public ModelMap bidangIlmu(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("bidangIlmu", bidangIlmuDao.findByNamaContainingIgnoreCase(value, pageable));
        } else {
            return new ModelMap().addAttribute("bidangIlmu", bidangIlmuDao.findAll(pageable));
        }
    }

    @GetMapping("/bidangilmu/form")
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) BidangIlmu bidangIlmu) {
        if (bidangIlmu == null) {
            bidangIlmu = new BidangIlmu();
        }
        return new ModelMap("bidangIlmu", bidangIlmu);
    }

    @PostMapping("/bidangilmu/form")
    public String simpan(@ModelAttribute @Valid BidangIlmu bidangIlmu, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/bidangilmu/form";
        }
        bidangIlmuDao.save(bidangIlmu);
        status.setComplete();
        return "redirect:/bidangilmu/list";
    }

    @GetMapping("/bidangilmu/delete")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) BidangIlmu bidangIlmu) {
        return new ModelMap("bidangIlmu", bidangIlmu);
    }

    @PostMapping("/bidangilmu/delete")
    public Object delete(@ModelAttribute BidangIlmu bidangIlmu, SessionStatus status) {
        try{
            bidangIlmuDao.delete(bidangIlmu);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", bidangIlmu.getNama())
                    .addObject("entityName", "bidangIlmu")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink","/bidangIlmu/list");
        }
        status.setComplete();
        return "redirect:/bidangilmu/list";
    }
}
