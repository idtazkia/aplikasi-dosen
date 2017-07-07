package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.JenisKegiatan;
import id.ac.tazkia.dosen.entity.KategoriKegiatan;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yogi on 04/05/2017. Updated by Razi on 08/06/2017.
 */
@Repository
public interface JenisKegiatanDao extends PagingAndSortingRepository<JenisKegiatan, String> {
    List<JenisKegiatan> findByKategoriKegiatan(KategoriKegiatan kk);
    Page<JenisKegiatan> findByNamaContainingIgnoreCase(String nama, Pageable pageable);
}