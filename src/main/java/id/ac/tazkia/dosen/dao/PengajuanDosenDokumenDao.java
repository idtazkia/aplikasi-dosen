package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.PengajuanDosenDokumen;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanDosenDokumenDao  extends PagingAndSortingRepository<PengajuanDosenDokumen, String> {
}
