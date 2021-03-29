package com.dndadventure.repositories;

import com.dndadventure.domain.dtos.SpellNameDto;
import com.dndadventure.domain.entities.Spell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, String> {

    @Query("SELECT new com.dndadventure.domain.dtos.SpellNameDto(s.uuid, s.name) FROM Spell s WHERE s.template = true")
    List<SpellNameDto> getAllNames();

    @Query("SELECT s FROM Spell s " +
        "WHERE LOWER(s.name) LIKE %:value% " +
        "OR LOWER(s.level) LIKE %:value% " +
        "OR LOWER(s.damageDice) LIKE %:value% " +
        "OR LOWER(s.damageModifier) LIKE %:value% " +
        "OR LOWER(s.effect) LIKE %:value% " +
        "OR LOWER(s.notes) LIKE %:value% " +
        "OR LOWER(s.range) LIKE %:value% " +
        "OR LOWER(s.castingType) LIKE %:value% " +
        "OR LOWER(s.durationType) LIKE %:value% " +
        "OR LOWER(s.duration) LIKE %:value% " +
        "OR LOWER(s.durationUnit) LIKE %:value% " +
        "AND s.template = true")
    Page<Spell> findAllContainingValue(@Param("value") String value, Pageable pageable);
}
