package com.onlinelearningsystem.service.account;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.dto.AddAccountDTO;
import com.onlinelearningsystem.dto.LoginDTO;
import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.model.RoleAccount;
import com.onlinelearningsystem.repository.AccountRepository;
import com.onlinelearningsystem.repository.RoleRepository;
import com.onlinelearningsystem.response.LoginResponse;
import com.onlinelearningsystem.response.PageResponse;
import com.onlinelearningsystem.security.JwtService;
import com.onlinelearningsystem.token.Token;
import com.onlinelearningsystem.token.TokenRepository;
import com.onlinelearningsystem.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private HttpServletRequest request;

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

    @Override
    public PageResponse<AccountDto> findAll(int pageNumber,String sortBy,String sortOrder,String email) {
        int pageSize = 5;
        Pageable pageable;

        if (sortOrder.equalsIgnoreCase("asc")) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        }
        Page<AccountDto> accountPage = accountRepository.findAllAccount(pageable,email);
        PageResponse<AccountDto> pageResponse = new PageResponse<>();
        pageResponse.setItems(accountPage.getContent());
        pageResponse.setTotalElements(accountPage.getTotalElements());
        pageResponse.setTotalPages(accountPage.getTotalPages());
        pageResponse.setPageNo(pageNumber);
        return pageResponse;
    }

    @Override
    public void register(AddAccountDTO account) {
        Optional<RoleAccount> roleAccount =roleRepository.findById(account.getIdRole());
        Account addAccount= new Account();
        addAccount.setEmail(account.getEmail());
        addAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        addAccount.setBanned(false);
        addAccount.setRoleAccount(roleAccount.get());
        accountRepository.save(addAccount);
        String jwtToken = jwtService.generateToken(addAccount);
        jwtService.generateRefreshToken(addAccount);
        saveUserToken(addAccount,jwtToken);
    }

    @Override
    public List<String> getRoles() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            List<String> roleToken = (List<String>) session.getAttribute("authorities");
            System.out.println(roleToken);
            return roleToken != null ? roleToken : new ArrayList<>();
        }
        return new ArrayList<>();
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
