package com.subscription.demo.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: Yen Han Sern
 * Created on: 1:28 am, 04/04/2022
 */
@Entity
public class PaymentSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String subscriptionType;

    private String paymentMethod;

    private double billing_amount;

    private String description;

    private long customerId;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getBilling_amount() {
        return billing_amount;
    }

    public void setBilling_amount(double billing_amount) {
        this.billing_amount = billing_amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "PaymentSubscription{" +
                "id=" + id +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", billing_amount=" + billing_amount +
                ", description='" + description + '\'' +
                ", customerId=" + customerId +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
