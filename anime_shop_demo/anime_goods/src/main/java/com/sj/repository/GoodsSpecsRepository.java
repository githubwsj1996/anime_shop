package com.sj.repository;

import com.sj.entity.AnimeSpecs;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsSpecsRepository {
    //查询商品的所有规格
    public List<AnimeSpecs> findByAnimeId(Integer animeId);
    //规格Id查询
    public AnimeSpecs findAllById(Integer specsId);
    //修改规格的库存数
    public int updateSpecs(Integer specsId,Integer quantity);
    //批量更新库存
    public void updateSpecsStock (List animeSpecsList);

}
