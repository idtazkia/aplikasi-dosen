
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Kota;
import id.ac.tazkia.dosen.entity.Provinsi;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KotaDao extends PagingAndSortingRepository<Kota, String>{
  
    public id.ac.tazkia.dosen.entity.Kota findByKode(String kode);
    public List<Kota> findByProvinsi(Provinsi provinsi);
}
