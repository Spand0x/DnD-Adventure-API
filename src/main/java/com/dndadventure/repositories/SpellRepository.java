package com.dndadventure.repositories;

import com.dndadventure.domain.dtos.SpellNameDto;
import com.dndadventure.domain.entities.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpellRepository extends JpaRepository<Spell, String> {

    @Query("SELECT new com.dndadventure.domain.dtos.SpellNameDto(s.uuid, s.name) FROM Spell s")
    List<SpellNameDto> getAllNames();
}
