package com.scaler.ecommerceuserservice.repositories;

import com.scaler.ecommerceuserservice.models.Token;
import com.scaler.ecommerceuserservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Override
    Token save(Token token);

    //List<Token> findByUserAndIsDeletedAndExpiryAtBefore(User user, Boolean isdeleted, LocalDate date);

    /*@Query("from Token t left join t.user where t.user.id=:id and t.isDeleted=FALSE and t.expiryAt <= :expiryDate")
    List<Token> findValidToken(@Param("id") Long id, @Param("expiryDate") LocalDate expiryDate);*/

    Optional<Token> findByValueAndIsDeleted(String token, boolean isDeleted);

    Optional<Token> findByValueAndIsDeletedAndExpiryAtGreaterThan(String value, boolean isDeleted, LocalDate expiry);
}