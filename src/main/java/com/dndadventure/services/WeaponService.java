package com.dndadventure.services;

import com.dndadventure.domain.dtos.WeaponCreateDto;
import com.dndadventure.domain.dtos.WeaponDetailsDto;
import com.dndadventure.domain.entities.items.Weapon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface WeaponService {
    void create(WeaponCreateDto weaponCreateDto);

    Page<WeaponDetailsDto> getAllByPages(String searchValue, Pageable pageable);

    Set<Weapon> getWeapons(List<String> weaponUuids);
}
