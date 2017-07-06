/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.KategoriKegiatan;
import id.ac.tazkia.dosen.entity.KegiatanDosen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivans
 */
@Repository
public interface KegiatanDosenDao extends PagingAndSortingRepository<KegiatanDosen, String> {
    Page<KegiatanDosen> findByDosenAndKategoriKegiatan(Dosen dosen, KategoriKegiatan kk, Pageable pageable);
    Page<KegiatanDosen> findByKategoriKegiatan(KategoriKegiatan kk, Pageable pageable);
}
