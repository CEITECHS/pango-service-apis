package com.ceitechs.service.apis.rest.resources.models;

/**
 * Created by iddymagohe on 7/2/16.
 */
public enum PerPeriod {
    MONTHLY("mo"),
    YEARLY("yr");
    private String value;

    PerPeriod(String value) {
        this.value = value;
    }
}
