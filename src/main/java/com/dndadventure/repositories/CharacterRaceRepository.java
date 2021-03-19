package com.dndadventure.repositories;

import com.dndadventure.domain.entities.CharacterRace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRaceRepository extends JpaRepository<CharacterRace, String> {

}
