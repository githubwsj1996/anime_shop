package com.sj.controller;

import com.sj.dto.OrderDto;
import com.sj.entity.OrderDetails;
import com.sj.entity.OrdersLook;
import com.sj.repository.OrderDetailsRepository;
import com.sj.repository.OrdersRepository;
import com.sj.service.OrderService;
import com.sj.until.ResultUtil;
import com.sj.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Order")
public class OrderHandler {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailsRepository detailsRepository;

    @PostMapping("/promptlyOrder")
    public ResultVO promptlyOrder(@RequestBody OrderDto orderDto) {
        return orderService.PromptlySaveOrder(orderDto);
    }

    @GetMapping("/findPromptlyOrder/{orderId}")
    public ResultVO findPromptlyOrder(@PathVariable("orderId") String orderId) {
        return orderService.findPromptlySaveOrder(orderId);
    }

    @PostMapping("/saveCartOrders")
    public ResultVO saveCartOrders(@RequestBody List<Map<String, String>> cartValues) {
        return orderService.cartSaveDetails(cartValues);
    }

    @GetMapping("/findByState/{uid}/{state}")
    public ResultVO findNoByState(@PathVariable("uid") Integer uid,
                                  @PathVariable("state") Integer state) {
        return orderService.findByState(uid, state);
    }

    @PutMapping("/updateState/{orderId}/{state}")
    public ResultVO updateState(@PathVariable("orderId") String orderId,
                                @PathVariable("state") Integer state) {
        return orderService.updateState(orderId, state);
    }

    @GetMapping("/getExpire/{orderId}")
    public ResultVO getExpire(@PathVariable("orderId") String orderId) {
        return orderService.getExpire(orderId);
    }

    @DeleteMapping("/delExpireOrder/{orderId}")
    public ResultVO delExpireOrder(@PathVariable("orderId") String orderId) {
        return orderService.delExpireOrder(orderId);
    }

    @PutMapping("/cancelOrder/{orderId}/{causeId}")
    public ResultVO cancelOrder(@PathVariable("orderId") String orderId,
                                @PathVariable("causeId") String causeId) {
        return orderService.cancelOrder(orderId, causeId);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResultVO deleteOrder(@PathVariable("orderId") String orderId) {
        return orderService.deleteOrder(orderId);
    }

    @GetMapping("/countDetailsByAnimeId")
    public ResultVO countDetailsByAnimeId() {
        return orderService.countDetailsByAnimeId();
    }

//    @GetMapping("/test/{uid}")
//    public ResultVO test(@PathVariable("uid") String uid){
//        return ResultUtil.success(detailsRepository.findPromptlyOrder(uid));
//    }


}
