package com.byk.services;

import com.byk.exceptions.ImageResizerException;
import com.google.gson.JsonObject;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class ImageResizerImpl implements ImageResizer {

    @Autowired
    ResizeConfiguration resizeConfiguration;

    public BufferedImage resize(BufferedImage bufferedImage, String type) throws ImageResizerException {
        JsonObject configuration = resizeConfiguration.getConfiguration(type);
        if (configuration == null) {
            throw new ImageResizerException(HttpStatus.INTERNAL_SERVER_ERROR, "Configuration is not available: " + type);
        }
        final int height = configuration.getAsJsonPrimitive("height").getAsInt();
        final int width = configuration.getAsJsonPrimitive("width").getAsInt();
        // TODO Map other properties to the image resize library.
        try {
            return Thumbnails.of(bufferedImage)
                    .height(height)
                    .width(width)
                    .asBufferedImage();
        } catch (IOException exception) {
            throw new ImageResizerException(HttpStatus.INTERNAL_SERVER_ERROR, "Image could not be resized to type: " + type);
        }
    }
}
