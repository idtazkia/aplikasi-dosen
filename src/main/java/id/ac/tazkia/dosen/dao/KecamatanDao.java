/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Kecamatan;
import id.ac.tazkia.dosen.entity.Kota;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KecamatanDao extends PagingAndSortingRepository<Kecamatan, String> {

    List<Kecamatan> findByKota(Kota kota);
    public Kecamatan findByKode(String kode);
}
