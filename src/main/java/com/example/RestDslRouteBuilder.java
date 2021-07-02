package com.example;

import com.example.model.ReferenceType;
import com.example.request.SamplePostResponseType;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * This RouteBuilder defines our REST API using Camel's REST DSL.
 *
 * A RestConfiguration block first defines how the service will be instantiated.
 * The rest() DSL block then defines each of the RESTful service operations.
 */
@Component
public class RestDslRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // This section is required - it tells Camel how to configure the REST service
        restConfiguration()
                // Use the 'servlet' component.
                // This tells Camel to create and use a Servlet to 'host' the RESTful API.
                // Since we're using Spring Boot, the default servlet container is Tomcat.
                .component("servlet")

                // Allow Camel to try to marshal/unmarshal between Java objects and JSON
                .bindingMode(RestBindingMode.auto);


        // Now define the REST API (POST, GET, etc.)
        rest()
                .path("/api") // This makes the API available at http://host:port/$CONTEXT_ROOT/api

                .produces("application/json")


                // HTTP: GET /api
                .get("/{id}")
                .outType(String.class)
                .to("bean:sampleGetBean?method=response(${header.id})") // This will invoke the Spring bean 'getBean'

                .consumes("application/json")

                // HTTP: POST /api
                .post()
                .type(ReferenceType.class) // Setting the request type enables Camel to unmarshal the request to a Java object
                .outType(SamplePostResponseType.class) // Setting the response type enables Camel to marshal the response to JSON
                .to("bean:samplePostBean");

    }
}