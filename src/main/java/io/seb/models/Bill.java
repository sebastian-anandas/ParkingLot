package io.seb.models;


import java.util.Date;
import java.util.List;

public class Bill extends BaseModel {

    private Ticket ticket;
    private Gate gate;
    private Operator generatedBy;
    private BillStatus billStatus;
    private Date exitTime;
    private int amount;
    private List<Payment> payments;
    private FeesCalculationStrategyType feesCalculationStrategyType;



}
