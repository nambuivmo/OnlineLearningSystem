package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.dto.AccountDto;
import com.onlinelearningsystem.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(nativeQuery = true,
            value = "SELECT a.id as idAccount, a.email as email, ra.role_name as roleName, a.is_banned as isBanned " +
                    "FROM Account a " +
                    "JOIN role_account ra ON a.id_role = ra.id " +
                    "Where a.email like CONCAT('%', ?1, '%')"
                    )
    Page<AccountDto> findAllAccount(Pageable pageable,String email);

    Optional<Account> findOneByEmailAndPassword(String email, String password);
    Optional<Account> findByEmail(String email);

}
