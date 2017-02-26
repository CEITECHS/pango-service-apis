package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.service.apis.rest.resources.validators.StringEnumValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author iddymagohe on 12/18/16.
 */

@Getter
@Setter
@ToString
public class AttachmentResource {

    // parent-id to associate the attachment
    @Setter
    @NotEmpty(message = "Attachment parent referenceId  can not be null or empty")
    private String attachmentParentReferenceId;

    @StringEnumValidator(enumClass = attachmentCategoryType.class, message = "Missing or Unsupported attachment category")
    private String attachmentCategory;

    private String attachmentDescription;

    private Boolean isThumbnail;

    @JsonIgnore
    private MultipartFile attachment;

    public enum attachmentCategoryType {
        PROPERTY,
        PROFILE_PICTURE,
        CORRESPONDENCE,
        OTHER
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


}
