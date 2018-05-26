package com.byk.services;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Service
public class ImageStorageImpl implements ImageStorage {

    private String RESOURCE_LOCATION_ROOT = "classpath:";

    public File get(String name) throws FileNotFoundException {
        File file  = ResourceUtils.getFile(RESOURCE_LOCATION_ROOT + name);
        return file;
    }

    public String save(File file) {
        return "new_filename.jpg";
    }

}
