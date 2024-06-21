package com.iwomi.projet.file.service;

import com.iwomi.projet.file.controller.ImageController;
import com.iwomi.projet.file.model.FileImage;
import com.iwomi.projet.file.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StorageService implements FileSevice {

    @Autowired
    private  StorageRepository repository;

    public String uploatImage(MultipartFile file) throws IOException {

        FileImage fileImage = repository.save(FileImage.builder().name(file.getOriginalFilename()).type(file.getContentType()).fileImage(ImageController.compressImage(file.getBytes())).build());
        return "file uploaded successfully :" + file.getOriginalFilename();
    }


    public byte[] downloadImage(String filename){
        Optional<FileImage> dbFileImage = repository.findByName(filename);
        return ImageController.decompressImage(dbFileImage.get().getFileImage());
    }

    @Override
    public FileSevice rename(long id, FileImage fileImage) {
        return (FileSevice) repository.findById(id).map(p-> {
            p.setName(fileImage.getName());
            p.setType(fileImage.getType());
            return repository.save(p);
        } ).orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public String delete(long id) {

        repository.deleteById(id);
        return "Deleted files";
    }
}
