package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.List;

/**
 * Model 1 to get the first level of multi field and store the second level
 */
@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FoodieHeaderModelLevel1 {

    @ValueMapValue(name = "title1")
    private String title;

    @ValueMapValue(name = "link1")
    private String link;

    @ChildResource(name = "field2")
    private List<FoodieHeaderModelMultiL2> foodieHeaderL2List;

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
    public List<FoodieHeaderModelMultiL2> getFoodieHeaderL2List() { return foodieHeaderL2List; }
}
