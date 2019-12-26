package com.hellofresh.challenge.entity;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Getter
@ToString
public class RestRequest {
    private URL url;
    private String method;
    private String body = null;

    public RestRequest(String url, String method) {
        setUrl(url);
        this.method = method;
    }

    public RestRequest(String url, String method, String body) {
        setUrl(url);
        this.method = method;
        this.body = body;
    }

    private void setUrl(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException m) {
            this.url = null;
            log.error("Invalid URL setting property to null");
        }
    }
}
