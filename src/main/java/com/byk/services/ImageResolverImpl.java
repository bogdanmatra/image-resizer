package com.byk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageResolverImpl implements ImageResolver{

    @Autowired
    ImageStorage imageStorage;

    @Autowired
    ImageResizer imageResizer;

    public InputStreamResource resolve(String type, String reference) throws IOException {
        File file = imageStorage.get(reference);
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage thumbnail = imageResizer.resize(bufferedImage, "thumbnail");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(thumbnail, "jpg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return new InputStreamResource(is);
    }

}
