package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.PengajuanDosenProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanDosenProfileDao extends PagingAndSortingRepository<PengajuanDosenProfile, String> {
    public PengajuanDosenProfile findByDosen(Dosen d);
    public Page<PengajuanDosenProfile> findByDosen(Dosen d, Pageable pageable);
}
