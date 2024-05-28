package com.onlinelearningsystem.service.account;


import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.dto.AddAccountDTO;
import com.onlinelearningsystem.dto.LoginDTO;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.response.LoginResponse;
import com.onlinelearningsystem.response.MessResponse;
import com.onlinelearningsystem.response.PageResponse;


import java.util.List;

public interface IAccountService {

    Account updateActive(long id, boolean isBanned);

    LoginResponse login(LoginDTO loginDTO);

    PageResponse<AccountDto> findAll(int pageNumber,String sortBy, String sortOrder,String email);

    void register(AddAccountDTO account);

    List<String> getRoles();

    MessResponse forgotPassword(String email);

    MessResponse changePassword(String email,String oldPassword,String newPassword);

}
