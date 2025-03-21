package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CoursesDetails {
    @ValueMapValue
    public String courseName;

    @ValueMapValue
    public int score;

    public String getCourseName() {
        return courseName;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        if(score<=33) return "F";
        else if(score >= 75) return "A";
        else return "P";
    }
}
