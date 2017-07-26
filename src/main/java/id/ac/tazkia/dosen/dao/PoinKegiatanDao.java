package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.PoinKegiatan;
import id.ac.tazkia.dosen.entity.SuratTugas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by yogi on 04/05/2017.
 */
public interface PoinKegiatanDao extends PagingAndSortingRepository<PoinKegiatan, String> {
    Page<PoinKegiatan> findByJenisKegiatanNamaContainingIgnoreCase(String nama,Pageable p);
}
