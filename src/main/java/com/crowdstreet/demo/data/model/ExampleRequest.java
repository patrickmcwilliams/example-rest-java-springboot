package com.crowdstreet.demo.data.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ExampleRequest extends GetsJSON {
    
    @Getter @Setter
    private Request body;
    @Getter @Setter
    private String callback;
    
}
