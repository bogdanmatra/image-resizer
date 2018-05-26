package com.byk.services;

import java.io.File;
import java.io.FileNotFoundException;


public interface ImageStorage {

    File get(String name) throws FileNotFoundException;

    String save(File file);
}
