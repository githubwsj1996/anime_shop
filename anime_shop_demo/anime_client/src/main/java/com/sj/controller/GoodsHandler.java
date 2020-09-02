package com.sj.controller;

import com.sj.feign.GoodsFeign;
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
    private GoodsFeign goodsFeign;

    @GetMapping("/findAllPage/{limit}/{size}")
    public ResultVO findAllPage(@PathVariable("limit") int limit,
                                @PathVariable("size") int size){
        return  goodsFeign.findAllPage(limit,size);
    }

    @GetMapping("/findAllByCategory/{categoryName}")
    public ResultVO findAll(@PathVariable("categoryName")String categoryName){
        return  goodsFeign.findAllByCategory(categoryName);
    }

    @GetMapping("/findById/{animeId}")
    public ResultVO findByID(@PathVariable("animeId") Integer animeId){
        return goodsFeign.findById(animeId);
    }

    @GetMapping("/parse/{keyWords}")
    public Boolean parseGoods(@PathVariable("keyWords") String keyWords) throws IOException{
        return  goodsFeign.parseGoods(keyWords);
    }

    @GetMapping("/searchGoods/{title}")
    public List<Map<String, Object>> searchGoods(@PathVariable("title") String title) throws IOException{
        return goodsFeign.searchGoods(title);
    }

}
