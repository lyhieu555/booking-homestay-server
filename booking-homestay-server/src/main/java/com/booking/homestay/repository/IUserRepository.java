package com.booking.homestay.repository;


import com.booking.homestay.model.HomeStay;
import com.booking.homestay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Optional<User> findByUserNameAndStatusTrue(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhone(String phone);

    List<User> findByHomeStay(HomeStay homeStay);

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Employee' and u.status = true ")
    List<User> findByEmployeeNotLock();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Employee' and u.status = false ")
    List<User> findByEmployeeLock();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Employee' and u.status = true and u.homeStay is null ")
    List<User> findByEmployeeCheck();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Member' and u.status = true ")
    List<User> findByMemberNotLock();

    @Query(value = "SELECT u FROM User u WHERE u.role = 'Member' and u.status = false ")
    List<User> findByMemberLock();

}
