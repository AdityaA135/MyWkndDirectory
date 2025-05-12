package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.resource.Resource;

import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AdityaVideoModel {
    @ValueMapValue
    private String titleSegment;

    @ChildResource(name="field2")
    private List<AdityaSubVideoModel> listAdityaVideo;

    public String getTitleSegment() {
        return titleSegment;
    }

    public List<AdityaSubVideoModel> getListAdityaVideo() {
        return listAdityaVideo;
    }
}
