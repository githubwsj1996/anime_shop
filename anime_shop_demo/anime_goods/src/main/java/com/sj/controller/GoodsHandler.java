package com.sj.controller;


import com.sj.service.GoodsService;

import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Goods")
public class GoodsHandler {

    @Autowired
    private GoodsService goodsService;


    @GetMapping("/findAllPage/{limit}/{size}")
    public ResultVO findAllPage(@PathVariable("limit") int limit,
                                @PathVariable("size") int size) {
        int index = (limit - 1) * size;
        return goodsService.findGoodsPage(index, size);
    }

    @GetMapping("/findAllByCategory/{categoryName}")
    public ResultVO findAllByCategory(@PathVariable("categoryName")String categoryName){
       return goodsService.findByCategory(categoryName);
    }

    @GetMapping("/findById/{animeId}")
    public ResultVO findById(@PathVariable("animeId") Integer animeId)
    {
        return goodsService.findById(animeId);
    }

    @GetMapping("/parse/{keyWords}")
    public Boolean parseGoods(@PathVariable("keyWords") String keyWords) throws IOException {
        return goodsService.parseGoods(keyWords);
    }

    @GetMapping("/searchGoods/{title}")
    public List<Map<String, Object>> searchGoods(@PathVariable("title") String title) throws IOException{
        return  goodsService.searchGoods(title);
    }

    @GetMapping("/addGoodsIdToRedis")
    public Boolean addGoodsIdToRedis(){
        return goodsService.addGoodsIdToRedis();
    }
}
