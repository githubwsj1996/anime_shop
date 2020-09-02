package com.sj.controller;

import com.sj.dto.OrderDto;
import com.sj.feign.OrderFeign;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Order")
public class OrderHandler {
    @Autowired
    private OrderFeign orderFeign;

    @PostMapping("/promptlyOrder")
    public ResultVO promptlyOrder(@RequestBody OrderDto orderDto){
        return  orderFeign.promptlyOrder(orderDto);
    }

    @GetMapping("/findPromptlyOrder/{orderId}")
    public ResultVO findPromptlyOrder(@PathVariable("orderId") String orderId){
        return orderFeign.findPromptlyOrder(orderId);
    }

    @PostMapping("/saveCartOrders")
    public ResultVO saveCartOrders(@RequestBody List<Map<String,String>> cartValues){
        return  orderFeign.saveCartOrders(cartValues);
    }

    @GetMapping("/findByState/{uid}/{state}")
    public ResultVO findByState(@PathVariable("uid") Integer uid,
                                  @PathVariable("state") Integer state){
        return  orderFeign.findByState(uid,state);
    }

    @PutMapping("/updateState/{orderId}/{state}")
    public ResultVO updateState(@PathVariable("orderId") String orderId,
                                @PathVariable("state") Integer state){
        return orderFeign.updateState(orderId,state);
    }

    @GetMapping("/getExpire/{orderId}")
    public ResultVO getExpire(@PathVariable("orderId") String orderId){
        return orderFeign.getExpire(orderId);
    }

    @DeleteMapping("/delExpireOrder/{orderId}")
    public ResultVO delExpireOrder(@PathVariable("orderId") String orderId){
        return orderFeign.delExpireOrder(orderId);
    }

    @PutMapping("/cancelOrder/{orderId}/{causeId}")
    public ResultVO cancelOrder(@PathVariable("orderId") String orderId,
                                @PathVariable("causeId") String causeId){
        return  orderFeign.cancelOrder(orderId,causeId);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResultVO deleteOrder(@PathVariable("orderId") String orderId){
        return orderFeign.deleteOrder(orderId);
    }

    @GetMapping("/countDetailsByAnimeId")
    public ResultVO countDetailsByAnimeId(){
        return orderFeign.countDetailsByAnimeId();
    }
}
