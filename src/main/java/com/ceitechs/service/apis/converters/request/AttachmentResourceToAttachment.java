package com.ceitechs.service.apis.converters.request;

import com.ceitechs.domain.service.domain.Attachment;
import com.ceitechs.service.apis.rest.resources.AttachmentResource;
import org.springframework.core.convert.converter.Converter;

/**
 * @author iddymagohe on 2/25/17.
 */
public class AttachmentResourceToAttachment implements Converter<AttachmentResource, Attachment> {
    @Override
    public Attachment convert(AttachmentResource attachmentResource) {
        Attachment attachment = new Attachment();
        attachment.setParentReferenceId(attachmentResource.getAttachmentParentReferenceId());
        attachment.setCategory(attachmentResource.getAttachmentCategory());
        attachment.setDescription(attachmentResource.getAttachmentDescription());
        attachment.setThumbnail(attachmentResource.getIsThumbnail()!=null ? attachmentResource.getIsThumbnail() : false );
        attachment.setAttachment(attachmentResource.getAttachment());
        return attachment;
    }
}
