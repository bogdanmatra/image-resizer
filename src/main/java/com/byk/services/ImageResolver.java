package com.byk.services;

import com.byk.exceptions.ImageResizerException;

import java.io.InputStream;

public interface ImageResolver {

    /**
     * Resolves the image request to the original image or a resized image.
     * Resized images are resized and saved only at the first request.
     * @param type Resize type {@link ResizeConfiguration}
     * @param reference Original image name.
     * @return
     * @throws ImageResizerException
     */
    InputStream resolve(String type, String reference) throws ImageResizerException;
}
