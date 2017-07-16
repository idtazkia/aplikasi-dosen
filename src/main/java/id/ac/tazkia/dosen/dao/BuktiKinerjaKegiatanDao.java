package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.BuktiKinerjaKegiatan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jimmy
 */
@Repository
public interface BuktiKinerjaKegiatanDao extends PagingAndSortingRepository<BuktiKinerjaKegiatan, String>{
    
}
