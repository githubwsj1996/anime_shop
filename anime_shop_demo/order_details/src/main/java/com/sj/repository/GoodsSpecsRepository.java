package com.sj.repository;

import com.sj.entity.AnimeSpecs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsSpecsRepository {
    public AnimeSpecs findAllById(Integer specsId);
    public int updateSpecs(Integer specsId,Integer quantity);
    public void updateMoreStock(List animeSpecsList);
}
