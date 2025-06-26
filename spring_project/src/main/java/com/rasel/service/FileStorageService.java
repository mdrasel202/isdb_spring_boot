package com.rasel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private static final String UPLOAD_OIR = "uploads";

    @Autowired
    private ResourceLoader resourceLoader;

    public String storeFile(MultipartFile file) throws IOException{
        String randomFileName = UUID.randomUUID().toString().replace("_", "").substring(0,8)
                +"_"+file.getOriginalFilename();

        Path uploadPath = Paths.get(UPLOAD_OIR);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path path = uploadPath.resolve(randomFileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return randomFileName;
    }
}
