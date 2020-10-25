package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
public class UploadController {
    /**
     * 自定义上传文件路径
     */
    @Value("${file.upload.path}")
    String fileUploadPath;


    @PostMapping(value = "/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败";
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        // 拼接文件的绝对路径
        String destFileName = fileUploadPath + File.separator + fileName;

        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            Path path = Paths.get(destFileName);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "文件保存路径为" + destFileName;
    }

}
