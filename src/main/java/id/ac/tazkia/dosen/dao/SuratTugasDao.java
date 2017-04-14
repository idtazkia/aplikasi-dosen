package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.SuratTugas;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ronny susetyo  <ronny at susetyo.com>
 * @since 14 Apr 2017
 */
@Repository
public interface SuratTugasDao extends PagingAndSortingRepository<SuratTugas, String> {
}