package com.hellofresh.challenge.factory;

import com.hellofresh.challenge.entity.RestRequest;
import com.hellofresh.challenge.entity.RestResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

@Slf4j
public class RestResponseFactory {
    public RestResponse processRestRequest(RestRequest restRequest) {
        HttpURLConnection conn = null;
        String responseMessage = null;
        Integer responseCode = null;
        try {
            log.info("Sending request = {}", restRequest);
            conn = ((HttpURLConnection) restRequest.getUrl().openConnection());
            conn.setRequestMethod(restRequest.getMethod());
            conn.setRequestProperty("Content-Type", "application/json");
            if (restRequest.getMethod().equals("POST")) {
                conn.setDoOutput(true);
                String input = restRequest.getBody();
                OutputStream os = conn.getOutputStream();
                os.write(input.getBytes());
                os.flush();
            }
            responseCode = conn.getResponseCode();
            log.info("Response code is: "+responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            StringBuilder stringBuilder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                stringBuilder.append(output);
            };
            responseMessage = stringBuilder.toString();
            log.info("Response message is = {}", responseMessage);
        } catch (IOException e) {

        } finally {
            RestResponse restResponse = new RestResponse(responseCode, responseMessage);
            log.info("Received response = {}", restResponse);
            return restResponse;
        }
    }
}
