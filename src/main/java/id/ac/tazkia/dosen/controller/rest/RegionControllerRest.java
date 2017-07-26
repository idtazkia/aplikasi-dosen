/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller.rest;

import id.ac.tazkia.dosen.dao.KecamatanDao;
import id.ac.tazkia.dosen.dao.KotaDao;
import id.ac.tazkia.dosen.dao.ProvinsiDao;
import id.ac.tazkia.dosen.entity.Kecamatan;
import id.ac.tazkia.dosen.entity.Kota;
import id.ac.tazkia.dosen.entity.Provinsi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/region")
public class RegionControllerRest {

    @Autowired
    private ProvinsiDao provinsiDao;
    @Autowired
    private KotaDao kotaDao;
    @Autowired
    private KecamatanDao kecamatanDao;

    @RequestMapping(method = RequestMethod.GET, value = "/get/provinsi", produces = "application/json")
    public Iterable<Provinsi> getProvinsi() {
        Iterable<Provinsi> result = provinsiDao.findAll();
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/kota/{id}", produces = "application/json")
    public List<Kota> getKota(@PathVariable String id) {
        Provinsi p = provinsiDao.findOne(id);
        return kotaDao.findByProvinsi(p);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/kecamatan/{id}", produces = "application/json")
    public List<Kecamatan> getKecamatan(@PathVariable String id) {
        Kota k = kotaDao.findOne(id);
        return kecamatanDao.findByKota(k);
    }

    @RequestMapping(value = "/kecamatan/findByKode/{kode}", method = RequestMethod.GET)
    public Kecamatan findByKodeKecamatan(@PathVariable("kode") String kode) {
        return kecamatanDao.findByKode(kode);
    }
    
    @RequestMapping(value = "/provinsi/findByKode/{kode}", method = RequestMethod.GET)
    public Provinsi findByKodeProvinsi(@PathVariable("kode") String kode) {
        return provinsiDao.findByKode(kode);
    }
    
    @RequestMapping(value = "/kota/findByKode/{kode}", method = RequestMethod.GET)
    public Kota findByKodeKota(@PathVariable("kode") String kode) {
        return kotaDao.findByKode(kode);
    }
}
