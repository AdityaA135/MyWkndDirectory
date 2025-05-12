package com.adobe.aem.guides.wknd.core.servlets;
import com.adobe.aem.guides.wknd.core.services.IPAddressService;
import com.adobe.aem.guides.wknd.core.services.Test;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.apache.sling.api.servlets.ServletResolverConstants.*;

@Component(service= Servlet.class, property={
        SLING_SERVLET_RESOURCE_TYPES + "=wknd/components/ServletComponentTesting",
        SLING_SERVLET_EXTENSIONS + "=txt",
        SLING_SERVLET_METHODS + "=GET"
})
public class ModernServlet extends SlingSafeMethodsServlet {

    @Reference
    private transient IPAddressService ipAddressService;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String query=request.getParameter("query");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.print("Ip address from OSGI object using reference: " + ipAddressService.getIP());
        out.print("<h1>hello aditya, this is servlet speaking, coming from get from modern servlet</h1>");
        out.print("<br>");
        out.print(query);
        out.flush();
        out.close();
    }
}
