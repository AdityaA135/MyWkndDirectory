package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FirstCardFoodieModel {

    private static final Logger log = LoggerFactory.getLogger(FirstCardFoodieModel.class);

    @ChildResource(name="fieldProductFoodie")
    private List<FirstCardFoodieModelMultiL1> myCards;

    public List<FirstCardFoodieModelMultiL1> getMyCards() {
        log.info("This is model card: {}", myCards != null ? myCards.toString() : "cards are null");
        return myCards;
    }
}
