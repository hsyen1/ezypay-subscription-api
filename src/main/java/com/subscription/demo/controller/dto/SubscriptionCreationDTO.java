package com.subscription.demo.controller.dto;

import java.util.List;

/**
 * @author: Yen Han Sern
 * Created on: 6:10 pm, 03/04/2022
 */
public class SubscriptionCreationDTO {

    private String errorMessage;
    private boolean success;
    private double amount;
    private String subscriptionType;
    private List<String> invoiceDates;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
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
        return "SubscriptionCreationDTO{" +
                "errorMessage='" + errorMessage + '\'' +
                ", success=" + success +
                ", amount=" + amount +
                ", subscriptionType='" + subscriptionType + '\'' +
                ", invoiceDates=" + invoiceDates +
                '}';
    }
}
