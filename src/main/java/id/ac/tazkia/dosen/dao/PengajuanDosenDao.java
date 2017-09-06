package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.PengajuanDosen;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanDosenDao extends PagingAndSortingRepository<PengajuanDosen, String> {
}
