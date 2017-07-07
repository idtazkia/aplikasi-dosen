package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.JenisSurat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Repository
public interface JenisSuratDao extends PagingAndSortingRepository<JenisSurat, String> {
    public Page<JenisSurat> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}
