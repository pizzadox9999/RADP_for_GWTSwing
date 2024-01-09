package org.radp;

import java.util.HashMap;

public final class Util {
    
    public static HashMap<String, String> stringToHashMap(String data) {
        return stringToHashMap(data, "#,", "#:");
    }
    
    public static HashMap<String, String> stringToHashMap(String data, String delimiter, String pairDelimiter) {
        HashMap<String, String> myMap = new HashMap<String, String>();
        String[] pairs = data.split(delimiter);
        for (int i=0;i<pairs.length;i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(pairDelimiter);
            myMap.put(keyValue[0], keyValue[1]);
        }
        return myMap;
    }
}