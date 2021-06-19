package com.vish.orderservice.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

  private int paymentId;
  private String paymentStatus;
  private String transactionId;
  private int orderId;
  private double amount;

}
