package id.ac.tazkia.dosen.controller;

/**
 * Created by yogi on 30/03/2017.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class DatadosenController {

    @RequestMapping("/datadosen/list")
    public ModelMap daftarDosen(){
        return new ModelMap();
    }

    @RequestMapping(value = "/datadosen/form", method = RequestMethod.GET)
    public void tampilkanForm() {
    }
}
