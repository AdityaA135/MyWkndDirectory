package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;

/**
 * Model 2 to get the second level of multi field and store the third level
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiLevel2Model {

    @ValueMapValue(name = "title2")
    private String title;

    @ValueMapValue(name = "link2")
    private String link;

    @ChildResource(name = "field3")
    private List<MultiLevel3Model> multiLevel3List;

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

    /**
     * Getter to get the list of 2nd level of nested multi field
     * @return list of type MultiLevel2Model
     */
    public List<MultiLevel3Model> getMultiLevel3List() { return multiLevel3List; }
}
