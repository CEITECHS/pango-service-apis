package com.ceitechs.service.apis.converters.request;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.CorrespondenceType;
import com.ceitechs.domain.service.domain.EnquiryCorrespondence;
import com.ceitechs.service.apis.rest.resources.CorrespondenceResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class CorrespondenceResourceToEnquiryCorrespondence
        implements Converter<CorrespondenceResource, EnquiryCorrespondence> {

    @Override
    public EnquiryCorrespondence convert(CorrespondenceResource source) {
        EnquiryCorrespondence enquiryCorrespondence = new EnquiryCorrespondence();
        enquiryCorrespondence.setCorrespondenceReferenceId(source.getCorrespondenceReferenceId());
        enquiryCorrespondence.setCorrespondenceDate(source.getCorrespondenceDate());
        enquiryCorrespondence.setCorrespondenceType(CorrespondenceType.valueOf(source.getCorrespondenceType()));
        enquiryCorrespondence.setMessage(source.getMessage());
        enquiryCorrespondence.setOwner(source.isOwner());
        // Missing properties
        enquiryCorrespondence.setAttachment(null);
        return enquiryCorrespondence;
    }
}
