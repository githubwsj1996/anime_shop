package com.sj.repository;

import com.sj.entity.AnimeCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
GoodsCategoryRepository {
    public List<AnimeCategory> findAllCate();
    public AnimeCategory findAllById(Integer category_id);
}
