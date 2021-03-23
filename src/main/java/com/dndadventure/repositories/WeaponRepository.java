package com.dndadventure.repositories;

import com.dndadventure.domain.entities.items.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, String> {

}
