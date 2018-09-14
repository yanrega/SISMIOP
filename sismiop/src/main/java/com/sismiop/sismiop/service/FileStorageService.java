package com.sismiop.sismiop.service;

import com.sismiop.sismiop.Exception.FileStorageException;
import com.sismiop.sismiop.Exception.MyFileNotFoundException;
import com.sismiop.sismiop.configuration.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileStorageService {
    private Path fileStorageLocation;

    public FileStorageService() {
    }

    public void setFileStorageLocation(String location) {
        FileStorageProperties fileStorageProperties = new FileStorageProperties();
        fileStorageProperties.setUploadDir(location);
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")) {
                throw  new FileStorageException("Sorry ! Filename contains invalid path sequence");
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        }catch (IOException ex) {
            throw  new FileStorageException("Could not store file " +fileName, ex);
        }
    }



    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw  new MyFileNotFoundException("File not found" + fileName);
            }
        }catch (MalformedURLException ex) {
            throw  new MyFileNotFoundException("File not found" +fileName, ex);
        }
    }


}
