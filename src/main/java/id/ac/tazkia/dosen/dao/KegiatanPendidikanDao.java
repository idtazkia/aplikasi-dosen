package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.KinerjaPendidikan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KegiatanPendidikanDao extends PagingAndSortingRepository<KinerjaPendidikan, String> {

}
