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
//        StringBuffer user_address=new StringBuffer();
//        user_address.append("aaa"+"bbbb");
//
//        System.out.println(user_address.toString());
//    }

}
