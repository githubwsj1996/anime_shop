package com.sj.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrUtil {
   public static List<Map<String,String>> createData(String str){
       String[] strs=str.split("&");
       Map<String,String> maps=null;
       List<Map<String,String>> tagList=new ArrayList<>();
       for (String s: strs) {
           maps=new HashMap<>();
           maps.put("name",s);
           tagList.add(maps);
       }
       return tagList;
    }

//    public static void main(String[] args) {
//        List<Map<String,String>> tagList=createTag("../../public/static/images/anime_img/rm/路飞/lufei01.jpg&../../public/static/images/anime_img/rm/路飞/lufei02.jpg&../../public/static/images/anime_img/rm/路飞/lufei03.jpg&../../public/static/images/anime_img/rm/路飞/lufei04.jpg&../../public/static/images/anime_img/rm/路飞/lufei05.jpg");
//        System.out.println(tagList.get(0));
//    }

}
