package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.WeaponCreateDto;
import com.dndadventure.domain.entities.items.Weapon;
import com.dndadventure.repositories.WeaponRepository;
import com.dndadventure.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final WeaponRepository weaponRepository;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(WeaponRepository weaponRepository, ModelMapper modelMapper) {
        this.weaponRepository = weaponRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createWeapon(WeaponCreateDto weaponCreateDto) {
        Weapon weapon = this.modelMapper.map(weaponCreateDto, Weapon.class);
        this.weaponRepository.save(weapon);
    }
}
