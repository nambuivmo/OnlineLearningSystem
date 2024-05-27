package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.PaymentDTO;
import com.onlinelearningsystem.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(nativeQuery = true,
            value = "SELECT p.id as idPayment,\n" +
                    "    CONCAT(s.first_name, ' ', s.last_name) as studentName,\n" +
                    "    c.course_name AS courseName,\n" +
                    "    p.amount AS amount,\n" +
                    "    p.payment_date AS paymentDate\n" +
                    "FROM payment p\n" +
                    "JOIN  enrollment e ON p.id = e.id_payment\n" +
                    "JOIN student s ON e.id_student = s.id\n" +
                    "JOIN  course c ON e.id_course = c.id\n" +
                    "Where CONCAT(s.first_name, ' ', s.last_name) LIKE CONCAT('%', ?1, '%') AND c.course_name LIKE CONCAT('%', ?2, '%')"
    )
    Page<PaymentDTO> findAllPayment(Pageable pageable, String nameStudent, String nameCourse);
}
