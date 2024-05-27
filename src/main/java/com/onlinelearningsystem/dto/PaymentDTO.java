package com.onlinelearningsystem.dto;

import java.time.LocalDate;

public interface PaymentDTO {

    public long getIdPayment();
    public double getAmount();
    public String getStudentName();
    public String getCourseName();
    public LocalDate getPaymentDate();
}
