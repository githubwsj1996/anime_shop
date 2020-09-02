package com.sj.service;

import com.sj.vo.ResultVO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GoodsService {
    public ResultVO findGoodsPage(int limit,int size);
    public ResultVO findByCategory(String categoryName);
    public ResultVO findById(Integer animeId);
    public boolean addGoodsIdToRedis();

    public Boolean parseGoods(String keywords) throws IOException;
    public List<Map<String, Object>> searchGoods(String keyword) throws IOException;
}
