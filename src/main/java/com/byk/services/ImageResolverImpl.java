package com.byk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageResolverImpl implements ImageResolver {

    @Autowired
    ImageStorage imageStorage;

    @Autowired
    ImageResizer imageResizer;

    @Autowired
    ResizeConfiguration resizeConfiguration;

    private String ORIGINAL_TYPE = "original";

    public InputStreamResource resolve(String type, String reference) throws IOException {
        if (!type.endsWith(ORIGINAL_TYPE)) {
            try {
                File storedResizedImage = imageStorage.get(getResizedFileName(reference, type));
                return new InputStreamResource(new FileInputStream(storedResizedImage));
            } catch (FileNotFoundException exception) {
                File file = imageStorage.get(reference);
                BufferedImage resizedImage = getResizedImage(file, type);
                imageStorage.save(resizedImage, getResizedFileName(reference, type));
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, "jpg", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                return new InputStreamResource(is);
            }
        } else {
            File file = imageStorage.get(reference);
            return new InputStreamResource(new FileInputStream(file));
        }
    }

    private BufferedImage getResizedImage(File file, String type) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage resizedImage = imageResizer.resize(bufferedImage, type);
        return resizedImage;
    }

    private String getResizedFileName(String reference, String type) {
        return type + "_" + reference;
    }

}