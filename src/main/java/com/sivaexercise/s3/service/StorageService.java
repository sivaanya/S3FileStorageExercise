package com.sivaexercise.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3client;


    public String upload(MultipartFile file){
        File fileContent = convertMultiPartFileToFile(file);
        String fileName = file.getOriginalFilename();
        s3client.putObject(bucketName, fileName,fileContent);
        fileContent.delete();
        return "File Uploaded SuccessFully";
    }

    private File convertMultiPartFileToFile(MultipartFile file){
        File convertedFile = new File (file.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(convertedFile);
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertedFile;
    }

    public String delete(String fileName){
        s3client.deleteObject(bucketName, fileName);
        return "Successfully removed file from S3 bucket";
    }





}
