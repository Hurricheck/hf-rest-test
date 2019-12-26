package com.hellofresh.challenge.util.builder;

import com.hellofresh.challenge.entity.RestRequest;
import com.hellofresh.challenge.util.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;
import java.util.Set;

@Slf4j
@Setter
public class RestRequestBuilder {

    private String baseUrl;
    private String apiMethod;
    private String restMethod;
    private String body;
    private Properties properties;

    public RestRequestBuilder(String restMethod) {
        this.restMethod = restMethod;
        this.properties = new Properties();
    }

    public RestRequestBuilder(String baseUrl, String apiMethod, Properties properties) {
        this.baseUrl = baseUrl;
        this.apiMethod = apiMethod;
        this.properties = properties;
    }

    public RestRequestBuilder(String baseUrl, String apiMethod) {
        this.baseUrl = baseUrl;
        this.apiMethod = apiMethod;
        this.properties = new Properties();
    }

    public RestRequestBuilder() {
        this.properties = new Properties();
    }

    public void addRestParam(String key, String value) {
        properties.setProperty(key, value);
    }

    public RestRequest build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(baseUrl);
        stringBuilder.append(apiMethod);
        Set<String> paramsList = properties.stringPropertyNames();
        int isFirstParam = 0;
        for (String key: paramsList) {
            String specialChar;
            if (isFirstParam == 0) {
                specialChar = "?";
            } else {
                specialChar = "&";
            }
            String value = properties.getProperty(key);
            stringBuilder.append(specialChar)
                    .append(key)
                    .append("=")
                    .append(value);
            isFirstParam = 1;
        }
        String apiURL = stringBuilder.toString();
        if (restMethod.equals(Constants.GET_METHOD)) {
            RestRequest getRequest = new RestRequest(apiURL, restMethod);
            log.info("New GET Request created = {}", getRequest.toString());
            return getRequest;
        } else if (restMethod.equals(Constants.POST_METHOD)) {
            RestRequest postRequest = new RestRequest(apiURL, restMethod, body);
            log.info("New POST Request created = {}", postRequest.toString());
            return postRequest;
        }
        return null;
    }

}
