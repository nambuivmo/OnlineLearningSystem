package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(nativeQuery = true,
            value = "SELECT  a.id_account as idAccount , a.email as email, a.password as password, ra.role_name as roleName, a.is_banned as isBanned \n" +
                    "FROM Account a \n" +
                    "JOIN role_account ra ON a.role_id = ra.id_role_account;")
    List<AccountDto> findAllAccount();

    @Query(nativeQuery = true,
            value = "SELECT  a.id_account as idAccount , a.email as email, a.password as password, ra.role_name as roleName, a.is_banned as isBanned \n" +
                    "FROM Account a\n" +
                    "JOIN role_account ra ON a.role_id = ra.id_role_account\n" +
                    "WHERE a.email like CONCAT('%', ?1, '%')")
    List<AccountDto> getAccount(String email);
}
