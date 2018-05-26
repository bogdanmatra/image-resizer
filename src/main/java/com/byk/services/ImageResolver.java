package com.byk.services;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;

public interface ImageResolver {

    InputStreamResource resolve(String type, String reference) throws IOException;
}
