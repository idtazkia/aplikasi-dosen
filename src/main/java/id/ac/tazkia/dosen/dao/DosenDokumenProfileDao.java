package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Dosen;
import id.ac.tazkia.dosen.entity.JenisPengajuanDokumenProfile;
import id.ac.tazkia.dosen.entity.PengajuanDosenDokumen;
import id.ac.tazkia.dosen.entity.DosenDokumenProfile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DosenDokumenProfileDao extends PagingAndSortingRepository<DosenDokumenProfile, String>{
    
    public DosenDokumenProfile findByDosenAndJenisPengajuanDokumen(Dosen profile, JenisPengajuanDokumenProfile jenis);

    public DosenDokumenProfile findByDosenIdAndJenisPengajuanDokumenId(String idDosen, String idJenis);
}
