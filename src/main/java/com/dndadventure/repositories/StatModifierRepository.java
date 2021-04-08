package com.dndadventure.repositories;

import com.dndadventure.domain.entities.StatModifier;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatModifierRepository extends JpaRepository<StatModifier, String> {
    Optional<StatModifier> findByNameAndValue(CharacterStatsEnum name, Byte value);

    @Query("SELECT DISTINCT m FROM StatModifier m " +
        "WHERE LOWER(m.name) LIKE concat('%', :value, '%') " +
        "OR LOWER(m.value) LIKE concat('%', :value, '%')")
    Page<StatModifier> findAllContainingValue(@Param("value") String searchValue, Pageable pageable);
}
