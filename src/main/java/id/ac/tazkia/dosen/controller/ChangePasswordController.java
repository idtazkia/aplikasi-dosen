package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.dao.PasswordResetTokenDao;
import id.ac.tazkia.dosen.dao.UserDao;
import id.ac.tazkia.dosen.dao.UserPasswordDao;
import id.ac.tazkia.dosen.entity.User;
import id.ac.tazkia.dosen.entity.UserPassword;
import id.ac.tazkia.dosen.exception.ResourceNotFoundException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ari
 */
@Controller
public class ChangePasswordController {
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;
    
    @Autowired
    private UserPasswordDao userPasswordDao;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);
    
    @GetMapping("/changepassword")
    public String changePasswordForm(){
        return "change_password";
    }
    
    @PostMapping(value = "/changepassword")
    public String saveChangePassword(Model model, String oldPassword, String newPassword,HttpServletRequest request, Principal p) throws ServletException{
        Map<String, String> msg = new HashMap<>();
        
        logger.info("old password : "+oldPassword);
        logger.info("new password : "+newPassword);
        
        User user = userDao.findByUsername(p.getName());
        if(user != null){
            logger.info("user ditemukan : "+user.getUsername());
            UserPassword password = userPasswordDao.findById(user.getId());
            if(password == null){
                return "change_password";
            }else{
                if(!passwordEncoder.matches(oldPassword, password.getPassword())){
                     logger.warn("PASSWORD NOT MATCHING !!");
                     msg.put("PASSWORD NOT MATCHING !!", "password yang anda masukan salah");
                     
                     model.addAttribute("message", msg);
                     return "change_password";
                 }else{
                     String newPassEncoded = passwordEncoder.encode(newPassword);
                     password.setPassword(newPassEncoded);
                     userPasswordDao.save(password);
                     request.logout();
                     
                     return "redirect:/";
                 }
            }
        }else {
            logger.warn("USER NOT FOUND WITH USERNAME [{}] ", p.getName());
            throw new ResourceNotFoundException("USER NOT FOUND WITH USERNAME : " + p.getName());
        }
        
    }
}
