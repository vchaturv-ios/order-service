package com.vish.orderservice.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  private int id;
  private String name;
  private int qty;
  private double price;
}
