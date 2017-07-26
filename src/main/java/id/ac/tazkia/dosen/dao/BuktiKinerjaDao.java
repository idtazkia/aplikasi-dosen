package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.BuktiKinerja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimmy
 */
@Repository
public interface BuktiKinerjaDao extends PagingAndSortingRepository<BuktiKinerja, String>{

    Page<BuktiKinerja> findByKegiatanBelajarMengajarId(String id, Pageable pageable);
    
}
