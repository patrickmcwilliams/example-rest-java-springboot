package com.crowdstreet.demo.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Request extends GetsJSON {

    @Getter
    @Setter
    private String body;

}
