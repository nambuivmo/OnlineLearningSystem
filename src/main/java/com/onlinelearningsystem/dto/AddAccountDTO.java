package com.onlinelearningsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccountDTO {
    private String email;
    private String password;
    private long idRole;
}
