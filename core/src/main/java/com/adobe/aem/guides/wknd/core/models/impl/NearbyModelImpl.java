package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.dto.NearbyDto;
import com.adobe.aem.guides.wknd.core.models.NearbyModel;


@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},adapters = NearbyModel.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = NearbyModelImpl.RESOURCE_TYPE)
public class NearbyModelImpl implements NearbyModel {

    protected static final String RESOURCE_TYPE = "wknd/components/nearbycomponent";

    @SlingObject
    Resource componentResource;

    @ValueMapValue
    String title;
 
    @ValueMapValue
    String subtitle;

    @ValueMapValue
     String searchbuttontext;

     @ValueMapValue
      List<NearbyDto> quicksearchlinks;

    @ValueMapValue
      String bgLogo;

    @PostConstruct
    public void init()
    {
       

        quicksearchlinks =new ArrayList<>();

        Resource res = componentResource.getChild("quicksearchlinks");

        if(null != res && res.hasChildren()) {

            Iterator<Resource> cards = res.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                NearbyDto nearbyDto = new NearbyDto();

               nearbyDto.setDiffIcon(card.getValueMap().get("diffIcon", String.class));
               nearbyDto.setQueryparam(card.getValueMap().get("queryparam", String.class));
               nearbyDto.setLinktext(card.getValueMap().get("linktext", String.class));
               quicksearchlinks.add(nearbyDto);
    }
}
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public String getSearchButtonText() {
        return searchbuttontext;
    }

    @Override
    public List<NearbyDto> getQuickSearchLinks() {
        return quicksearchlinks;
    }

    @Override
    public String getBgImage() {
       return bgLogo;
    }

    @Override
    public boolean isEmpty() {
        return StringUtils.isBlank(title);
    }
    


    
     

}
