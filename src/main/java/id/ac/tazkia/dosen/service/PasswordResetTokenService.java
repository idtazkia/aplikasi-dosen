/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.service;

import id.ac.tazkia.dosen.dao.PasswordResetTokenDao;
import id.ac.tazkia.dosen.entity.PasswordResetToken;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivans
 */
@Service
@Transactional
@EnableScheduling
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenDao passwordResetTokenDao;

    private Logger logger = LoggerFactory.getLogger(PasswordResetTokenService.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordResetTokenDao.findByToken(token);
        if (passToken == null) {
            return "Token tidak ditemukan";
        }
        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate()
                .getTime() - cal.getTime()
                        .getTime()) <= 0) {
            return "Token telah kadaluarsa";
        }
        return null;
    }

    @Scheduled(cron = "0 1 0 * * ?")
    public void reportCurrentTime() {
        long now = System.currentTimeMillis();
        logger.info("Scheduler run on {}", dateFormat.format(new Timestamp(now)));

        passwordResetTokenDao.deleteExpiredToken(new Date());
    }
}
