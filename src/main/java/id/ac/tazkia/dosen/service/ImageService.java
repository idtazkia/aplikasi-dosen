/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.tazkia.dosen.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ivans
 */
@Service
public class ImageService {

    private final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    @Value("${app.folder.upload}")
    public String uploadDir;
    
    public File moveFile(MultipartFile multipartFile, String tpe, String extension) {
        try {
            String folder = uploadDir + File.separator + tpe + File.separator;
            File folderImages = new File(folder);
            if (!folderImages.exists()) {
                LOGGER.debug("Creating folder [{}]", folderImages.getAbsolutePath());
                Files.createDirectories(folderImages.toPath());
            }
            
            String random = UUID.randomUUID().toString().substring(0, 7);
            LOGGER.info("random [{}]", random);
            LOGGER.info("multipartFile [{}]", multipartFile.getSize());
            
            String fileName = multipartFile.getOriginalFilename();
            int occurance = StringUtils.countOccurrencesOf(fileName, ".");
            if(occurance > 1) {
                for(int i = 0; i < occurance - 1; i++){
                    fileName = fileName.replaceFirst("\\.","-");
		
                }
            }
            
            
            fileName = fileName.replace(" ", "-");
            fileName = fileName.replace("_", "-");
            fileName = fileName.replaceAll("[^\\w\\-\\.]", "");
            String name = random + "-" + fileName;

            //save original
            File originalFile = new File(folder + name);
            LOGGER.info("original File [{}]", originalFile.getPath());
            Files.copy(multipartFile.getInputStream(), originalFile.toPath());

            
            return originalFile;
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
    private Path load(String folder, String filename){
        return new File(uploadDir).toPath().resolve(folder+"/"+filename);
    }
    
    public void removeFile(String folder, String filename) {
        Path pathOfFile = new File(uploadDir).toPath().resolve(folder+"/"+filename);
        
        File f = pathOfFile.toFile();
        if(f.exists()) {
            f.delete();
        }
    }
    
    public Resource loadAsResource(String filename, String tpe) throws MalformedURLException {
        Path filePath = load(tpe, filename);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new MalformedURLException("Could not read file: " + filename);
        }
    }
}
