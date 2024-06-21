package com.iwomi.projet.file;

import com.iwomi.projet.file.model.FileImage;
import com.iwomi.projet.file.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class Application {

	@Autowired
	private StorageService service;

	@PostMapping
	public ResponseEntity<?>uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = service.uploatImage(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?>downloadImage(@PathVariable String fileName){

		byte[] fileImage = service.downloadImage(fileName);

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(fileImage);
	}

	@PutMapping("/rename/{id}")
	public FileImage rename(@PathVariable long id, @RequestBody FileImage fileImage){
		return (FileImage) service.rename(id, fileImage);
	}

	@DeleteMapping("/delete/{id}")
	public String delete (@PathVariable long id){
		return service.delete(id);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
