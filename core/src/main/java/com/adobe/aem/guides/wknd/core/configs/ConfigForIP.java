package com.adobe.aem.guides.wknd.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="IP Service Config",description = "Config for API ")
public @interface ConfigForIP {
    @AttributeDefinition(name = "API URL", description = "URL of api")
    public String apiUrl();
}

