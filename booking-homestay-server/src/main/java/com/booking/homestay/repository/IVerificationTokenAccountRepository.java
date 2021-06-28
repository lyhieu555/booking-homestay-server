package com.booking.homestay.repository;

import com.booking.homestay.model.VerificationTokenAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVerificationTokenAccountRepository extends JpaRepository<VerificationTokenAccount, Long> {
    Optional<VerificationTokenAccount> findByToken(String token);
}
