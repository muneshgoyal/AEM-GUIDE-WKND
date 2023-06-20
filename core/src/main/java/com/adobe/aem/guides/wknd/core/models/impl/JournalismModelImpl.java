package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.dto.TabSubComponentDto;
import com.adobe.aem.guides.wknd.core.models.JournalismModel;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { JournalismModel.class }, resourceType = {

    JournalismModelImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class JournalismModelImpl implements JournalismModel{

    protected static final String RESOURCE_TYPE = "wknd/components/journalismcomponent";
    
    @ValueMapValue
    private String textCopy;

    // @ChildResource
    //  List<JournalismDto> planType;


    // @Override
    // public List<JournalismDto> getPlanType() {
        
    //     return planType;
    // }


    @Override
    public String getTextCopy() {
       return textCopy;
    }


    @Override
    public boolean isEmpty() {
        return StringUtils.isBlank(textCopy);
    }

     

}
