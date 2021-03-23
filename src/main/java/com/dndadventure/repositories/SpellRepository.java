package com.dndadventure.repositories;

import com.dndadventure.domain.entities.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepository extends JpaRepository<Spell, String> {
}
