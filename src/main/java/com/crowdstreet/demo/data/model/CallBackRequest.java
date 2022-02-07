package com.crowdstreet.demo.data.model;

import com.crowdstreet.demo.data.model.Status.StatusTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CallBackRequest extends GetsJSON{
    
    @Getter @Setter
    private StatusTypes status;
    @Getter @Setter
    private String detail;
}
