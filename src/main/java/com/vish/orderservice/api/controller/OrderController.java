package com.vish.orderservice.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vish.orderservice.api.common.TransactionRequest;
import com.vish.orderservice.api.common.TransactionResponse;
import com.vish.orderservice.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

  @Autowired
  private OrderService service;

  @PostMapping("/placeOrder")
  public TransactionResponse bookOrder(@RequestBody TransactionRequest request) throws JsonProcessingException {
    return service.saveOrder(request);
  }
}
