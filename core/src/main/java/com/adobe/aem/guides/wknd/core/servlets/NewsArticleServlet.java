
package com.adobe.aem.guides.wknd.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;


import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.dto.NewsArticleDto;
import com.adobe.aem.guides.wknd.core.services.NewsArticleContentFragment;

import com.google.gson.Gson;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "wknd/components/page", methods = HttpConstants.METHOD_GET, selectors = "news", extensions = "json")
@ServiceDescription("Simple Demo Servlet")
public class NewsArticleServlet extends SlingAllMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(NewsArticleServlet.class);
    private static final long serialVersionUID = 1L;

    @Reference
    NewsArticleContentFragment newsArticleContentFragment;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Inside goget");
        resp.setContentType("application/json");
        try {

            String url = "https://newsdata.io/api/1/news?apikey=pub_2179368b521a6f85340cc257c08b9e3c3bc09&q=cryptocurrency";
            HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            HttpClient client = HttpClient.newBuilder().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson g = new Gson();
            NewsArticleDto newsData = g.fromJson(response.body(), NewsArticleDto.class);
            for (int i = 0; i < newsData.getResults().size(); i++) {
                resp.getWriter().write(newsData.getResults().get(i).getTitle() + "\n");
                resp.getWriter().write(newsData.getResults().get(i).getDescription() + "\n");
            }

           

            String resultContent = newsArticleContentFragment.getData(newsData);
            

            resp.getWriter().write(resultContent);
            

        } catch (Exception e) {
            LOG.info("exception in servlet  ", e.getMessage());
        }

    }
}
