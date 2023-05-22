package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.services.ReadCsvFile;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "wknd/components/page", methods = HttpConstants.METHOD_GET, selectors = "csv", extensions = "json")
@ServiceDescription("Simple Demo Servlet")
public class ReadCsvServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ReadCsvServlet.class);
    private static final long serialVersionUID = 1L;

    @Reference
    ReadCsvFile readCsvFile;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Inside doGet");
        resp.setContentType("application/json");
        String path = req.getParameter("path");
        
        HashMap<String, ArrayList<String>> data = readCsvFile.readData(path);
        
        for (String key : data.keySet()) {
            resp.getWriter().write("key: " + key + " val" + data.get(key) + "\n");
            
        }

    }
}