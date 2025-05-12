package com.adobe.aem.guides.wknd.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyExperienceFragment {

    private static final Logger log = LoggerFactory.getLogger(MyExperienceFragment.class);

    @Self
    private SlingHttpServletRequest request;

    @Inject
    private PageManager pageManager;

    private String customLocalizedFragmentVariationPath;

    @Activate
    @Modified
    @PostConstruct
    protected void init() {
        Page currentPage = null;
        currentPage = request.adaptTo(Page.class);
        if (currentPage == null && pageManager != null) {
            Resource resource = request.getResource();
            currentPage = pageManager.getContainingPage(resource);
        }

        if (currentPage != null && currentPage.getPath().startsWith("/content")) {
            String pagePath = currentPage.getPath();
            log.info("Resolved actual content page path: {}", pagePath);

            String[] parts = pagePath.split("/");

            if (parts.length >= 5) {
                String projectName = parts[2];
                String country = parts[3];
                String language = parts[4];

                this.customLocalizedFragmentVariationPath = String.format(
                        "/content/experience-fragments/%s/%s/%s/site/us-en/master/jcr:content",
                        projectName, country, language, country, language
                );

                log.info("Mapped XF path: {}", customLocalizedFragmentVariationPath);
            } else {
                log.warn("Unexpected structure for content page path: {}", pagePath);
            }
        } else {
            log.warn("Could not resolve a valid /content page. Path may point to a template structure.");
        }
    }

    public String getCustomLocalizedFragmentVariationPath() {
        return customLocalizedFragmentVariationPath;
    }
}
