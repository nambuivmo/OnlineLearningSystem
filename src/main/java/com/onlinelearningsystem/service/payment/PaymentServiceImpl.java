package com.onlinelearningsystem.service.payment;

import com.onlinelearningsystem.dto.PaymentDTO;
import com.onlinelearningsystem.repository.PaymentRepository;
import com.onlinelearningsystem.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService{
   @Autowired
   private PaymentRepository paymentRepository;

    @Override
    public PageResponse<PaymentDTO> findAll(int pageNumber, String sortBy, String sortOrder, String nameStudent, String nameCourse) {
        int pageSize = 5;
        Pageable pageable;

        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        if (nameStudent == null) {
            nameStudent="";
        } else if (nameCourse==null) {
            nameCourse="";
        }
        Page<PaymentDTO> paymentPage = paymentRepository.findAllPayment(pageable,nameStudent,nameCourse);
        PageResponse<PaymentDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(paymentPage.getContent());
        pageResponse.setTotalElements(paymentPage.getTotalElements());
        pageResponse.setTotalPages(paymentPage.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
    }
}
