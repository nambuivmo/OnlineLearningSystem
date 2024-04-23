package com.onlinelearningsystem.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountResponse {
    private long id;
    private String email;
    private String password;
    private long idRole;
    private boolean isBanned;

    public AccountResponse(String email, String password, long idRole, boolean isBanned) {
        this.email = email;
        this.password = password;
        this.idRole = idRole;
        this.isBanned = isBanned;
    }
}
