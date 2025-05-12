package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterBottomCompo {
    private static final Logger log = LoggerFactory.getLogger(FooterBottomCompo.class);

    @ChildResource(name="fieldFooter1")
    private List<FooterBottomCompoLeft> myCards1;

    @ChildResource(name="fieldFooter2")
    private List<FooterBottomCompoRight> myCards2;

    public List<FooterBottomCompoLeft> getMyCards1() {
        log.info("This is model card1: {}", myCards1 != null ? myCards1.toString() : "cards 1 are null");
        return myCards1;
    }

    public List<FooterBottomCompoRight> getMyCards2() {
        log.info("This is model card2: {}", myCards2 != null ? myCards2.toString() : "cards 2 are null");
        return myCards2;
    }
}
