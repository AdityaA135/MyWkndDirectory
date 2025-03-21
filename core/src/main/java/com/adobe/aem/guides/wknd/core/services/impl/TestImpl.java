package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.services.Test;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import java.util.ArrayList;
import java.util.List;
//@Component(service= Test.class) //only builds the component but it starts only when the service is called -> statisfied
@Component(service= Test.class, immediate = true) // to make this component start working soon after building -> active
public class TestImpl implements Test{
    private final List<String> members= new ArrayList<>();

    @Activate
    protected void activate(){
        members.add("Adi");
        members.add("Tarun");
        System.out.println(members);
    }

    @Modified
    protected void modified(){
        members.add("Vibanshu");
        System.out.println(members);
    }

    @Deactivate
    protected void deactivate() {
        members.clear();
        System.out.println(members);
    }

    @Override
    public List<String> getNumbers()
    {
        return members;
    }
}
