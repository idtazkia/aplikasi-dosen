package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Dosen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Repository
public interface DosenDao extends PagingAndSortingRepository<Dosen, String> {
    Dosen findOneByEmail(String email);
    Page<Dosen> findByNamaContainingIgnoreCase(String name, Pageable pageable);
    public Dosen findByUserId(String userId);
    
}
