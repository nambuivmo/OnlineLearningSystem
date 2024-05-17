package com.onlinelearningsystem.auth;

import com.onlinelearningsystem.model.RoleAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String email;
  private String password;
  private Long role;
  private boolean isBanned;
}
