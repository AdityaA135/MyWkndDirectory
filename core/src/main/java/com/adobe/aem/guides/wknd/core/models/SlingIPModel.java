package com.adobe.aem.guides.wknd.core.models;
import javax.annotation.PostConstruct;

import com.adobe.aem.guides.wknd.core.configs.ConfigForIP;
import com.adobe.aem.guides.wknd.core.services.IPAddressService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.metatype.annotations.Designate;

@Model(adaptables=Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Designate(ocd= ConfigForIP.class,factory=true)
public class SlingIPModel {
    @OSGiService
    private IPAddressService defualtService;

    @OSGiService(filter="(component.name=com.adobe.aem.guides.wknd.core.services.impl.ServiceOne)")
    private IPAddressService apiOne;

    @OSGiService(filter="(component.name=com.adobe.aem.guides.wknd.core.services.impl.ServiceTwo)")
    private IPAddressService apiTwo;

    private String defaultIP;
    private String firstAPI;
    private String secondAPI;

    @PostConstruct
    protected void init()
    {
        defaultIP=defualtService.getIP();
        firstAPI=apiOne.getIP();
        secondAPI=apiTwo.getIP();
    }

    public String getDefaultIP()
    {
        return defaultIP;
    }

    public String getFirstAPI()
    {
        return firstAPI;
    }

    public String getSecondAPI()
    {
        return secondAPI;
    }
}
