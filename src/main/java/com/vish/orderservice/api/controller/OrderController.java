package com.vish.orderservice.api.controller;

import com.vish.orderservice.api.common.Payment;
import com.vish.orderservice.api.common.TransactionRequest;
import com.vish.orderservice.api.common.TransactionResponse;
import com.vish.orderservice.api.entity.Order;
import com.vish.orderservice.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderService service;

  @PostMapping("/placeOrder")
  public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
    return service.saveOrder(request);
  }
}
