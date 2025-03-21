package com.adobe.aem.guides.wknd.core.models;

import com.adobe.aem.guides.wknd.core.services.Test;
import com.adobe.aem.guides.wknd.core.services.impl.TestImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.Resource;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestModelService {
    @OSGiService
    private TestImpl testImpl;

    protected List<String> getMyNumbers()
    {
        return testImpl.getNumbers();
    }
}
