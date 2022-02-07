package com.crowdstreet.demo.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Status extends GetsJSON{
    
    public static enum StatusTypes{
        INIT, STARTED;
    }

    @Id
    @GeneratedValue
    @Getter
    private long id;
    @Getter @Setter
    private StatusTypes status;

    public Status (StatusTypes status) {
        this.status = status;
    }

}
