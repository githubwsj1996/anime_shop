package com.sj.until;

import com.sj.entity.AnimeInfo;
import com.sj.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
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
