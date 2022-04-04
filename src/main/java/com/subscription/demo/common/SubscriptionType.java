package com.subscription.demo.common;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: Yen Han Sern
 * Created on: 10:36 pm, 03/04/2022
 */
public enum SubscriptionType {

    DAILY("DAILY"),

    WEEKLY("WEEKLY"),

    MONTHLY("MONTHLY");

    private final String code;

    SubscriptionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SubscriptionType findByCode(String code) {
        for(SubscriptionType type : values()) {
            if(StringUtils.equals(code, type.getCode())) {
                return type;
            }
        }
        return null;
    }

}
