package com.onlinelearningsystem.service.AccountService;

import com.onlinelearningsystem.model.Account;

import java.util.List;

public interface IAccountService {
    //Add new account
    Account createAccount(String email, String password, long roleId, boolean isBanned);
    //List all
    List<Account> getAllAccount();


}
