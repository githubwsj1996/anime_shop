package com.sj.service.impl;

import com.sj.entity.AnimeSpecs;
import com.sj.repository.GoodsSpecsRepository;
import com.sj.service.SpecsService;
import com.sj.until.IconUtil;
import com.sj.until.ResultUtil;
import com.sj.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecsServiceImpl implements SpecsService {

    @Autowired
    private GoodsSpecsRepository goodsSpecsRepository;

    @Override
    public ResultVO findSpecsByAnimeId(Integer animeId) {
        List<AnimeSpecs> animeSpecsList = goodsSpecsRepository.findByAnimeId(animeId);

        List<TreeDetailsVO> treeDetailsVOList = animeSpecsList.stream()
                .map(e -> new TreeDetailsVO(
                        e.getSpecs_id(),
                        e.getSpecs_name(),
                        e.getSpecs_icon(),
                        e.getSpecs_preview()
                )).collect(Collectors.toList());
        TreeVO treeVO = new TreeVO();
        treeVO.setV(treeDetailsVOList);
        List<TreeVO> treeVOList = new ArrayList<>();
        treeVOList.add(treeVO);

        List<SkuListVO> skuListVOList = animeSpecsList.stream()
                .map(e -> new SkuListVO(
                        e.getSpecs_id().toString(),
                        e.getSpecs_price().intValue() * 100,
                        e.getSpecs_stock()
                )).collect(Collectors.toList());
        Integer price1 = null;
        Integer stock_num1 = null;

        GoodsVO goodsVO = new GoodsVO();
        for (AnimeSpecs animeSpecs : animeSpecsList) {
            goodsVO.setSpecs_icon(IconUtil.createIcon(animeSpecs.getAnimeInfo().getAnime_icon()));
            price1 = animeSpecs.getAnimeInfo().getAnime_price().intValue() * 100;
            stock_num1 = animeSpecs.getAnimeInfo().getAnime_stock();
        }
        String price2 = (price1 / 100) + ".00";

        SkuVO skuVO = new SkuVO();
        skuVO.setTree(treeVOList);
        skuVO.setList(skuListVOList);
        skuVO.setPrice(price2);
        skuVO.setStock_num(stock_num1);

        AnimeDetailsSkuVO animeDetailsSkuVO = new AnimeDetailsSkuVO();
        animeDetailsSkuVO.setGoodsVO(goodsVO);
        animeDetailsSkuVO.setSkuVO(skuVO);

        return ResultUtil.success(animeDetailsSkuVO);
    }
}
