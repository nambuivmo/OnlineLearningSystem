package com.onlinelearningsystem.service.account;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDto> findAll() {
        return this.accountRepository.findAllAccount();
    }

    @Override
    public Account updateActive(long id, boolean isBanned) {
        Optional< Account > accountDb = this.accountRepository.findById(id);
        if(accountDb.isPresent()) {
            Account account = accountDb.get();
            account.setBanned(isBanned);
            return accountRepository.save(account);
        }else{
//            throw new ResourceNotFoundException("Record not found with id : " + accounts.getId());
            return null;
        }
    }

    @Override
    public List<AccountDto> getAccount(String email) {
        return this.accountRepository.getAccount(email);
    }


}
