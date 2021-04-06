package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.SpellNameDto;
import com.dndadventure.domain.dtos.WeaponCreateDto;
import com.dndadventure.domain.dtos.WeaponDetailsDto;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.domain.entities.Weapon;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.WeaponRepository;
import com.dndadventure.services.SpellService;
import com.dndadventure.services.WeaponService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WeaponServiceImpl implements WeaponService {
    private final WeaponRepository weaponRepository;
    private final SpellService spellService;
    private final ModelMapper modelMapper;

    public WeaponServiceImpl(WeaponRepository weaponRepository,
                             SpellService spellService,
                             ModelMapper modelMapper) {
        this.weaponRepository = weaponRepository;
        this.spellService = spellService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(WeaponCreateDto weaponCreateDto) {
        Weapon weapon = this.modelMapper.map(weaponCreateDto, Weapon.class);
        if (weaponCreateDto.getSpell() != null) {
            Spell spell = this.spellService.getSpell(weaponCreateDto.getSpell().getUuid());
            weapon.setSpell(spell);
        }
        this.weaponRepository.save(weapon);
    }

    @Override
    public Page<WeaponDetailsDto> getAllByPages(String searchValue, Pageable pageable) {
        String value = searchValue.toLowerCase().trim();
        Page<Weapon> weapons = this.weaponRepository.findAllContainingValue(value, pageable);
        return weapons.map(w -> {
            WeaponDetailsDto weaponDetailsDto = this.modelMapper.map(w, WeaponDetailsDto.class);
            if (w.getSpell() != null) {
                weaponDetailsDto.setSpell(this.modelMapper.map(w.getSpell(), SpellNameDto.class));
            }
            return weaponDetailsDto;
        });
    }

    @Override
    public Set<Weapon> getWeapons(List<String> weaponUuids) {
        Set<Weapon> weapons = new HashSet<>();
        weaponUuids.forEach(uuid -> weapons.add(this.weaponRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("Weapon not found."))));
        return weapons;
    }
}
