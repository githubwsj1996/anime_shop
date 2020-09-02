package com.sj.controller;

import com.sj.service.SpecsService;
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
    private SpecsService specsService;

    @GetMapping("/findByAnimeId/{animeId}")
    public ResultVO findByAnimeId(@PathVariable("animeId")Integer animeId)
    {
        return  specsService.findSpecsByAnimeId(animeId);
    }


}
