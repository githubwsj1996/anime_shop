package com.sj.controller;

import com.sj.feign.GoodsFeign;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Specs")
public class SpecsHandler {

    @Autowired
    private GoodsFeign goodsFeign;

    @GetMapping("/findByAnimeId/{animeId}")
    public ResultVO findByAnimeId(@PathVariable("animeId")Integer animeId){
       return goodsFeign.findSpecsByAnimeId(animeId);
    }
}
