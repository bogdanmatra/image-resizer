package com.byk.controllers;

import com.byk.services.ImageResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.byk.services"})
public class ImageController {

    @Autowired
    ImageResolver imageResolver;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ImageController.class, args);
    }

    @RequestMapping("/image/show/{predefined-type-name}/{dummy-seo-name}")
    public ResponseEntity testImage(@PathVariable("predefined-type-name") String predefinedTypeName,
                                    @RequestParam("reference") String reference) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        InputStreamResource inputStreamResource = imageResolver.resolve(predefinedTypeName, reference);
        return new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
    }

}