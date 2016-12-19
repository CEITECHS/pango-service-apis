package com.ceitechs.service.apis.converters.request;

import com.ceitechs.service.apis.rest.resources.AttachmentResource;

import com.ceitechs.service.apis.rest.resources.AttachmentResource.requestFields;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.MultiValueMap;

import java.io.File;

/**
 * @author iddymagohe on 12/18/16.
 */
public class MultiValueMapToAttachmentResource implements Converter<MultiValueMap, AttachmentResource> {

    @Override
    public AttachmentResource convert(MultiValueMap params) {
        String category = (String) params.getFirst(requestFields.CATEGORY.getValue());
        String description = (String) params.getFirst(requestFields.DESCRIPTION.getValue());
        Boolean thumbnail = (Boolean) params.getFirst(requestFields.THUMBNAIL_INDICATOR.getValue());
        File attachment = (String) params.getFirst(requestFields.ATTACHMENT.getValue()) != null ?
              new File((String)params.getFirst(requestFields.ATTACHMENT.getValue())): null;

        return new AttachmentResource(category, description, thumbnail, attachment);
    }
}
