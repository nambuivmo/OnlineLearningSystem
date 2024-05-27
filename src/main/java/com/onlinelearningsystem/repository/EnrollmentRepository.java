package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.model.Enrollment;
import org.springframework.data.repository.CrudRepository;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
}
