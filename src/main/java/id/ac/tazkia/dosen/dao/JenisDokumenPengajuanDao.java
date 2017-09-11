package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisDokumenPengajuanDao  extends PagingAndSortingRepository<JenisPengajuanDokumen, String> {

    Page<JenisPengajuanDokumen> findByNamaContainingIgnoreCase(String nama,Pageable pageable);
    List<JenisPengajuanDokumen> findByRequired(Boolean required);
}
