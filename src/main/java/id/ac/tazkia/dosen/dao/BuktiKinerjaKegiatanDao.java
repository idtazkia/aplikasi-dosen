package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.BuktiKinerjaKegiatan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimmy
 */
@Repository
public interface BuktiKinerjaKegiatanDao extends PagingAndSortingRepository<BuktiKinerjaKegiatan, String>{

    Page<BuktiKinerjaKegiatan> findByKegiatanDosenId(String id, Pageable pageable);
    
}
