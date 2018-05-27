package com.byk.services;

import com.google.gson.JsonObject;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class ImageResizerImpl implements ImageResizer {

    @Autowired
    ResizeConfiguration resizeConfiguration;

    public BufferedImage resize(BufferedImage bufferedImage, String type) throws IOException {
        JsonObject configuration = resizeConfiguration.getConfiguration(type);
        final int height = configuration.getAsJsonPrimitive("height").getAsInt();
        final int width = configuration.getAsJsonPrimitive("width").getAsInt();
        // TODO Map other properties to the image resize library.
        return Thumbnails.of(bufferedImage)
                .height(height)
                .width(width)
                .asBufferedImage();
    }
}
