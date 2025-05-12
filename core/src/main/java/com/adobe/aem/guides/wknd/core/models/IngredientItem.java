package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class IngredientItem {
    @ValueMapValue
    private String ingredientName;

    @ValueMapValue
    private Boolean active;

    public String getIngredientName() {
        return ingredientName;
    }

    public Boolean getActive() {
        return active;
    }
}
