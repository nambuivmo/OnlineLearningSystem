package com.onlinelearningsystem.dto;

import java.time.LocalDate;

public interface CourseDTO {
    public Long getId();

    public String getName();

    public String getDescription();

    public LocalDate getCreateDate();

    public String getFirstName();

    public String getLastName();
}
