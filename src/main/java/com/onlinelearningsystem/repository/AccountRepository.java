package com.onlinelearningsystem.repository;

import com.onlinelearningsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
