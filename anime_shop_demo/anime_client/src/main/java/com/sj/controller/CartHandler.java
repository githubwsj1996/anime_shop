package com.sj.controller;

import com.sj.dto.UserCartDto;
import com.sj.feign.CartFeign;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Cart")
public class CartHandler {
    @Autowired
    private CartFeign cartFeign;

    @PostMapping("/saveCart")
    public ResultVO saveCart(@RequestBody UserCartDto userCartDto) {
        return cartFeign.saveCart(userCartDto);
    }

    @GetMapping("/countUserCart/{userId}")
    public ResultVO countUserCart(@PathVariable("userId") Integer userId) {
        return cartFeign.countUserCart(userId);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ResultVO findAllByUserId(@PathVariable("userId") Integer userId) {
        return cartFeign.findAllByUserId(userId);
    }

    @PutMapping("/addQuantity/{cartId}")
    public ResultVO addQuantity(@PathVariable("cartId") String cartId) {
        return cartFeign.addQuantity(cartId);
    }

    @PutMapping("/reduceQuantity/{cartId}")
    public ResultVO reduceQuantity(@PathVariable("cartId") String cartId) {
        return cartFeign.reduceQuantity(cartId);
    }

    @PostMapping("/deleteUserCart")
    public ResultVO deleteUserCart (@RequestBody  List<Map<String,String>> cartValues) {
        return cartFeign.deleteUserCart(cartValues);
    }
}
