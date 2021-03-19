package com.dndadventure.repositories;

import com.dndadventure.domain.entities.StatsModifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsModifierRepository extends JpaRepository<StatsModifier, String> {
}
