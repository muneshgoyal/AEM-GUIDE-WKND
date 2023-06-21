package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.models.ReadCsv;
import com.adobe.aem.guides.wknd.core.services.ReadCsvFile;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ReadCsv.class, resourceType = ReadCsvImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReadCsvImpl implements ReadCsv {
  private static final Logger LOG = LoggerFactory.getLogger(ReadCsvImpl.class);
  protected static final String RESOURCE_TYPE = "wknd/components/ReadCsvComponent";

  @OSGiService
  ReadCsvFile readCsvFile;

  @ValueMapValue
  private String path;

  HashMap<String, ArrayList<String>> data;

  @PostConstruct
  public void init() {
    if (path != null && !path.isEmpty()) {
      data = readCsvFile.readData(path);
    }
  }

 

  @Override
  public HashMap<String, ArrayList<String>> getData() {
    return data;
  }
}
