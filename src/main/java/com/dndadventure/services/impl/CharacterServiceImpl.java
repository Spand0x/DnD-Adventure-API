package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.CharacterCreateDto;
import com.dndadventure.domain.dtos.CharacterHpChangeDto;
import com.dndadventure.domain.dtos.CharacterViewDto;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.*;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.CharacterRepository;
import com.dndadventure.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final SpellService spellService;
    private final RaceService raceService;
    private final CharacterClassService classService;
    private final WeaponService weaponService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CharacterServiceImpl(CharacterRepository characterRepository,
                                SpellService spellService,
                                RaceService raceService,
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
        Race race = this.raceService.getById(characterCreateDto.getRace());
        CharacterClass characterClass = this.classService.getById(characterCreateDto.getClazz());

        character.setRace(race);
        character.setClazz(characterClass);
        character.setSpells(this.spellService.getSpells(characterCreateDto.getSpells()));
        character.setWeapons(this.weaponService.getWeapons(characterCreateDto.getWeapons()));

        setFirstLevelHealth(character);
        setInitiative(character);
        modifyStats(character, race.getModifiers());

        character.setLevel((byte) 1)
            //To be implemented Armor, Campaign.
            .setArmor(10)
            .setUser(user)
            .setCurrentHitPoints(character.getMaxHitPoints());

        if (characterClass.getMaxSpellCharges() != null && characterClass.getMaxSpellCharges() != 0) {
            character
                .setMaxSpellCharges(characterClass.getMaxSpellCharges())
                .setAvailableSpellCharges(characterClass.getMaxSpellCharges());
        }

        this.characterRepository.save(character);
        this.userService.addCharacter(user, character);
    }

    @Override
    public CharacterViewDto get(String uuid) {
        Character character = this.characterRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("Character not found!"));
        CharacterViewDto characterViewDto = this.modelMapper.map(character, CharacterViewDto.class);
        return characterViewDto;
    }

    @Override
    public void changeHp(CharacterHpChangeDto characterHpChangeDto) {
        Character character = this.characterRepository.findById(characterHpChangeDto.getUuid())
            .orElseThrow(() -> new NotFoundException("Character not found!"));
        character.setCurrentHitPoints(characterHpChangeDto.getCurrentHitPoints());
        this.characterRepository.save(character);
    }

    @Override
    public void castSpell(String characterUuid) {
        Character character = this.characterRepository.findById(characterUuid)
            .orElseThrow(() -> new NotFoundException("Character not found!"));
        if (character.getAvailableSpellCharges() == 0) {
            return;
        } else if (character.getAvailableSpellCharges() < 0) {
            throw new IllegalArgumentException("You don't have available spell charges!");
        }
        character.setAvailableSpellCharges(character.getAvailableSpellCharges() - 1);
        this.characterRepository.save(character);
    }

    private void setFirstLevelHealth(Character character) {
        Integer hitPoints = character.getClazz().getHitPointsDice().value;

        Byte constitution = character.getStats()
            .stream()
            .filter(s -> s.getName().equals(CharacterStatsEnum.CONSTITUTION))
            .findFirst().get().getValue();
        StatModifier constitutionModifier = character.getRace().getModifiers()
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
        StatModifier dexterityModifier = character.getRace().getModifiers()
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

    private void modifyStats(Character character, List<StatModifier> modifiers) {
        character.getStats().forEach(s -> {
            modifiers.forEach(m -> {
                if (s.getName().equals(m.getName())) {
                    s.setValue((byte) (s.getValue() + m.getValue()));
                }
            });
        });
    }
}
