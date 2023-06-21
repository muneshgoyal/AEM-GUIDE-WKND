package com.adobe.aem.guides.wknd.core.dto;

public class NearbyDto {
    String diffIcon;
    String queryparam;
    String linktext;
    
    public String getQueryparam() {
        return queryparam;
    }
    public void setQueryparam(String queryparam) {
        this.queryparam = queryparam;
    }
    public String getLinktext() {
        return linktext;
    }
    public void setLinktext(String linktext) {
        this.linktext = linktext;
    }
    public String getDiffIcon() {
        return diffIcon;
    }
    public void setDiffIcon(String diffIcon) {
        this.diffIcon = diffIcon;
    }
    
}
