package com.subscription.demo.repository;

import com.subscription.demo.repository.entity.PaymentSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Yen Han Sern
 * Created on: 1:29 am, 04/04/2022
 */
public interface PaymentSubscriptionRepository extends JpaRepository<PaymentSubscription, Long> {
}
