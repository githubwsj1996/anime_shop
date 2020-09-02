package com.sj.feign;

import com.sj.dto.UserCartDto;
import com.sj.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "animegoods")
public interface CartFeign {

    @PostMapping("/Cart/saveCart")
    public ResultVO saveCart(@RequestBody UserCartDto userCartDto);

    @GetMapping("/Cart/countUserCart/{userId}")
    public ResultVO countUserCart(@PathVariable("userId") Integer userId);

    @GetMapping("/Cart/findAllByUserId/{userId}")
    public ResultVO findAllByUserId(@PathVariable("userId") Integer userId);

    @PutMapping("/Cart/addQuantity/{cartId}")
    public ResultVO addQuantity(@PathVariable("cartId") String cartId);

    @PutMapping("/Cart/reduceQuantity/{cartId}")
    public ResultVO reduceQuantity(@PathVariable("cartId") String cartId);

    @PostMapping("/Cart/deleteUserCart")
    public ResultVO deleteUserCart (@RequestBody  List<Map<String,String>> cartValues);
}
