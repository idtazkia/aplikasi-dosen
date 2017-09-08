package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.JenisPengajuanDokumen;
import id.ac.tazkia.dosen.entity.PengajuanDosenDokumen;
import id.ac.tazkia.dosen.entity.PengajuanDosenProfile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PengajuanDosenDokumenDao extends PagingAndSortingRepository<PengajuanDosenDokumen, String> {

    public PengajuanDosenDokumen findByPengajuanDosenAndJenisPengajuanDokumen(PengajuanDosenProfile profile, JenisPengajuanDokumen jenis);

    public PengajuanDosenDokumen findByPengajuanDosenIdAndJenisPengajuanDokumenId(String idPengajuan, String idJenis);

}
