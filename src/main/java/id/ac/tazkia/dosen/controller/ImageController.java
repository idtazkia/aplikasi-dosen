/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.controller;

import id.ac.tazkia.dosen.service.ImageService;
import java.net.MalformedURLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ivans
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/penugasan/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> imagePenugasan(@PathVariable String filename) {
        try {
            Resource file = imageService.loadAsResource(filename, "bukti-penugasan");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    @GetMapping("/dokumen-pengajuan/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> filePengajuan(@PathVariable String filename) {
        try {
            Resource file = imageService.loadAsResource(filename, "dokumen-pengajuan");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    @GetMapping("/kinerja/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> imageKinerja(@PathVariable String filename) {
        try {
            Resource file = imageService.loadAsResource(filename, "bukti-kinerja");
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (MalformedURLException ex) {
            return null;
        }
    }
}
