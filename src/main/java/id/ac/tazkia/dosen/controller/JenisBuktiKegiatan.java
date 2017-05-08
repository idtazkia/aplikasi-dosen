package id.ac.tazkia.dosen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yogi on 05/05/2017.
 */
@Controller
public class JenisBuktiKegiatan {

    @RequestMapping("/jenisbuktikegiatan/list")
    public ModelMap jenisBuktiKegiatan(){
        return new ModelMap();
    }

    @RequestMapping(value = "/jenisbuktikegiatan/form", method = RequestMethod.GET)
    public void tampilkanForm() {
    }
}
