package com.onlinelearningsystem.dto;

import java.time.LocalDate;

public interface SearchStudentDTO {
    public Long getId();

    public String getFirstName();

    public String getLastName();

    public LocalDate getDob();

    public boolean getGender();

    public String getAddress();

    public String getPhoneNumber();
}
