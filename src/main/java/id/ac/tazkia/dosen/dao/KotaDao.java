package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Kota;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author muhsin
 */
public interface KotaDao extends PagingAndSortingRepository<Kota, String> {
    
}
