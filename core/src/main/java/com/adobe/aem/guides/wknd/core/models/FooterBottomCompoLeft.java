package com.adobe.aem.guides.wknd.core.models;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

public class FooterBottomCompoLeft {
    @ValueMapValue
    private String leftBottomLink;

    @ValueMapValue
    private String leftBottomText;

    public String getLeftBottomLink() {
        return leftBottomLink;
    }

    public String getLeftBottomText() {
        return leftBottomText;
    }
}
