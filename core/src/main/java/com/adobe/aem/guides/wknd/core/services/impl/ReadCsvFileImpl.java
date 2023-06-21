package com.adobe.aem.guides.wknd.core.services.impl;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

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

@Component(service = ReadCsvFile.class, immediate = true)
public class ReadCsvFileImpl implements ReadCsvFile {
    String title = "";
    private static final Logger LOG = LoggerFactory.getLogger(ReadCsvFileImpl.class);
    @Reference
    ResourceResolverFactory resolverFactory;
    HashMap<String, ArrayList<String>> csvHashMap = new HashMap<>();

    @Override
    public HashMap<String, ArrayList<String>> readData(String path) {

        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resolverFactory);
            Resource resource = resourceResolver.getResource(path);
            Asset asset = resource.adaptTo(Asset.class);

            BufferedReader br = new BufferedReader(new InputStreamReader(asset.getOriginal().getStream()));
            String line = "";
            int i = 0;
            ArrayList<String> keys = new ArrayList<>();
            ArrayList<ArrayList<String>> values = new ArrayList<>();
            while ((line = br.readLine()) != null) {

                String[] rowValues = line.split(",");
                if (i == 0) {
                    for (String val : rowValues) {
                        keys.add(val);
                    }
                } else {
                    if (i == 1) {
                        for (String val : rowValues) {
                            ArrayList<String> list = new ArrayList<>();
                            list.add(val);
                            values.add(list);
                        }
                    } else {
                        int listIndex = 0;
                        for (String val : rowValues) {
                            ArrayList<String> list = values.get(listIndex);
                            list.add(val);
                            listIndex++;
                        }
                    }

                }
                i++;

            }
            for (int count = 0; count < keys.size(); count++) {
                csvHashMap.put(keys.get(count), values.get(count));
            }
        } catch (Exception e) {
            LOG.info("error");
        }
        return csvHashMap;
    }

}
