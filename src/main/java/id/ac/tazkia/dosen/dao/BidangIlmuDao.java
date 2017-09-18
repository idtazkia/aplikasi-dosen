package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.BidangIlmu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ari
 */
@Repository
public interface BidangIlmuDao extends PagingAndSortingRepository<BidangIlmu, String>{
    
    public Page<BidangIlmu> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
