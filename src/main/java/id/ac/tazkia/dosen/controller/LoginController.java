/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.service.MailService;
import id.ac.tazkia.dosen.dao.PasswordResetTokenDao;
import id.ac.tazkia.dosen.service.PasswordResetTokenService;
import id.ac.tazkia.dosen.dao.UserDao;
import id.ac.tazkia.dosen.entity.PasswordResetToken;
import id.ac.tazkia.dosen.entity.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ivans
 */
@Controller
public class LoginController {

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordResetTokenService passwordResetService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/login")
    public String getlogin() {
        return "login";
    }

    @GetMapping("/login/testmail")
    public String testMail() {
        String random = UUID.randomUUID().toString();
        try {
            mailService.send("vaansaa@gmail.com", "test mail" + random, "Coba test email yahh" + random);
        } catch (MessagingException ex) {
            LOGGER.error(ex.getMessage());
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(value = "/forgot_password")
    public void getForgotPassword(Model mm) {

    }

    @PostMapping(value = "/forgot_password")
    public String saveForgotPassword(@RequestParam("email") String email, Model model, HttpServletRequest request) {
        Map<String, String> msg = new HashMap<>();

        if (!StringUtils.hasText(email)) {
            LOGGER.error("Forgot Password Error, Email is null ");
            msg.put("EmailIsNull", "Email Harus Diisi");
        }

        User user = userDao.findByUsername(email);

        if (user == null) {
            LOGGER.error("Forgot Password Error, USER NOT FOUND WITH USERNAME : [{}] ", email);
            msg.put("UserNotFound", "Maaf, Email yang anda masukan tidak valid");
        } else {
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            PasswordResetToken prt = passwordResetTokenDao.findByUser(user);

            if (prt == null) {
                prt = new PasswordResetToken(token, user);
            } else {
                prt.setToken(token);
                prt.setExpiryDate(prt.calculateExpiryDate());
            }

            passwordResetTokenDao.save(prt);

            String link = getAppUrl(request) + "/reset_password?token=" + token;

            try {
                mailService.send(user.getUsername(), "Reset Password", "Link Reset Password : <a href='" + link + "'>" + token + "</a>");
            } catch (MessagingException ex) {
                LOGGER.error(ex.getMessage());
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
            }

            //kirim email notif disini
            msg.put("success", "Link Reset Password Telah Dikiriman ke email yang terdaftar");

        }
        model.addAttribute("message", msg);
        return "forgot_password";
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.GET)
    public String getResetPassword(@RequestParam("token") String token, Model mm, RedirectAttributes redir) {
        Map<String, String> message = new HashMap<>();

        if (!StringUtils.hasText(token)) {
            LOGGER.error("Error Reset Password, token is null");
            message.put("ErrorTokenNull", "Terjadi Kesalahan dalam reset password, {Token Is NUll}");
            redir.addFlashAttribute("message", message);
            return "redirect:signin";
        }

        String result = passwordResetService.validatePasswordResetToken(token);
        if (result != null) {
            message.put("ErrResetToken", result);
            redir.addFlashAttribute("message", message);
            return "redirect:signin";
        }

        mm.addAttribute("message", message);
        return "reset_password";
    }

    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public String resetPassword(String newPassword, String token, Model mm, RedirectAttributes redirectAttributes) {
        Map<String, String> message = new HashMap<>();

        if (!StringUtils.hasText(newPassword) && !StringUtils.hasText(token)) {
            LOGGER.error("Error Reset Password, PASSWORD OR TOKEN IS NULL");
            message.put("TokenOrPasswordNull", "Terjadi Kesalahan Dalam Reset Password");
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:signin";
        }

        PasswordResetToken prt = passwordResetTokenDao.findByToken(token);

        if (prt == null) {
            message.put("tokenNofFound", "token tidak ditemukan / akses token sudah kadaluarsa");
            mm.addAttribute(message);
            return "reset_password?token=" + token;
        }

        User u = prt.getUser();
        u.getUserPassword().setPassword(passwordEncoder.encode(newPassword));
        userDao.save(u);
        passwordResetTokenDao.delete(prt);
        message.put("success", "Password Anda Berhasil di Rubah, Silahkan Login Kembali");
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:signin";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
