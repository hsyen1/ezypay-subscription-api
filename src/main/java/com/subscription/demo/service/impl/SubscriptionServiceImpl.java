package com.subscription.demo.service.impl;

import com.subscription.demo.common.DateUtils;
import com.subscription.demo.common.SubscriptionType;
import com.subscription.demo.controller.dto.SubscriptionRequestDTO;
import com.subscription.demo.repository.PaymentSubscriptionRepository;
import com.subscription.demo.repository.entity.PaymentSubscription;
import com.subscription.demo.service.SubscriptionService;
import com.subscription.demo.service.SubscriptionValidationService;
import com.subscription.demo.service.model.SubscriptionCreationBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Yen Han Sern
 * Created on: 6:00 pm, 03/04/2022
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionValidationService subscriptionValidationService;

    @Autowired
    private PaymentSubscriptionRepository paymentSubscriptionRepository;

    @Override
    @Transactional
    public SubscriptionCreationBO create(SubscriptionRequestDTO request) {

        String subscriptionType = request.getSubscriptionType();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        double amount = request.getAmount();

        // business logic validation
        SubscriptionType subscriptionTypeEnum = SubscriptionType.findByCode(subscriptionType);

        subscriptionValidationService.validate(request);

        // calculate all recurring dates
        List<LocalDate> invoiceDates = calculateRecurringDates(startDate, endDate, subscriptionTypeEnum);

        // insert into PAYMENT_SUBSCRIPTION table
        paymentSubscriptionRepository.save(createSubscriptionEntity(request));

        // assemble subscription details and return
        return convertToSubscriptionCreationBO(subscriptionTypeEnum, invoiceDates, amount);
    }

    private List<LocalDate> calculateRecurringDates(LocalDate startDate, LocalDate endDate,
                                         SubscriptionType subscriptionType) {
        switch(subscriptionType) {
            case DAILY:
                return DateUtils.getDailyRecurringDates(startDate, endDate);
            case WEEKLY:
                return DateUtils.getWeeklyRecurringDates(startDate, endDate);
            case MONTHLY:
                return DateUtils.getMonthlyRecurringDates(startDate, endDate);
            default:
                throw new IllegalArgumentException("Invalid subscription type found!");
        }
    }

    private PaymentSubscription createSubscriptionEntity(SubscriptionRequestDTO requestDTO) {
        PaymentSubscription paymentSubscription = new PaymentSubscription();
        paymentSubscription.setBilling_amount(requestDTO.getAmount());
        paymentSubscription.setCustomerId(requestDTO.getCustomerId());
        // hardcode for now
        paymentSubscription.setPaymentMethod("CREDIT_CARD");
        paymentSubscription.setSubscriptionType(requestDTO.getSubscriptionType());
        paymentSubscription.setCreatedDate(LocalDateTime.now());
        paymentSubscription.setModifiedDate(LocalDateTime.now());
        return paymentSubscription;
    }

    private SubscriptionCreationBO convertToSubscriptionCreationBO(SubscriptionType subscriptionType,
                                                                   List<LocalDate> invoiceDates, double amount) {
        SubscriptionCreationBO creationBO = new SubscriptionCreationBO();
        creationBO.setAmount(amount);
        creationBO.setSubscriptionType(subscriptionType);
        creationBO.setInvoiceDates(DateUtils.convertDateToStringInList(invoiceDates, DateUtils.format));
        return creationBO;
    }
}
