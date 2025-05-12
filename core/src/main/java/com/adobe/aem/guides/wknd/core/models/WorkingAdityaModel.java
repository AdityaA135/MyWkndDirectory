package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WorkingAdityaModel {
    @ValueMapValue
    private String image;

    @ValueMapValue
    private String fullname;

    @ValueMapValue
    private String smallName;

    @ValueMapValue
    private int views;

    @Inject
    private List<SubAdityaClassExp> fieldExp;

    @Inject
    private List<SubAdityaClass> fieldClass;

    public String getImage() {
        return image;
    }

    public String getFullname() {
        return fullname;
    }

    public String getSmallName() {
        return smallName;
    }

    public int getViews() {
        return views;
    }

    public List<SubAdityaClass> getFieldClass() {
        System.out.println("hello"+fieldClass);
        return fieldClass;
    }

    public List<SubAdityaClassExp> getFieldExp() {
        System.out.println("helloe"+fieldExp);
        return fieldExp;
    }
}