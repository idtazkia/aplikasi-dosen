package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.ProgramStudi;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author muhsin
 */

public interface ProgramStudiDao extends PagingAndSortingRepository<ProgramStudi, String> {
    
}
