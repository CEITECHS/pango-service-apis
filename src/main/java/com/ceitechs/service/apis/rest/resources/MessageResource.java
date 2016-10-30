package com.ceitechs.service.apis.rest.resources;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe on 10/30/16.
 */

@Getter
@Setter
public class MessageResource {
    private String message;

    public MessageResource(String txt) {
        message = txt;
    }
}
