package com.dndadventure.repositories;

import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.domain.entities.constants.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    Optional<UserRole> findByRole(UserRoleEnum role);
}
