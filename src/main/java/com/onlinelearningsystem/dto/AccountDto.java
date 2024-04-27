package com.onlinelearningsystem.dto;

public interface AccountDto {

    public Long getIdAccount();

    public String getEmail();

    public String getPassword();

    public boolean getIsBanned();

    public String getRoleName();
}
