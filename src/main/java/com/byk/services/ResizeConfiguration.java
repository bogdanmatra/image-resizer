package com.byk.services;

import com.google.gson.JsonObject;

public interface ResizeConfiguration {

    /**
     * Retrieves a configuration from {resize_configuration.json}.
     * @param type Configuration type.
     * @return
     */
    JsonObject getConfiguration(String type);
}
