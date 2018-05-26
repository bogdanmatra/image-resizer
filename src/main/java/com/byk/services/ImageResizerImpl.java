package com.byk.services;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class ImageResizerImpl implements ImageResizer {

    public BufferedImage resize(BufferedImage bufferedImage, String type) throws IOException {
        return Thumbnails.of(bufferedImage)
                .size(300, 300)
                .asBufferedImage();
    }
}
