package com.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
//@Controller
//@RequiredArgsConstructor
@RequestMapping(value = "/images")
public class ImagesController {
    @Value("${uploadDir}")
    private String uploadDir;

//    show Image
//    @GetMapping(value = "/images/{name}")
////    @ResponseBody
//    public Resource showImage(@PathVariable("name") String name) throws IOException {
//        Path path = Paths.get(uploadDir + "/" + name + ".jpg");
//        Resource resource = new ByteArrayResource(Files.readAllBytes(path));
//        return resource;
//    }

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
    public ResponseEntity<InputStreamResource> showImage(@PathVariable("name") String name) throws IOException {
        String imagePath = uploadDir + "/" + name;
        File file = new File(imagePath);

        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamResource resource = new InputStreamResource(inputStream);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
