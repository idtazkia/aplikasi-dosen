package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.KegitanBelajarMengajar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KegiatanBelajarMengajarDao extends PagingAndSortingRepository<KegitanBelajarMengajar, String> {

}
