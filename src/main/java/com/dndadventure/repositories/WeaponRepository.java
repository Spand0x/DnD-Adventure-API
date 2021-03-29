package com.dndadventure.repositories;

import com.dndadventure.domain.entities.items.Weapon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, String> {

    @Query("SELECT w FROM Weapon w " +
        "LEFT JOIN Spell s ON w.spell.uuid = s.uuid " +
        "WHERE LOWER(w.name) LIKE %:value% " +
        "OR LOWER(w.rarity) LIKE %:value% " +
        "OR LOWER(w.weaponType) LIKE %:value% " +
        "OR LOWER(w.attackType) LIKE %:value% " +
        "OR LOWER(w.damageDice) LIKE %:value% " +
        "OR LOWER(w.damageModifier) LIKE %:value%")
    Page<Weapon> findAllContainingValue(@Param("value") String searchValue, Pageable pageable);

}
