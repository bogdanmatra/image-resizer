package com.byk.services;

import com.byk.exceptions.ImageResizerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private String ORIGINAL_TYPE = "original";

    public InputStream resolve(String type, String reference) throws ImageResizerException {
        if (!type.endsWith(ORIGINAL_TYPE)) {
            try {
                // Get image from storage if it is already resized.
                File storedResizedImage = imageStorage.get(getResizedFileName(reference, type));
                return new FileInputStream(storedResizedImage);
            } catch (FileNotFoundException exception) {
                // Resize image, store it and return it.
                InputStream inputStream = resizeAndSave(reference, type);
                return inputStream;
            }
        } else {
            try {
                // Return original.
                File file = imageStorage.get(reference);
                return new FileInputStream(file);
            } catch (FileNotFoundException exception) {
                throw new ImageResizerException(HttpStatus.NOT_FOUND, "The original image could not be found.");
            }
        }
    }

    private InputStream resizeAndSave(String reference, String type) throws ImageResizerException {
        try {
            File file = imageStorage.get(reference);
            BufferedImage resizedImage = getResizedImage(file, type);
            imageStorage.save(resizedImage, getResizedFileName(reference, type));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", outputStream);
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return inputStream;
        } catch (FileNotFoundException exception) {
            throw new ImageResizerException(HttpStatus.NOT_FOUND, "The original image could not be found.");
        } catch (IOException exception) {
            throw new ImageResizerException(HttpStatus.INTERNAL_SERVER_ERROR, "The resized image could not be saved.");
        }
    }

    private BufferedImage getResizedImage(File file, String type) throws ImageResizerException {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            BufferedImage resizedImage = imageResizer.resize(bufferedImage, type);
            return resizedImage;
        } catch (IOException exception) {
            throw new ImageResizerException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not read the original image.");
        }
    }

    private String getResizedFileName(String reference, String type) {
        return type + "_" + reference;
    }

}