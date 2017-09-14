/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller.rest;

import id.ac.tazkia.dosen.dao.ProgramStudiDao;
import id.ac.tazkia.dosen.entity.ProgramStudi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ivans
 */
@RestController
@RequestMapping("/api/program_studi")
public class ProgramStudiControllerRest {

    @Autowired
    private ProgramStudiDao programStudiDao;

    @RequestMapping(value = "/byFakultas/{id}", method = RequestMethod.GET)
    public List<ProgramStudi> mataKuliahByName(@PathVariable String id) {
        return programStudiDao.findByFakultasId(id);
    }
}
