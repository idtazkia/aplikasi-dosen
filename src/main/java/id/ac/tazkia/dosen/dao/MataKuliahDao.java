/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.MataKuliah;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author razi
 */
public interface MataKuliahDao extends PagingAndSortingRepository<MataKuliah, String> {
    Page<MataKuliah> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}