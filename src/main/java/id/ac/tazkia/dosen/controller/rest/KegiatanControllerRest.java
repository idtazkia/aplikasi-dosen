/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller.rest;

import id.ac.tazkia.dosen.dao.JenisKegiatanDao;
import id.ac.tazkia.dosen.dao.MataKuliahDao;
import id.ac.tazkia.dosen.entity.JenisKegiatan;
import id.ac.tazkia.dosen.entity.MataKuliah;
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
public class KegiatanControllerRest {

    @Autowired
    private MataKuliahDao mataKuliahDao;

    @Autowired
    private JenisKegiatanDao jenisKegiatanDao;

    @RequestMapping(value = "/mata_kuliah", method = RequestMethod.GET)
    public Page<MataKuliah> mataKuliahByName(@RequestParam("nama") String nama,
            @PageableDefault(size = 10) Pageable pageable) {
        return mataKuliahDao.findByNamaContainingIgnoreCase(nama, pageable);
    }

    @RequestMapping(value = "/jenis_kegiatan", method = RequestMethod.GET)
    public Page<JenisKegiatan> jenisKegiatanByName(@RequestParam("nama") String nama,
            @PageableDefault(size = 10) Pageable pageable) {
        return jenisKegiatanDao.findByNamaContainingIgnoreCase(nama, pageable);
    }
}
