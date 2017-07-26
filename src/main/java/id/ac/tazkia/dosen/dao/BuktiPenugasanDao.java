package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.BuktiPenugasan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimmy
 */
@Repository
public interface BuktiPenugasanDao extends PagingAndSortingRepository<BuktiPenugasan, String>{
    
    Page<BuktiPenugasan> findByKegiatanBelajarMengajarId(String id, Pageable pageable);
    
}
