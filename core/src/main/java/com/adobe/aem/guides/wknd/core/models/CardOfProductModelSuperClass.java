package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardOfProductModelSuperClass {

    private static final Logger log = LoggerFactory.getLogger(CardOfProductModelSuperClass.class);

    @ChildResource(name="fieldProduct")
    private List<CardOfProductModel> cards;

    public List<CardOfProductModel> getCards() {
        log.info("This is model card: {}", cards != null ? cards.toString() : "cards are null");
        return cards;
    }
}
