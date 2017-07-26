/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.JenisBuktiKegiatan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author anggi
 */
public interface JenisBuktiKegiatanDao extends PagingAndSortingRepository<JenisBuktiKegiatan, String>{
    Page<JenisBuktiKegiatan> findByNamaContainingIgnoreCase(String nama,Pageable pageable);
    
}
