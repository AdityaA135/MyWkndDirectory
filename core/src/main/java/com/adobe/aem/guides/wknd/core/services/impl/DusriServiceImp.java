package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.Test;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(service= DusriServiceImp.class, immediate = true)
public class DusriServiceImp {
    @Reference
    private Test test; // dusri is dependent on Test

    @Activate
    @Modified
    protected void bolo()
    {
        System.out.println(test.getNumbers());
    }
}
