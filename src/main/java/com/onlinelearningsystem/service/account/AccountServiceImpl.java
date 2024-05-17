package com.onlinelearningsystem.service.account;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.dto.LoginDTO;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.repository.AccountRepository;
import com.onlinelearningsystem.response.LoginResponse;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.security.JwtService;
import com.onlinelearningsystem.token.Token;
import com.onlinelearningsystem.token.TokenRepository;
import com.onlinelearningsystem.token.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public PageResponse<AccountDto> findAll(int pageNo, int pageSize, String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<AccountDto> accountsPage = accountRepository.findAllAccount(pageable);
        List<AccountDto> content = accountsPage.getContent();

        PageResponse<AccountDto> pageResponse = new PageResponse<>();
        pageResponse.setItems(content);
        pageResponse.setPageNo(accountsPage.getNumber());
        pageResponse.setPageSize(accountsPage.getSize());
        pageResponse.setTotalElements(accountsPage.getTotalElements());
        pageResponse.setTotalPages(accountsPage.getTotalPages());
        pageResponse.setLast(accountsPage.isLast());
        return pageResponse;
    }

//    private AccountDto convertToAccountDto(Account account) {
//        AccountDto accountDto = new AccountDto();
//        accountDto.setId(account.getIdAccount());
//        accountDto.setEmail(account.getEmail());
//        accountDto.setPassword(account.getPassword());
//        accountDto.setBanned(account.isBanned());
//        accountDto.setRoleName(account.getRoleAccount() != null ? account.getRoleAccount().getRoleName() : null);
//        return accountDto;
//    }

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

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        Optional<Account> account = accountRepository.findByEmail(loginDTO.getEmail());
        if(account.isPresent()) {
            String password = loginDTO.getPassword();
            String endcoderPassword = account.get().getPassword();
            var isPwdRight= passwordEncoder.matches(password, endcoderPassword);
            if (isPwdRight) {
                Optional<Account> accounts = accountRepository.findOneByEmailAndPassword(loginDTO.getEmail(), endcoderPassword);
                if (accounts.isPresent()) {
                    String jwtToken = jwtService.generateToken(accounts.get());
                    String refreshToken = jwtService.generateRefreshToken(accounts.get());
                    saveUserToken(accounts.get(),jwtToken);
                    return new LoginResponse("Login Success", true, jwtToken, refreshToken);
                } else {
                    return new LoginResponse( "Login Failed", false);
                }
            } else {
                System.out.println(password);
                System.out.println(endcoderPassword);
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }
    }



    private void saveUserToken(Account account, String jwtToken) {
        var token = Token.builder()
                .account(account)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

}
