package com.byk.services;

import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageResizer {

    /**
     * Resizes an image to a predefined type from {@link ResizeConfiguration}.
     * @param bufferedImage The image to be resized.
     * @param type Resize configuration from {@link ResizeConfiguration}.
     * @return
     * @throws IOException
     */
    BufferedImage resize(BufferedImage bufferedImage, String type) throws IOException;

}
