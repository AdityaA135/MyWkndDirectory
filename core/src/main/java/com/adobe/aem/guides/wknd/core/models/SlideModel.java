package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.models.ButtonModel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SlideModel {

    @ValueMapValue
    private String type;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String subtitle;

    @ValueMapValue
    private String ctaText;

    @ValueMapValue
    private String ctaLink;

    @ValueMapValue
    private String imageUrl;

    @ValueMapValue
    private String videoUrl;

    @ChildResource
    private List<ButtonModel> buttons;

    public String getType() {
        return type;
    }

    public List<ButtonModel> getButtons() {
        return buttons;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCtaLink() {
        return ctaLink;
    }

    public String getCtaText() {
        return ctaText;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }
}
