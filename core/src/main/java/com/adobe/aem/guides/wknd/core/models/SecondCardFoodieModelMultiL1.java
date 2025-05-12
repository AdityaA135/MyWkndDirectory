package com.adobe.aem.guides.wknd.core.models;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.adobe.aem.guides.wknd.core.services.DiscountCardService;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SecondCardFoodieModelMultiL1 {

    @ValueMapValue
    private String foodBlogTitle;

    @ValueMapValue
    private String foodBlogDate;

    @ValueMapValue
    private String foodBlogImage;

    @ValueMapValue
    private String foodBlogDesc;

    @ValueMapValue
    private String foodExplore;

    public String getFoodBlogTitle() {
        return foodBlogTitle;
    }

    public String getFoodBlogDate() {
        if (foodBlogDate == null) {
            return "";
        }

        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            Date date = parser.parse(foodBlogDate);

            int day = Integer.parseInt(new SimpleDateFormat("d").format(date));
            String suffix = getDaySuffix(day);
            String monthYear = new SimpleDateFormat("MMMM yyyy").format(date);

            return day + suffix + " " + monthYear;

        } catch (ParseException e) {
            return foodBlogDate;
        }
    }

    private String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public String getFoodBlogImage() {
        return foodBlogImage;
    }

    public String getFoodBlogDesc() {
        return foodBlogDesc;
    }

    public String getFoodExplore() {
        return foodExplore;
    }
}
