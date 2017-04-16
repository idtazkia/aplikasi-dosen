package id.ac.tazkia.dosen.controller;

/**
 * Created by yogi on 03/04/2017.
 */
import id.ac.tazkia.dosen.dao.JenisSuratDao;
import id.ac.tazkia.dosen.entity.JenisSurat;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class JenisSuratController {

    @Autowired
    private JenisSuratDao jenisSuratDao;

    @RequestMapping("/jenissurat/list")
    public ModelMap daftarJenisSurat(){
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("jenisSurat", jenisSuratDao.findAll());
        return modelMap;
    }

    @RequestMapping(value = "/jenissurat/form", method = RequestMethod.GET)
    public ModelMap tampilkanForm(@RequestParam(value = "id", required = false) String id) {
        
        if(!StringUtils.isEmpty(id)) {
            JenisSurat jenisSurat = jenisSuratDao.findOne(id);
            if(jenisSurat != null) {
                return new ModelMap("jenisSurat", jenisSurat);
            }
        }
        return new ModelMap("jenisSurat", new JenisSurat());
            
    }
    @RequestMapping(value = "/jenissurat/form", method = RequestMethod.POST)
    public String prosesForm(@ModelAttribute @Valid JenisSurat jenisSurat, BindingResult err, SessionStatus status) {
        if (err.hasErrors()) {
            return "/jenissurat/form";
        }
        jenisSuratDao.save(jenisSurat);
        status.setComplete();
        return "redirect:/jenissurat/list";
    }
    
}