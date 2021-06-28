package com.booking.homestay.repository;


import com.booking.homestay.model.VerificationTokenPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVerificationTokenPasswordRepository extends JpaRepository<VerificationTokenPassword, Long> {
    Optional<VerificationTokenPassword> findByToken(String token);

    List<VerificationTokenPassword> findByUser_Id(Long id);
}
