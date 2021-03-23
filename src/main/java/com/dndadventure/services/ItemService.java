package com.dndadventure.services;

import com.dndadventure.domain.dtos.WeaponCreateDto;

public interface ItemService {
    void createWeapon(WeaponCreateDto weaponCreateDto);
}
