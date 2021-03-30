package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.CharacterCreateDto;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.CharacterClass;
import com.dndadventure.domain.entities.StatsModifier;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.repositories.CharacterRepository;
import com.dndadventure.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final SpellService spellService;
    private final CharacterRaceService raceService;
    private final CharacterClassService classService;
    private final WeaponService weaponService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                SpellService spellService,
                                CharacterRaceService raceService,
                                CharacterClassService classService,
                                WeaponService weaponService,
                                UserService userService,
                                ModelMapper modelMapper) {
        this.characterRepository = characterRepository;
        this.spellService = spellService;
        this.raceService = raceService;
        this.classService = classService;
        this.weaponService = weaponService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CharacterCreateDto characterCreateDto, User user) {
        Character character = this.modelMapper.map(characterCreateDto, Character.class);
        character.setCharacterRace(this.raceService.getById(characterCreateDto.getRace()));
        CharacterClass characterClass = this.classService.getById(characterCreateDto.getClazz());

        character.setClazz(characterClass);
        character.setSpells(this.spellService.getSpells(characterCreateDto.getSpells()));
        character.setWeapons(this.weaponService.getWeapons(characterCreateDto.getWeapons()));

        setFirstLevelHealth(character);
        setInitiative(character);

        character.setLevel((byte) 1)
            //To be implemented Armor, Campaign.
            .setArmor(10)
            .setUser(user)
            .setCurrentHitPoints(character.getMaxHitPoints());

        if (characterClass.getMaxSpellCharges() != null && characterClass.getMaxSpellCharges() != 0) {
            character
                .setMaxSpellCharges(characterClass.getMaxSpellCharges())
                .setUsedSpellCharges(0.0d);
        }

        this.characterRepository.save(character);
        this.userService.addCharacter(user, character);
    }

    private void setFirstLevelHealth(Character character) {
        Integer hitPoints = character.getClazz().getHitPointsDice().value;

        Byte constitution = character.getStats()
            .stream()
            .filter(s -> s.getName().equals(CharacterStatsEnum.CONSTITUTION))
            .findFirst().get().getValue();
        StatsModifier constitutionModifier = character.getCharacterRace().getModifiers()
            .stream()
            .filter(s -> s.getName().equals(CharacterStatsEnum.CONSTITUTION))
            .findFirst()
            .orElse(null);

        if (constitutionModifier != null) {
            constitution = (byte) (constitution + constitutionModifier.getValue());
        }
        hitPoints += mapStatToModifier(constitution);

        character.setMaxHitPoints(hitPoints);
    }

    private void setInitiative(Character character) {
        Byte dexterity = character.getStats()
            .stream()
            .filter(s -> s.getName().equals(CharacterStatsEnum.DEXTERITY))
            .findFirst().get().getValue();
        StatsModifier dexterityModifier = character.getCharacterRace().getModifiers()
            .stream()
            .filter(s -> s.getName().equals(CharacterStatsEnum.DEXTERITY))
            .findFirst()
            .orElse(null);
        if (dexterityModifier != null) {
            dexterity = (byte) (dexterity + dexterityModifier.getValue());
        }
        character.setInitiative(mapStatToModifier(dexterity));
    }

    private Byte mapStatToModifier(Byte stat) {
        return (byte) Math.floorDiv(stat - 10, 2);
    }
}
