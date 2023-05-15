package com.adobe.aem.guides.wknd.core.services.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.services.ReadCsvFile;
import com.adobe.aem.guides.wknd.core.util.ResolverUtil;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;

@Component(service = ReadCsvFile.class, immediate = true)
public class ReadCsvFileImpl implements ReadCsvFile {
    // String data = "Read Success Fully";
    private static final Logger LOG = LoggerFactory.getLogger(ReadCsvFileImpl.class);
    @Reference
    ResourceResolverFactory resolverFactory;

    List<String> data = new ArrayList<>();

    @Override
    public List<String> readData() {

        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resolverFactory);
            Resource resource = resourceResolver.getResource("/content/dam/wknd/AssetMetaData.csv");
            Asset asset = resource.adaptTo(Asset.class);

            BufferedReader br = new BufferedReader(new InputStreamReader(asset.getOriginal().getStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                for (String index : row) {
                    LOG.info("DATA" + index);
                    data.add(index);

                }
            }

        } catch (Exception e) {
            LOG.info("error");
        }
        return data;
    }

}
