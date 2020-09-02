package com.sj.controller;

import com.sj.dto.UserCartDto;
import com.sj.entity.UserCart;
import com.sj.repository.UserCartRepository;
import com.sj.service.CartService;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Cart")
public class CartHandler {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserCartRepository userCartRepository;

    @PostMapping("/saveCart")
    public ResultVO saveCart(@RequestBody UserCartDto userCartDto){
        return cartService.saveCart(userCartDto);
    }

    @GetMapping("/countUserCart/{userId}")
    public ResultVO countUserCart(@PathVariable("userId") Integer userId){
        return cartService.countUserCart(userId);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ResultVO findAllByUserId(@PathVariable("userId") Integer userId){
        return  cartService.findByUserCartId(userId);
    }

    @PutMapping("/addQuantity/{cartId}")
    public ResultVO addQuantity(@PathVariable("cartId") String cartId){
        return cartService.addQuantity(cartId);
    }

    @PutMapping("/reduceQuantity/{cartId}")
    public ResultVO reduceQuantity(@PathVariable("cartId") String cartId){
        return cartService.reduceQuantity(cartId);
    }

    @PostMapping("/deleteUserCart")
    public ResultVO deleteUserCart (@RequestBody  List<Map<String,String>> cartValues){
        return  cartService.deleteUserCart(cartValues);
    }



    //测试
    @GetMapping("/test/{userId}")
    public List<UserCart> test(@PathVariable("userId") Integer userId){
        return  userCartRepository.findCartByUserId(userId);
    }
}
