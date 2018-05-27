package com.byk.services;

import com.byk.exceptions.ImageResizerException;

import java.awt.image.BufferedImage;


public interface ImageResizer {

    /**
     * Resizes an image to a predefined type from {@link ResizeConfiguration}.
     * @param bufferedImage The image to be resized.
     * @param type Resize configuration from {@link ResizeConfiguration}.
     * @return
     * @throws ImageResizerException
     */
    BufferedImage resize(BufferedImage bufferedImage, String type) throws ImageResizerException;

}
