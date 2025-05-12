package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyBlogListing {

    @ChildResource(name = "field")
    private List<Resource> pageLocation;

    @Inject()
    @Optional
    private PageManager pageManager;

    @SlingObject
    private ResourceResolver resourceResolver;


    public List<BlogDetails> getBlogs() {
        List<BlogDetails> myblogs = new ArrayList<>();

        if (pageLocation == null || pageManager == null) {
            return Collections.emptyList();
        }

        for (Resource item : pageLocation) {
            String path = item.getValueMap().get("pathdetail", String.class);
            if (path != null) {
                Resource blogResource = resourceResolver.getResource(path + "/jcr:content");
                if (blogResource != null) {
                    BlogDetails page = blogResource.adaptTo(BlogDetails.class);
                    if (page != null) {
                        String imagePath = getImagePath(blogResource);
                        page.setImagePath(imagePath);
                        myblogs.add(page);
                    }
                }
            }
        }
        return myblogs;
    }

    private String getImagePath(Resource blogResource) {
        Resource imageResource = blogResource.getChild("image");
        assert imageResource != null;
        Resource childimageResource=imageResource.getChild("file");
        if(childimageResource!=null)
        {
            return imageResource.getPath()+"/file";
        }
        ValueMap properties = imageResource.getValueMap();
        return properties.get("fileReference", String.class);
    }
}