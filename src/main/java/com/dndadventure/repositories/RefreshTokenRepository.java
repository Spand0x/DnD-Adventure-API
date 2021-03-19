package com.dndadventure.repositories;

import com.dndadventure.domain.entities.RefreshToken;
import com.dndadventure.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findFirstByValue(String value);

    Iterable<RefreshToken> findAllByUser(User user);
}
