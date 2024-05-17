package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.model.Account;
import com.onlinelearningsystem.model.RoleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleAccount,Long> {

    @Query(nativeQuery = true,
            value = "Select role_name")
    Account findByAccountId(long id);

    Optional<RoleAccount> findById(Long idRole);

}
