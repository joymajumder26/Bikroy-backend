package com.example.Bikroy.service;

import com.example.Bikroy.dto.request.OrderRequest;
import com.example.Bikroy.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrderList();

    OrderResponse editOrder(String uuid, OrderRequest orderRequest);

    boolean deleteOrder(String uuid);
}
