package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenisDokumenPengajuanDao  extends PagingAndSortingRepository<JenisPengajuanDokumen, String> {

}
