package com.onlinelearningsystem.token;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join Account a\s
      on t.account.idAccount = a.idAccount\s
      where a.idAccount = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(long id);

    Optional<Token> findByToken(String token);
}
