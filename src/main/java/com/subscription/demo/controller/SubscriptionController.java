package com.subscription.demo.controller;

import com.subscription.demo.controller.dto.SubscriptionRequestDTO;
import com.subscription.demo.controller.dto.SubscriptionCreationDTO;
import com.subscription.demo.service.SubscriptionService;
import com.subscription.demo.service.model.SubscriptionCreationBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: Yen Han Sern
 * Created on: 5:58 pm, 03/04/2022
 */
@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @PostMapping(value="/api/payment/subscription")
    public ResponseEntity<SubscriptionCreationDTO> subscribe(@Valid @RequestBody SubscriptionRequestDTO requestDTO) {
        try {
            SubscriptionCreationBO subscriptionCreationBO = subscriptionService.create(requestDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(constructSuccessResponse(subscriptionCreationBO));
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(constructErrorResponse(e.getMessage()));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(constructErrorResponse(e.getMessage()));
        }
    }

    private SubscriptionCreationDTO constructSuccessResponse(SubscriptionCreationBO subscriptionCreationBO) {
        SubscriptionCreationDTO subscriptionCreationDTO = new SubscriptionCreationDTO();
        subscriptionCreationDTO.setSuccess(true);
        subscriptionCreationDTO.setInvoiceDates(subscriptionCreationBO.getInvoiceDates());
        subscriptionCreationDTO.setAmount(subscriptionCreationBO.getAmount());
        subscriptionCreationDTO.setSubscriptionType(subscriptionCreationBO.getSubscriptionType().getCode());
        return subscriptionCreationDTO;
    }

    private SubscriptionCreationDTO constructErrorResponse(String errorMessage) {
        SubscriptionCreationDTO subscriptionCreationDTO = new SubscriptionCreationDTO();
        subscriptionCreationDTO.setSuccess(false);
        subscriptionCreationDTO.setErrorMessage(errorMessage);
        return subscriptionCreationDTO;
    }

}
