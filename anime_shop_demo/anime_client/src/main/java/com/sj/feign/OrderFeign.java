package com.sj.feign;

import com.sj.dto.OrderDto;
import com.sj.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "orderdetails")
public interface OrderFeign {
    @PostMapping("/Order/promptlyOrder")
    public ResultVO promptlyOrder(@RequestBody OrderDto orderDto);

    @GetMapping("/Order/findPromptlyOrder/{orderId}")
    public ResultVO findPromptlyOrder(@PathVariable("orderId") String orderId);

    @PostMapping("/Order/saveCartOrders")
    public ResultVO saveCartOrders(@RequestBody List<Map<String,String>> cartValues);

    @GetMapping("/Order/findByState/{uid}/{state}")
    public ResultVO findByState(@PathVariable("uid") Integer uid,
                                  @PathVariable("state") Integer state);

    @PutMapping("/Order/updateState/{orderId}/{state}")
    public ResultVO updateState(@PathVariable("orderId") String orderId,
                                @PathVariable("state") Integer state);

    @GetMapping("/Order/getExpire/{orderId}")
    public ResultVO getExpire(@PathVariable("orderId") String orderId);

    @DeleteMapping("/Order/delExpireOrder/{orderId}")
    public ResultVO delExpireOrder(@PathVariable("orderId") String orderId);

    @PutMapping("/Order/cancelOrder/{orderId}/{causeId}")
    public ResultVO cancelOrder(@PathVariable("orderId") String orderId,
                                @PathVariable("causeId") String causeId);

    @DeleteMapping("/Order/deleteOrder/{orderId}")
    public ResultVO deleteOrder(@PathVariable("orderId") String orderId);

    @GetMapping("/Order/countDetailsByAnimeId")
    public ResultVO countDetailsByAnimeId();
}
