package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import com.adobe.aem.guides.wknd.core.dto.TabSubComponentDto;
import com.adobe.aem.guides.wknd.core.models.TabSubComponentModel;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { TabSubComponentModel.class }, resourceType = {

    TabSubComponentImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TabSubComponentImpl implements TabSubComponentModel {
    
    protected static final String RESOURCE_TYPE = "wknd/components/tabsubcomponent";

     @ChildResource
     List<TabSubComponentDto> planType;


    @Override
    public List<TabSubComponentDto> getPlanType() {
        
        return planType;
    }
}
