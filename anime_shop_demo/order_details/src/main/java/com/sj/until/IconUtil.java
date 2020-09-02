package com.sj.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IconUtil {
    public static String createIcon(String icon){
        String[] icons=icon.split("&");
        return icons[0];
    }

    public static List<Map<String,String>> createListIcon(String icon){
        String[] icons=icon.split("&");
        Map<String,String> maps=null;
        List<Map<String,String>> mapList=new ArrayList<>();
        for (String s : icons) {
            maps=new HashMap<>();
            maps.put("url",s);
            mapList.add(maps);
        }
        return  mapList;
    }
}
