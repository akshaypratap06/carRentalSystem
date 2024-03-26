package com.example.carrental.payment;

import com.example.carrental.VehicleType;
import jakarta.annotation.PostConstruct;

public class FourWheelerPayment extends Payment {
    @Override
    public Payment calculateCost(long endTime, long startTime) {
        super.cost=((endTime-startTime)/60000)*10;
        return this;
    }
    @PostConstruct
    public void setType(){
        super.type= VehicleType.FOUR_WHEELER;
    }
}
