package com.onlinelearningsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateCourseDTO {
    private String name;
    private String description;
    private double price;
}
