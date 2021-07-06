package com.sivaexercise.s3.controllers;


import com.sivaexercise.s3.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam(value = "file") MultipartFile file){
       return new ResponseEntity<>(storageService.upload(file), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<String> delete(@PathVariable String filename){
        return new ResponseEntity<>(storageService.delete(filename), HttpStatus.OK);
    }
}
