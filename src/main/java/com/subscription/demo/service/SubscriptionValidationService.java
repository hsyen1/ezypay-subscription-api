package com.subscription.demo.service;

import com.subscription.demo.controller.dto.SubscriptionRequestDTO;

/**
 * @author: Yen Han Sern
 * Created on: 11:18 pm, 03/04/2022
 */
public interface SubscriptionValidationService {

    void validate(SubscriptionRequestDTO requestDTO);
}
