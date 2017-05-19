/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.KategoriBuktiKegiatan;
import java.io.Serializable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author razi
 */
public interface KategoriBuktiKegiatanDao extends PagingAndSortingRepository<KategoriBuktiKegiatan, String>{
    
}
