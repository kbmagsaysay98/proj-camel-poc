package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeRequest {

    @JsonIgnore
    private String typeOverride;

    private String type;

}