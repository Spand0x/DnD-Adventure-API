package com.dndadventure.repositories;

import com.dndadventure.domain.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceRepository extends JpaRepository<Race, String> {

    @Query("SELECT r FROM Race r " +
        "JOIN r.modifiers " +
        "WHERE r.uuid = ?1")
    Optional<Race> findByIdWithModifiers(@Param("uuid") String uuid);
}
