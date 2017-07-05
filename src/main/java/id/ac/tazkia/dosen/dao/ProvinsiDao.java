
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.Provinsi;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinsiDao extends PagingAndSortingRepository<Provinsi, String>{
    
    public Provinsi findByKode(String kode);
}
