package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.Jabatan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yogi on 03/05/2017.
 */
@Repository
public interface JabatanDao extends PagingAndSortingRepository<Jabatan, String> {

}
