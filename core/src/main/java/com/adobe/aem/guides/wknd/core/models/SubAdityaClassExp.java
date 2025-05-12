package com.adobe.aem.guides.wknd.core.models;

import jdk.internal.loader.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SubAdityaClassExp {
    @ValueMapValue
    private String experience;

    public String getExperience() {
        return experience;
    }
}