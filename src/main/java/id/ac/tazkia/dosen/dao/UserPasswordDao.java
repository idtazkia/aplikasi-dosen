/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.dao;

import id.ac.tazkia.dosen.entity.UserPassword;
import java.io.Serializable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author ari
 */
public interface UserPasswordDao extends PagingAndSortingRepository<UserPassword, String>{
    
    public UserPassword findById(String userId);
}
