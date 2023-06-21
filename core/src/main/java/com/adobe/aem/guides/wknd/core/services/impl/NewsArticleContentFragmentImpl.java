package com.adobe.aem.guides.wknd.core.services.impl;

import java.util.Iterator;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.dto.NewsArticleDto;

import com.adobe.aem.guides.wknd.core.services.NewsArticleContentFragment;
import com.adobe.aem.guides.wknd.core.util.ResolverUtil;
import com.adobe.cq.dam.cfm.ContentElement;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.ContentFragmentException;
import com.adobe.cq.dam.cfm.ContentFragmentManager;
import com.adobe.cq.dam.cfm.FragmentTemplate;

@Component(service = NewsArticleContentFragment.class, immediate = true)
public class NewsArticleContentFragmentImpl implements NewsArticleContentFragment {
    final String successString = "Done";
    private static final Logger LOG = LoggerFactory.getLogger(NewsArticleContentFragmentImpl.class);

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    public String getData(NewsArticleDto newsData) {
        String resultContents = "Data is not saved";
        ResourceResolver resourceResolver;
        try {
          
            resourceResolver = ResolverUtil.newResolver(resolverFactory);
            Resource templateResc = resourceResolver.resolve("/conf/wknd/settings/dam/cfm/models/news-articles");
            Resource cfParentResc = resourceResolver.resolve("/content/dam/wknd/NewsData");

            if (templateResc != null && cfParentResc != null) {
                try {
                    FragmentTemplate fragmentTemplate = templateResc.adaptTo(FragmentTemplate.class);
                    for (int i = 0; i < newsData.getResults().size(); i++) {

                        ContentFragment contentFragment = fragmentTemplate.createFragment(cfParentResc,
                                "title", newsData.getResults().get(i).getTitle());
                        
                        resourceResolver.commit();
                        if (contentFragment != null) {
                            ContentElement title = contentFragment.getElement("title");
                            String a = newsData.getResults().get(i).getTitle();
                            title.setContent(a, "text/plain");

                            ContentElement description = contentFragment.getElement("description");
                            String b = newsData.getResults().get(i).getDescription();
                            description.setContent(b, "text/plain");

                        }
                    }

                } catch (ContentFragmentException e) {
                    LOG.error("ContentFragmentException={}", e.getMessage());
                }
            }
        } catch (Exception e) {
            return "failed";
        }

        return resultContents;

    }

}
