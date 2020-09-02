package com.sj.repository;

import com.sj.entity.AnimeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsRepository {
    //分页查询商品信息
    public List<AnimeInfo> findAllPage(int limit, int size);
    //统计总行数
    public int animeCount();
    //分类查询
    public List<AnimeInfo> findAllByCategory(String categoryName);
    //Id查询
    public AnimeInfo findAllById(Integer animeId);
    //修改商品的库存
    public int updateByGoods(Integer animeId,Integer quantity);
    //删除购物车批量增加库存
    public void updateAnimeStock(List animeInfoList);

    //查询所有商品
    public  List<AnimeInfo> findAllGoods();

}
