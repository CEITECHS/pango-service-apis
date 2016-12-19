package com.ceitechs.service.apis.rest.resources;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

/**
 * @author iddymagohe on 12/18/16.
 */

@Getter
@ToString
public class AttachmentResource {

    // parent-id to associate the attachment
    @Setter
    private String referenceId;

    private String category;

    private String description;

    private Boolean thumbnail;

    private File attachment;

    public enum attachmentCategoryType {
        PROPERTY,
        PROFILE_PICTURE,
        CORRESPONDENCE
    }

    public enum requestFields {
        ATTACHMENT("attachment"),
        CATEGORY("attachmentCategory"),
        DESCRIPTION("description"),
        THUMBNAIL_INDICATOR("thumbnail");

        @Getter
        private String value;

        requestFields(String value) {
            this.value = value;
        }
    }


    public AttachmentResource(String category, String description, Boolean thumbnail, File attachment) {
        this.category = category;
        this.description = description;
        this.thumbnail = thumbnail;
        this.attachment = attachment;
    }
}
