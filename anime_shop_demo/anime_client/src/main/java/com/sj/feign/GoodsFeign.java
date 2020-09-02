package com.sj.feign;

import com.sj.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@FeignClient(value = "animegoods")
public interface GoodsFeign {

    @GetMapping("/Goods/findAllPage/{limit}/{size}")
    public ResultVO findAllPage(@PathVariable("limit") int limit,
                                @PathVariable("size") int size);

    @GetMapping("/Goods/findAllByCategory/{categoryName}")
    public ResultVO findAllByCategory(@PathVariable("categoryName")String categoryName);

    @GetMapping("/Goods/findById/{animeId}")
    public ResultVO findById(@PathVariable("animeId") Integer animeId);

    @GetMapping("/Specs/findByAnimeId/{animeId}")
    public ResultVO findSpecsByAnimeId(@PathVariable("animeId")Integer animeId);

    @GetMapping("/Goods/parse/{keyWords}")
    public Boolean parseGoods(@PathVariable("keyWords") String keyWords) throws IOException;

    @GetMapping("/Goods/searchGoods/{title}")
    public List<Map<String, Object>> searchGoods(@PathVariable("title") String title) throws IOException;
}
