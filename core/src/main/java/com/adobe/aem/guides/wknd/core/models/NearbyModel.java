package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.core.dto.NearbyDto;

public interface NearbyModel {
    String getTitle();
    String getSubtitle();
    String getSearchButtonText();
    List<NearbyDto> getQuickSearchLinks();
    String getBgImage();
    boolean isEmpty();
}
