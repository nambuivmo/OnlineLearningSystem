package com.onlinelearningsystem.service.account;

import com.onlinelearningsystem.auth.AuthenticationResponse;
import com.onlinelearningsystem.auth.RegisterRequest;
import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.dto.AddAccountDTO;
import com.onlinelearningsystem.dto.LoginDTO;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.response.LoginResponse;
import com.onlinelearningsystem.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAccountService {



    Account updateActive(long id, boolean isBanned);

    List<AccountDto> getAccount(String email);

    LoginResponse login(LoginDTO loginDTO);

    PageResponse<AccountDto> findAll(int pageNo, int pageSize, String sortBy, String sortDir);

//    Page<AccountDto> findAll(Pageable pageable);
}
