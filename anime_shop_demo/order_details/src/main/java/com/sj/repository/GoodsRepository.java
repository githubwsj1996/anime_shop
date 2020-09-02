package com.sj.repository;

import com.sj.entity.AnimeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository {
    public AnimeInfo findAllById(Integer animeId);
    public int updateByGoods(Integer animeId,Integer quantity);
    public void updateMoreStock(List animeInfoList);
}
