package com.byk.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public interface ImageStorage {

    /**
     * Gets an image file from the disk.
     * @param name File name.
     * @return
     * @throws FileNotFoundException
     */
    File get(String name) throws FileNotFoundException;

    /**
     * Saves an image to the disk.
     * @param bufferedImage Image.
     * @param fileName The new file name.
     * @return
     */
    File save(BufferedImage bufferedImage, String fileName) throws IOException;
}
