package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import java.util.List;

/**
 * Model 0 class to get the first level of nested multifield
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiLevel0Model {

    @ChildResource(name = "field1")
    private List<MultiLevel1Model> multiLevel1List;

    /**
     * Getting the first level list of multi field
     * Type-> MultiLevel1Model
     * @return list
     */
    public List<MultiLevel1Model> getMultiLevel1List() { return multiLevel1List; }

}
