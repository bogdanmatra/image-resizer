package com.byk.services;

import com.byk.exceptions.ImageResizerException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResizeConfigurationImpl implements ResizeConfiguration {

    private JsonObject jsonObject;

    private final String RESIZE_CONFIGURATION = "resize_configuration.json";

    private ResizeConfigurationImpl() throws ImageResizerException {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            String result = IOUtils.toString(classLoader.getResourceAsStream(RESIZE_CONFIGURATION));
            Gson gson = new Gson();
            jsonObject = gson.fromJson(result, JsonObject.class);
        } catch (IOException exception) {
            throw new ImageResizerException(HttpStatus.INTERNAL_SERVER_ERROR, "Configuration could not be loaded from: " + RESIZE_CONFIGURATION);
        }
    }

    @Override
    public JsonObject getConfiguration(String type) {
        return jsonObject.getAsJsonObject(type);
    }
}
