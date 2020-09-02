package com.sj.Until;

import java.util.Random;
import java.util.UUID;

public class KeyUtil {
    public static  synchronized String createId(){
        Random random=new Random();
        Integer id=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(id);
    }

    public static  synchronized String createUUId(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString();
    }
    
}
