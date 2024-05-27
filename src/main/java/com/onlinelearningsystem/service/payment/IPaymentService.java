package com.onlinelearningsystem.service.payment;

import com.onlinelearningsystem.dto.PaymentDTO;
import com.onlinelearningsystem.response.PageResponse;

public interface IPaymentService {
    PageResponse<PaymentDTO> findAll(int pageNumber, String sortBy, String sortOrder, String nameStudent, String nameCourse);
}
