package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Model 3 to get the third of multi field
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiLevel3Model {

    @ValueMapValue(name = "title3")
    private String title;

    @ValueMapValue(name = "link3")
    private String link;

    /**
     * Getter to get title from HTL
     * @return String
     */
    public String getTitle() { return title; }

    /**
     * Getter to get link from HTL
     * @return String
     */
    public String getLink() { return link; }
}
