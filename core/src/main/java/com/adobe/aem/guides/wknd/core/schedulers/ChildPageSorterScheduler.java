package com.adobe.aem.guides.wknd.core.schedulers;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import java.util.*;

import java.util.stream.Collectors;

@Component(
        service = Runnable.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "= Child Page Sorter Scheduler",
                "scheduler.expression=*/100 * * * * ?"  //(make 100 as 5 to make it work)
        },
        immediate = true
)

public class ChildPageSorterScheduler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ChildPageSorterScheduler.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    private static final String SUBSERVICE_NAME = "sitemap-service-user";
    private static final String CONTENT_PATH = "/content/wknd";
    private static final String ERROR_LOG = "Root path not found: /content/wknd";
    private static final String JCR_NAME = "sortByTitle";
    private static final String JCR_CONTENT = "jcr:content";
    private static final String JCR_CREATED = "jcr:created";

    /**
     * Run method override for calling all pages every t seconds, where t is cronn exp time.
     */
    @Override
    public void run() {
        log.info("ChildPageSorterScheduler triggered.");

        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, SUBSERVICE_NAME);

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {
            Resource root = resolver.getResource(CONTENT_PATH);
            if (root == null) {
                log.warn(ERROR_LOG);
                return;
            }

            PageManager pageManager = resolver.adaptTo(PageManager.class);
            traverseAndHandlePages(root, pageManager);

        } catch (Exception e) {
            log.error("Exception in scheduler: ", e);
        }
    }

    /**
     * Traverse every page from content to all, and check for "sortByTitle" if it is enabled.
     *
     * @param root        resource object, to traverse and see children
     * @param pageManager to get path of the page
     */
    private void traverseAndHandlePages(Resource root, PageManager pageManager) {
        for (Resource child : root.getChildren()) {
            Resource contentRes = child.getChild(JCR_CONTENT);
            if (contentRes != null) {
                boolean sortByTitle = contentRes.getValueMap().get(JCR_NAME, Boolean.FALSE);
                Page parentPage = pageManager.getPage(child.getPath());
                if (parentPage != null) {
                    if (sortByTitle) {
                        sortChildrenByTitle(parentPage);
                    } else {
                        resetChildrenByCreation(parentPage);
                    }
                }
            }

            traverseAndHandlePages(child, pageManager);
        }
    }

    /**
     * Sort children if "sortByTitle" is enabled, so order is done on jcr:title.
     *
     * @param parentPage page resource which gets children and sort them based on jcr:title.
     */
    private void sortChildrenByTitle(Page parentPage) {
        log.info("Sorting children of '{}' alphabetically", parentPage.getPath());
        List<Page> children = getChildren(parentPage);
        reorderChildren(parentPage, children.stream()
                .sorted(Comparator.comparing(Page::getTitle, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)))
                .collect(Collectors.toList()));
    }

    /**
     * Method to order the pages based on creation date, when the "sortByTitle" was unchecked.
     *
     * @param parentPage page resource which gets children and sort them based on jcr:created.
     */
    private void resetChildrenByCreation(Page parentPage) {
        log.info("Resetting children of '{}' by creation order", parentPage.getPath());
        List<Page> children = getChildren(parentPage);
        reorderChildren(parentPage, children.stream()
                .sorted((page1, page2) -> compareCreationTime(page1, page2))
                .collect(Collectors.toList()));
    }

    /**
     * Method to get children pages in an array list
     *
     * @param parentPage resource object to get page children
     * @return
     */
    private List<Page> getChildren(Page parentPage) {
        List<Page> children = new ArrayList<>();
        parentPage.listChildren().forEachRemaining(children::add);
        return children;
    }

    /**
     * Method comparator to compare 2 pages, and reorder them based on creation date
     *
     * @param page1 page 1 object
     * @param page2 page 2 object
     * @return
     */
    private int compareCreationTime(Page page1, Page page2) {
        try {
            Node node1 = page1.adaptTo(Node.class);
            Node node2 = page2.adaptTo(Node.class);

            if (node1 != null && node2 != null) {
                Property created1 = node1.hasProperty(JCR_CREATED) ? node1.getProperty(JCR_CREATED) : null;
                Property created2 = node2.hasProperty(JCR_CREATED) ? node2.getProperty(JCR_CREATED) : null;

                if (created1 != null && created2 != null) {
                    Calendar createdDate1 = created1.getDate();
                    Calendar createdDate2 = created2.getDate();
                    return createdDate1.compareTo(createdDate2);
                }
            }
        } catch (RepositoryException e) {
            log.error("Error comparing creation time of pages", e);
        }
        return 0;
    }

    /**
     * Method to reorder the children on jcr level to reflected on AEM touch UI.
     *
     * @param parentPage  Page object of the parent
     * @param sortedPages list of sorted children of parent page to display the new order
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
        } catch (javax.jcr.RepositoryException repoEx) {
            log.error("RepositoryException while reordering children for {}: {}", parentPage.getPath(), repoEx.getMessage(), repoEx);
        } catch (IllegalStateException illegalStateEx) {
            log.error("IllegalStateException during reordering for {}: {}", parentPage.getPath(), illegalStateEx.getMessage(), illegalStateEx);
        } catch (NullPointerException nullEx) {
            log.error("NullPointerException during reordering for {}: {}", parentPage.getPath(), nullEx.getMessage(), nullEx);
        } catch (Exception e) {
            log.error("Unexpected exception while reordering children for {}: {}", parentPage.getPath(), e.getMessage(), e);
        }
    }
}
