package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import id.ac.tazkia.dosen.entity.JenisPengajuanDokumenProfile;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisPengajuanDokumenProfileDao extends PagingAndSortingRepository<JenisPengajuanDokumenProfile, String>{
    
    Page<JenisPengajuanDokumenProfile> findByNamaContainingIgnoreCase(String nama,Pageable pageable);
    List<JenisPengajuanDokumenProfile> findByRequired(Boolean required);
}
