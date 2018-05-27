package com.byk.services;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface ImageResolver {

    /**
     * Resolves the image request to the original image or a resized image.
     * Resized images are saved and are resized only at the first request.
     * @param type Resize type {@link ResizeConfiguration}
     * @param reference Original image name.
     * @return
     * @throws IOException
     */
    InputStreamResource resolve(String type, String reference) throws IOException;
}
