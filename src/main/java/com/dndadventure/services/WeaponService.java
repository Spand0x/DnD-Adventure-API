package com.dndadventure.services;

import com.dndadventure.domain.dtos.WeaponCreateDto;
import com.dndadventure.domain.dtos.WeaponDetailsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WeaponService {
    void create(WeaponCreateDto weaponCreateDto);

    Page<WeaponDetailsDto> getAllByPages(String searchValue, Pageable pageable);
}
