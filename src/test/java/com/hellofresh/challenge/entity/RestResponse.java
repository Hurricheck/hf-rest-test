package com.hellofresh.challenge.entity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RestResponse{
    private final int code;
    private final String body;

    public RestResponse(int code, String body) {
        this.code = code;
        this.body = body;
    }
}
