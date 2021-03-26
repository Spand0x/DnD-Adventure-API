package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.WeaponCreateDto;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.domain.entities.items.Weapon;
import com.dndadventure.repositories.WeaponRepository;
import com.dndadventure.services.ItemService;
import com.dndadventure.services.SpellService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final WeaponRepository weaponRepository;
    private final SpellService spellService;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(WeaponRepository weaponRepository,
                           SpellService spellService,
                           ModelMapper modelMapper) {
        this.weaponRepository = weaponRepository;
        this.spellService = spellService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createWeapon(WeaponCreateDto weaponCreateDto) {
        Weapon weapon = this.modelMapper.map(weaponCreateDto, Weapon.class);
        if (weaponCreateDto.getSpell() != null) {
            Spell spell = this.spellService.getSpell(weaponCreateDto.getSpell());
            weapon.setSpell(spell);
        }
        this.weaponRepository.save(weapon);
    }
}
