package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.adobe.aem.guides.wknd.core.services.DiscountCardService;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Reference;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardOfProductModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private double price;

    @ValueMapValue
    private String image;

    @OSGiService
    private transient DiscountCardService discountCardService;

    private double discount;
    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPrice() {
        if (discountCardService != null) {
            return discountCardService.getDiscountOnProduct(price);
        } else {
            return price;
        }
    }

    public double getDiscount() {
        if (discountCardService != null) {
            return discountCardService.getDiscountPercentage(price);
        } else {
            return price;
        }
    }

}
