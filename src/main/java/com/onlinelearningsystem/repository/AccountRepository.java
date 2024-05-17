package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
//    @Query(nativeQuery = true,
//            value = "SELECT count(*) FROM Account a JOIN role_account ra ON a.id_role = ra.id")
//    long countAllAccounts();
    @Query(nativeQuery = true,
            value = "SELECT a.id as idAccount, a.email as email, a.password as password, ra.role_name as roleName, a.is_banned as isBanned " +
                    "FROM Account a " +
                    "JOIN role_account ra ON a.id_role = ra.id " +
                    "ORDER BY a.id\n")
    Page<AccountDto> findAllAccount(Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT  a.id as idAccount , a.email as email, a.password as password, ra.role_name as roleName, a.is_banned as isBanned \n" +
                    "FROM Account a\n" +
                    "JOIN role_account ra ON a.id_role = ra.id\n" +
                    "WHERE a.email like CONCAT('%', ?1, '%')")
    List<AccountDto> getAccount(String email);

    Optional<Account> findOneByEmailAndPassword(String email, String password);
    Optional<Account> findByEmail(String email);

}
