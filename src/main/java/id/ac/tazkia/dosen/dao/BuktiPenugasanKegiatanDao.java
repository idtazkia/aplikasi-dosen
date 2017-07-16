package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.BuktiPenugasanKegiatan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimmy
 */
@Repository
public interface BuktiPenugasanKegiatanDao extends PagingAndSortingRepository<BuktiPenugasanKegiatan, String>{
    
}
