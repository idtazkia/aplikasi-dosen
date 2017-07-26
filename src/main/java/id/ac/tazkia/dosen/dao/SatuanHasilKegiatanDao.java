package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.SatuanHasilKegiatan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimmy
 */
@Repository
public interface SatuanHasilKegiatanDao extends PagingAndSortingRepository<SatuanHasilKegiatan, String> {
    
}
