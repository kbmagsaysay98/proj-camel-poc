package com.example.model;


import com.example.request.SamplePostRequestType;
import com.example.request.SamplePostResponseType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class SamplePostBean {

    public SamplePostResponseType response(ReferenceType input) throws JSONException {

        final String uri = "http://localhost:8080/reference-types/";
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().add(new StringHttpMessageConverter());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<ReferenceType> entity = new HttpEntity<ReferenceType>(input, headers);

        URI result = template.postForLocation(uri,entity);


        return new SamplePostResponseType(result.toString());
    }
}

