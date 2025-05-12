package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterBottomCompoRight {

    @ValueMapValue
    private String leftBottomImageLink;

    @ValueMapValue
    private String leftBottomImage;

    @ValueMapValue
    private String leftBottomImageAlt;

    public String getLeftBottomImageAlt() {
        return leftBottomImageAlt;
    }

    public String getLeftBottomImage() {
        return leftBottomImage;
    }

    public String getLeftBottomImageLink() {
        return leftBottomImageLink;
    }
}
