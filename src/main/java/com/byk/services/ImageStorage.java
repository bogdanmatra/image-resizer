package com.byk.services;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ImageStorage {

    InputStream get(String name) throws FileNotFoundException;

    String save(InputStream inputStream);
}
