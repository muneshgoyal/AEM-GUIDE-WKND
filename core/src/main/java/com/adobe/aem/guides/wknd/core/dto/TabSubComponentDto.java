package com.adobe.aem.guides.wknd.core.dto;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class,
    Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TabSubComponentDto {
    

    @ValueMapValue
    String name;

    @ValueMapValue
    String ctaText;

    @ValueMapValue
    String sufixText;

    @ChildResource
     List<TabSubComponentAmountDto> amount;


    public String getName() {
        return name;
    }

    public String getCtaText() {
        return ctaText;
    }

    public String getSufixText() {
        return sufixText;
    }

    public List<TabSubComponentAmountDto> getAmount() {
        return amount;
    }

   
    
}
