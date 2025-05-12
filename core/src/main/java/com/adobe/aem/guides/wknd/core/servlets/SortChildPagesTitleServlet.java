package com.adobe.aem.guides.wknd.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "= Sort Child Pages Servlet",
                "sling.servlet.methods=GET",
                "sling.servlet.paths=/bin/sortpagestitle"
        }
)
public class SortChildPagesTitleServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(SortChildPagesTitleServlet.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    private static final String SUBSERVICE_NAME = "sitemap-service-user";
    private static final String JCR_NAME = "sortByTitle";
    private static final String JCR_CONTENT = "jcr:content";
    private static final String JCR_CREATED = "jcr:created";

    /**
     * Get the path as request and sort it
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String pagePath = request.getParameter("pagePath");

        if (pagePath == null || pagePath.isEmpty()) {
            response.setStatus(400);
            log.info("Path is empty");
            response.getWriter().write("Missing pagePath parameter");
            return;
        }

        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, SUBSERVICE_NAME);

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {
            PageManager pageManager = resolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage(pagePath);
            log.info("Sorting page: {}", page.getPath());

            if (page != null) {
                sortChildrenByTitle(page);
                response.setStatus(200);
                response.getWriter().write("Child pages sorted by title for: " + pagePath);
            } else {
                response.setStatus(404);
                response.getWriter().write("Page not found: " + pagePath);
            }

        }
        catch(NullPointerException ignored)
        {
            log.error("null pointer exception found in get");
        }
        catch (Exception e) {
            log.error("Error sorting pages: ", e);
            response.setStatus(500);
            response.getWriter().write("Server error: " + e.getMessage());
        }
    }


    /**
     * sort children using comparator function
     * @param parentPage
     */
    private void sortChildrenByTitle(Page parentPage) {
        List<Page> children = getChildren(parentPage);
        reorderChildren(parentPage, children.stream()
                .sorted(Comparator.comparing(Page::getTitle, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                .collect(Collectors.toList()));
    }

    /**
     * list children of parent page
     * @param parentPage
     * @return
     */
    private List<Page> getChildren(Page parentPage) {
        List<Page> children = new ArrayList<>();
        parentPage.listChildren().forEachRemaining(children::add);
        return children;
    }

    /**
     * Reorder children on jcr level using Node class and rearranging them.
     * @param parentPage
     * @param sortedPages
     */
    private void reorderChildren(Page parentPage, List<Page> sortedPages) {
        try {
            Node parentNode = parentPage.adaptTo(Node.class);
            if (parentNode == null) return;

            for (Page page : sortedPages) {
                Node childNode = page.adaptTo(Node.class);
                if (childNode != null) {
                    parentNode.orderBefore(childNode.getName(), null);
                }
            }

            parentNode.getSession().save();
            log.info("Reordering complete for {}", parentPage.getPath());
        }
        catch(NullPointerException ignored)
        {
            log.error("null pointer exception found");
        } catch(Exception e) {
            log.error("Error while reordering: ", e);
        }
    }
}
