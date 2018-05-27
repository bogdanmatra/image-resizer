package com.byk.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResizeConfigurationImpl implements ResizeConfiguration {

    private JsonObject jsonObject;

    private final String RESIZE_CONFIGURATION = "resize_configuration.json";

    private ResizeConfigurationImpl() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream(RESIZE_CONFIGURATION));
        Gson gson = new Gson();
        jsonObject = gson.fromJson(result, JsonObject.class);
    }

    @Override
    public JsonObject getConfiguration(String type) {
        return jsonObject.getAsJsonObject(type);
    }
}
