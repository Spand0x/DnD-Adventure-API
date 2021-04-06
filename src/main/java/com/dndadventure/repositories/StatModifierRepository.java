package com.dndadventure.repositories;

import com.dndadventure.domain.entities.StatModifier;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatModifierRepository extends JpaRepository<StatModifier, String> {
    Optional<StatModifier> findByNameAndValue(CharacterStatsEnum name, Byte value);
}
