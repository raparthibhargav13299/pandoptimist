package com.stackroute.usermanagementservice1.repository;


import com.stackroute.usermanagementservice1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("FROM User WHERE email=:email1")
    User findByEmail(@Param("email1") String email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.role=?1 WHERE u.email=?2")
    void updateUserRoleByEmailId(String role1, String email1);

}