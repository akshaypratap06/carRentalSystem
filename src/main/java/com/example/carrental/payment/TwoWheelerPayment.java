package com.example.carrental.payment;

import com.example.carrental.VehicleType;
import jakarta.annotation.PostConstruct;

public class TwoWheelerPayment extends Payment {

    @Override
    public Payment calculateCost(long endTime, long startTime) {
        super.cost=((endTime-startTime)/60000)*2;
        return this;
    }

    @PostConstruct
    public void setType() {
        super.type= VehicleType.TWO_WHEELER;
    }
}
