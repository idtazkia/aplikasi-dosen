package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Kecamatan;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author muhsin
 */
public interface KecamatanDao extends PagingAndSortingRepository<Kecamatan, String> {
    
}
