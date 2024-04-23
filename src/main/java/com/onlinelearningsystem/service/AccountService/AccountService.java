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

    @Override
    public Account createAccount(String email, String password, long roleId, boolean isBanned) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setBanned(isBanned);

        RoleAccount roleAccount = new RoleAccount();
        roleAccount.setIdRole(roleId);
        account.setRoleAccount(roleAccount);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

}
