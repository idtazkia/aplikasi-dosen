/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller.rest;

import id.ac.tazkia.dosen.dao.DosenDao;
import id.ac.tazkia.dosen.entity.Dosen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api")
public class DosenControllerRest {

    @Autowired
    private DosenDao dosenDao;

    @RequestMapping(value = "/dosen", method = RequestMethod.GET)
    public Page<Dosen> mataKuliahByName(@RequestParam("nama") String nama,
            @PageableDefault(size = 10) Pageable pageable) {
        return dosenDao.findByNamaContainingIgnoreCase(nama, pageable);
    }
}
