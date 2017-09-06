package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.JenisDokumenPengajuanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jenisDokumenPengajuan")
public class JenisDokumenPengajuanController {
    @Autowired
    private JenisDokumenPengajuanDao jenisDokumenPengajuanDao;

}
