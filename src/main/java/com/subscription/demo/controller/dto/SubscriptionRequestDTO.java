package com.subscription.demo.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author: Yen Han Sern
 * Created on: 6:03 pm, 03/04/2022
 */
public class SubscriptionRequestDTO {

    @Min(1)
    private double amount;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotBlank
    private String subscriptionType;

    private String dayOfWeek;

    private Integer dayOfMonth;

    @Min(1)
    private long customerId;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public String toString() {
        return "SubscriptionRequestDTO{" +
                "amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", dayOfMonth=" + dayOfMonth +
                ", customerId=" + customerId +
                '}';
    }
}
