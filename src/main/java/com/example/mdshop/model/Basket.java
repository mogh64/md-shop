package com.example.mdshop.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private UUID barCode;
    private LocalDate orderDate;
    @ManyToOne
    private Customer customer;
    private OrderStatus orderStatus;

    public Set<BasketItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<BasketItem> orderItems) {
        this.orderItems = orderItems;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<BasketItem> orderItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket order = (Basket) o;
        return Objects.equals(barCode, order.barCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barCode);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public Basket(Customer customer) {
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.Started;
        this.barCode = UUID.randomUUID();
        this.customer = customer;
    }

    protected Basket() {

    }

    public UUID getCode() {
        return barCode;
    }

    public void setCode(UUID code) {
        this.barCode = code;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
