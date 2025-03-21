package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.services.IPAddressService;
import com.adobe.aem.guides.wknd.core.services.Test;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_METHODS;
import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;

@Component(service= Servlet.class, property={
        SLING_SERVLET_PATHS + "=/bin/myOwnServlet",
        SLING_SERVLET_METHODS + "=GET",
        SLING_SERVLET_METHODS + "=POST",
        SLING_SERVLET_METHODS + "=DELETE",
})
public class myServlet extends SlingAllMethodsServlet {

    @Reference
    private transient IPAddressService ipAddressService;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String query=request.getParameter("query");
        PrintWriter out=response.getWriter();
        out.print("Ip address from OSGI object using reference: " + ipAddressService.getIP());
        response.setContentType("text/html");
        out.print("<h1>hello aditya, this is servlet speaking, coming from get</h1>");
        out.print("<br>");
        out.print(query);
        out.flush();
        out.close();
    }

    @Override
    public void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        String query=request.getParameter("query");
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        out.print("<h2>hello aditya, this is servlet speaking, coming from post");
        out.print("<br>");
        out.print(query);
        Resource resource=request.getResource();
        out.print("The resource path is: "+ resource.getPath());
        out.print("The valueMap of resource is "+resource.getValueMap());
        out.flush();
        out.close();
    }

    @Override
    public void doDelete(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
        String query=request.getParameter("query");
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        out.print("<h2>hello aditya, this is servlet speaking, coming from delete");
    }
}
