package com.byk.services;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class ImageStorageImpl implements ImageStorage {

    private String RESOURCE_LOCATION_ROOT = "classpath:";

    public InputStream get(String name) throws FileNotFoundException {
        File file  = ResourceUtils.getFile(RESOURCE_LOCATION_ROOT + name);
        return new FileInputStream(file);
    }

    public String save(InputStream inputStream) {
        return "new_filename.jpg";
    }

}
