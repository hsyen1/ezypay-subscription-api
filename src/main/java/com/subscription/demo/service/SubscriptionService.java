package com.subscription.demo.service;

import com.subscription.demo.controller.dto.SubscriptionRequestDTO;
import com.subscription.demo.service.model.SubscriptionCreationBO;

/**
 * @author: Yen Han Sern
 * Created on: 5:59 pm, 20/03/2022
 */
public interface SubscriptionService {

    SubscriptionCreationBO create(SubscriptionRequestDTO request);
}
