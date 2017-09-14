package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.ProgramStudi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author muhsin
 */

public interface ProgramStudiDao extends PagingAndSortingRepository<ProgramStudi, String> {
    public Page<ProgramStudi> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
    public List<ProgramStudi> findByFakultasId(String idFakultas);
}
