package com.example.carrental.payment;

import com.example.carrental.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Payment {

    protected int ticketId;

    protected String vehicleId;
    protected VehicleType type;

    protected Date issueDate;

    protected Date endTime;

    protected String user;

    protected long cost;

    abstract public Payment calculateCost(long endTime, long startTime);

    public abstract void setType();
}
