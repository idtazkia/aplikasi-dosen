/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author iqbal
 */
@RequestMapping("/pengajuan-pak")
@Controller
public class PengajuanPak {
    
    private final String FORM = "pengajuan/pak/form";
    
    @RequestMapping("/form")
    public String listDocument(String string){
        return FORM;
    }
    
}
