package com.subscription.demo.service.model;

import com.subscription.demo.common.SubscriptionType;

import java.util.List;

/**
 * @author: Yen Han Sern
 * Created on: 6:07 pm, 03/04/2022
 */
public class SubscriptionCreationBO {

    private double amount;

    private SubscriptionType subscriptionType;

    private List<String> invoiceDates;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public List<String> getInvoiceDates() {
        return invoiceDates;
    }

    public void setInvoiceDates(List<String> invoiceDates) {
        this.invoiceDates = invoiceDates;
    }

    @Override
    public String toString() {
        return "SubscriptionCreationBO{" +
                "amount=" + amount +
                ", subscriptionType=" + subscriptionType +
                ", invoiceDates=" + invoiceDates +
                '}';
    }
}
