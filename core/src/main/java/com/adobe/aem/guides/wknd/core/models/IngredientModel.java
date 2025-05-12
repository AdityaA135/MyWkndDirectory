package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.resource.ValueMap;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.List;
import java.util.stream.Collectors;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class IngredientModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String ctaText;

    @ValueMapValue
    private String ctaLink;

    @ChildResource(name = "fieldFood")
    private List<IngredientItem> ingredients;

    public String getTitle() { return title; }

    public String getCtaText() { return ctaText; }

    public String getCtaLink() { return ctaLink; }

    public List<IngredientItem> getIngredients() {
        return ingredients;
    }
}
