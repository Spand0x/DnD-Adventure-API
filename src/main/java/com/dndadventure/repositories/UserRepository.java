package com.dndadventure.repositories;

import com.dndadventure.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username, String email);

    @Query("SELECT DISTINCT u FROM User u " +
        "WHERE LOWER(u.username) LIKE %:value% " +
        "OR LOWER(u.email) LIKE %:value%")
    Page<User> findAllContainingValue(@Param("value") String searchValue, Pageable pageable);
}
