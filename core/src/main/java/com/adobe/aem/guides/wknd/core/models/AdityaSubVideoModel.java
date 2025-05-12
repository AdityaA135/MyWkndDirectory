package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.resource.Resource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AdityaSubVideoModel {
    @ValueMapValue
    private String videoTitle;

    @ValueMapValue
    private String link;

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getLink() {
        return link;
    }
}