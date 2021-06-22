package com.vish.orderservice.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vish.orderservice.api.common.Payment;
import com.vish.orderservice.api.common.TransactionRequest;
import com.vish.orderservice.api.common.TransactionResponse;
import com.vish.orderservice.api.entity.Order;
import com.vish.orderservice.api.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {

  @Autowired
  private OrderRepository repository;

  @Autowired
  @Lazy
  private RestTemplate restTemplate;

  @Value("${microservice.payment-service.endppoints.endpoint.uri}")
  private String ENDPOINT_URL;

  private Logger log = LoggerFactory.getLogger(OrderService.class);

  public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
    String message = "";
    Order order = request.getOrder();
    Payment payment = request.getPayment();
    payment.setOrderId(order.getId());
    payment.setAmount(order.getPrice());

    log.info("OrderService request : {}", new ObjectMapper().writeValueAsString(request));

    Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL,payment, Payment.class);

    log.info("PaymentService response from OrderService Rest call : {}", new ObjectMapper().writeValueAsString(paymentResponse));

    message = paymentResponse.getPaymentStatus().equals("success")
      ?"payment proccesing successful and order place"
      :"there is failure in payment api, order added to cart";
    repository.save(order);
    return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),message);

  }

}
