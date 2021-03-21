package com.dndadventure.repositories;

import com.dndadventure.domain.entities.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, String> {
}
