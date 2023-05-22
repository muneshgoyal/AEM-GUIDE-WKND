package com.adobe.aem.guides.wknd.core.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReadCsvFile {

    HashMap<String, ArrayList<String>> readData(String path);

}
