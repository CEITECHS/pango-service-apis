package com.ceitechs.service.apis.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe on 10/30/16.
 */

@Getter
@Setter
@JsonPropertyOrder({"title", "message"})
public class MessageResource {
    @JsonProperty("detail")
    private String message;

    private String title;

    public MessageResource(String txt, String tag) {
        message = txt;
        title = tag;
    }
}
