package com.example.Bikroy.controller;

import com.example.Bikroy.dto.request.OrderRequest;
import com.example.Bikroy.dto.response.OrderResponse;
import com.example.Bikroy.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
    @GetMapping
    public List<OrderResponse> getOrderList(){return orderService.getOrderList();
    }

    @PutMapping("/{order-uuid}")
    public OrderResponse editOrder(@PathVariable("order-uuid") String uuid,
                                   @RequestBody OrderRequest orderRequest){
        return orderService.editOrder(uuid, orderRequest);
    }

    @DeleteMapping("/{order-uuid}")
    public boolean deleteOrder(@PathVariable("order-uuid") String uuid) {
        return orderService.deleteOrder(uuid);
    }
}
