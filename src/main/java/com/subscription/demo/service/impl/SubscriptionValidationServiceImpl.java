package com.subscription.demo.service.impl;

import com.subscription.demo.common.SubscriptionType;
import com.subscription.demo.controller.dto.SubscriptionRequestDTO;
import com.subscription.demo.service.SubscriptionValidationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author: Yen Han Sern
 * Created on: 11:20 pm, 03/04/2022
 */
@Service
public class SubscriptionValidationServiceImpl implements SubscriptionValidationService {

    @Override
    public void validate(SubscriptionRequestDTO requestDTO) {
        String subscriptionType = requestDTO.getSubscriptionType();
        LocalDate startDate = requestDTO.getStartDate();
        LocalDate endDate = requestDTO.getEndDate();

        // check subscription type
        SubscriptionType subscriptionTypeEnum = SubscriptionType.findByCode(subscriptionType);
        Assert.notNull(subscriptionTypeEnum, "Invalid subscription type: " + subscriptionType);

        validateConditionalParams(requestDTO);

        // check subscription period range
        Period period = Period.between(startDate, endDate);
        int months = Math.abs(period.getMonths());
        int days = Math.abs(period.getDays());

        if(months == 3) {
            Assert.isTrue(days == 0, "Subscription period cannot exceed more than 3 months");
        } else if(months > 3) {
            throw new IllegalArgumentException("Subscription period cannot exceed more than 3 months");
        }
    }

    private void validateConditionalParams(SubscriptionRequestDTO requestDTO) {
        if(StringUtils.equals(SubscriptionType.WEEKLY.getCode(), requestDTO.getSubscriptionType())) {
            Assert.isTrue(StringUtils.isNotBlank(requestDTO.getDayOfWeek()),
                    "dayOfWeek cannot be blank/null");
        }

        if(StringUtils.equals(SubscriptionType.MONTHLY.getCode(), requestDTO.getSubscriptionType())) {
            Assert.isTrue(requestDTO.getDayOfMonth() > 0 && requestDTO.getDayOfMonth() <= 31,
                    "dayOfMonth must be within the range of 1 to 31");
        }
    }
}
