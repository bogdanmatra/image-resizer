package com.byk.services;

import java.awt.image.BufferedImage;
import java.io.IOException;


public interface ImageResizer {

    BufferedImage resize(BufferedImage file, String type) throws IOException;

}
