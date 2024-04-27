package com.onlinelearningsystem.service.account;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;

import java.util.List;

public interface IAccountService {

    List<AccountDto> findAll();


    Account updateActive(long id, boolean isBanned);

    List<AccountDto> getAccount(String email);
}
