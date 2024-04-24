package com.onlinelearningsystem.service.AccountService;

import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.model.RoleAccount;
import com.onlinelearningsystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService{
    private final AccountRepository accountRepository;

}
