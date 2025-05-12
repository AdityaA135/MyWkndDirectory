package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.adobe.aem.guides.wknd.core.services.DiscountCardService;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Reference;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FirstCardFoodieModelMultiL1 {

    @ValueMapValue
    private String foodTitle;

    @ValueMapValue
    private double foodTime;

    @ValueMapValue
    private String foodImage;

    @ValueMapValue
    private String foodTag;

    @ValueMapValue
    private String category;

    @ValueMapValue
    private String foodLevel;

    public double getFoodTime() {
        return foodTime;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public String getFoodTag() {
        return foodTag;
    }

    public String getFoodLevel() {
        return foodLevel;
    }

    public String getCategory() {
        return category;
    }

}
