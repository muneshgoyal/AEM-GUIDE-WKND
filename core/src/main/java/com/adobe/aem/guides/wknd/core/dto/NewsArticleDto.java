package com.adobe.aem.guides.wknd.core.dto;

import java.util.ArrayList;

public class NewsArticleDto {
    public String status;
    public int totalResults;
    public ArrayList<Results> results;
    public String nextPage;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
    public ArrayList<Results> getResults() {
        return results;
    }
    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }
    public String getNextPage() {
        return nextPage;
    }
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    
}
