package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselModel{

    @ChildResource
    private List<SlideModel> slides;

    public List<SlideModel> getSlides() {
        return slides;
    }
}
