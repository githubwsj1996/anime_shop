package com.sj.service.impl;

import com.alibaba.fastjson.JSON;
import com.sj.entity.AnimeCategory;
import com.sj.entity.AnimeInfo;
import com.sj.repository.GoodsCategoryRepository;
import com.sj.repository.GoodsRepository;
import com.sj.service.GoodsService;
import com.sj.until.IconUtil;
import com.sj.until.RedisUtil;
import com.sj.until.ResultUtil;
import com.sj.until.StrUtil;
import com.sj.vo.AnimeDetailsVO;
import com.sj.vo.AnimeInfoVO;
import com.sj.vo.AnimeIndexVO;
import com.sj.vo.ResultVO;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsCategoryRepository categoryRepository;

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Override
    public ResultVO findGoodsPage(int limit, int size) {
        List<AnimeInfo> animeInfoList = goodsRepository.findAllPage(limit, size);
        int animeCount = goodsRepository.animeCount();

        List<AnimeCategory> categoryList = categoryRepository.findAllCate();
        Map<String, String> categoryMap = null;
        List<Map<String, String>> categoryVOList = new ArrayList<>();
        for (AnimeCategory animeCategory : categoryList) {
            categoryMap = new HashMap<>();
            categoryMap.put("name", animeCategory.getCategory_name());
            categoryVOList.add(categoryMap);
        }

        List<AnimeInfoVO> animeInfoVOList = animeInfoList.stream()
                .map(e -> new AnimeInfoVO(
                        e.getAnime_id(),
                        e.getAnime_name(),
                        e.getAnimeCategory(),
                        e.getAnime_price() + ".00",
                        e.getAnime_desc(),
                        e.getAnime_stock(),
                        IconUtil.createIcon(e.getAnime_icon()),
                        StrUtil.createData(e.getAnime_tag()),
                        e.getAdmin_id()
                )).collect(Collectors.toList());

        AnimeIndexVO animeIndexVO = new AnimeIndexVO();
        animeIndexVO.setAnimeInfoVOList(animeInfoVOList);
        animeIndexVO.setCountPages(animeCount);
        animeIndexVO.setCategories(categoryVOList);

        return ResultUtil.success(animeIndexVO);
    }

    @Override
    public ResultVO findByCategory(String categoryName) {
        List<AnimeInfo> animeInfoList = goodsRepository.findAllByCategory(categoryName);

        List<AnimeInfoVO> animeInfoVOList = animeInfoList.stream()
                .map(e -> new AnimeInfoVO(
                        e.getAnime_id(),
                        e.getAnime_name(),
                        e.getAnimeCategory(),
                        e.getAnime_price() + ".00",
                        e.getAnime_desc(),
                        e.getAnime_stock(),
                        IconUtil.createIcon(e.getAnime_icon()),
                        StrUtil.createData(e.getAnime_tag()),
                        e.getAdmin_id()
                )).collect(Collectors.toList());

        AnimeIndexVO animeIndexVO = new AnimeIndexVO();
        animeIndexVO.setAnimeInfoVOList(animeInfoVOList);

        return ResultUtil.success(animeIndexVO);
    }

    @Override
    public ResultVO findById(Integer animeId) {
        AnimeInfo animeInfo = goodsRepository.findAllById(animeId);
        AnimeDetailsVO animeDetailsVO = new AnimeDetailsVO();
        animeDetailsVO.setAnime_id(animeInfo.getAnime_id());
        animeDetailsVO.setAnime_name(animeInfo.getAnime_name());
        animeDetailsVO.setAnimeCategory(animeInfo.getAnimeCategory());
        animeDetailsVO.setAnime_price(animeInfo.getAnime_price() + ".00");
        animeDetailsVO.setAnime_desc(animeInfo.getAnime_desc());
        animeDetailsVO.setAnime_stock(animeInfo.getAnime_stock());
        animeDetailsVO.setAdmin_id(animeInfo.getAdmin_id());
        animeDetailsVO.setAnime_icon(IconUtil.createListIcon(animeInfo.getAnime_icon()));
        animeDetailsVO.setAnime_tag(StrUtil.createData(animeInfo.getAnime_tag()));

        redisUtil.incr("animeId"+animeId,1);
        animeDetailsVO.setCount(redisUtil.get("animeId"+animeId));
        return ResultUtil.success(animeDetailsVO);
    }

    /*向redis中添加animeId*/
    @Override
    public boolean addGoodsIdToRedis() {
        List<AnimeInfo> animeInfoList=goodsRepository.findAllGoods();
        for (AnimeInfo animeInfo : animeInfoList) {
            redisTemplate.opsForValue().set("animeId"+animeInfo.getAnime_id(),1);
        }
        return true;
    }

    /*解析数据，存放到es中*/
    @Override
    public Boolean parseGoods(String keywords) throws IOException {
        List<AnimeInfo> animeInfoList=goodsRepository.findAllGoods();

        List<AnimeInfoVO> animeInfoVOList=animeInfoList.stream()
                .map(e ->new AnimeInfoVO(
                        e.getAnime_id(),
                        e.getAnime_name(),
                        e.getAnimeCategory(),
                        e.getAnime_price() + ".00",
                        e.getAnime_desc(),
                        e.getAnime_stock(),
                        IconUtil.createIcon(e.getAnime_icon()),
                        StrUtil.createData(e.getAnime_tag()),
                        e.getAdmin_id()
                )).collect(Collectors.toList());

        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> map=null;
        for (AnimeInfoVO animeInfoVO : animeInfoVOList) {
            map=new HashMap<>();
            map.put("anime_id",animeInfoVO.getAnime_id());
            map.put("title",animeInfoVO.getAnime_name());
            map.put("price",animeInfoVO.getAnime_price());
            map.put("desc",animeInfoVO.getAnime_desc());
            map.put("thumb",animeInfoVO.getAnime_icon());
            map.put("tag",animeInfoVO.getAnime_tag());
            list.add(map);
        }

        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.timeout("2m");

        for (Map<String,Object> map1 : list) {
            bulkRequest.add(
                    new IndexRequest("anime_goods")
                        .source(JSON.toJSONString(map1), XContentType.JSON)
            );
        }
        BulkResponse bulk=client.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulk.hasFailures();
    }

    /*Es精准查询*/
    @Override
    public List<Map<String, Object>> searchGoods(String keyword) throws IOException {
        //搜索条件
        SearchRequest searchRequest=new SearchRequest("anime_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //精准匹配
        MatchQueryBuilder matchQueryBuilder= QueryBuilders.matchQuery("title",keyword);
        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse=client.search(searchRequest,RequestOptions.DEFAULT);

        //解析结果
        List<Map<String,Object>> list=new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }


}
