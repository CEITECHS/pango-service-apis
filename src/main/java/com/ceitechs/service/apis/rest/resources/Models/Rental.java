package com.ceitechs.service.apis.rest.resources.Models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by iddymagohe on 7/21/16.
 */
@Getter
@Setter
public class Rental {

    private Long id;
    private String reservationName;

    public Rental(String reservationName) {
        this.reservationName = reservationName;
        this.id = System.currentTimeMillis();
    }
}
