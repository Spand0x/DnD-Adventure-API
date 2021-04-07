package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.CharacterCreateDto;
import com.dndadventure.domain.dtos.CharacterHpChangeDto;
import com.dndadventure.domain.dtos.CharacterStatCreateDto;
import com.dndadventure.domain.dtos.CharacterViewDto;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.CharacterStat;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.CharacterRepository;
import com.dndadventure.services.*;
import com.dndadventure.testutils.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CharacterServiceImplTest {
    private static final String UUID = "aaa-aaa-aaa";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CharacterRepository characterRepositoryMock;
    private SpellService spellServiceMock;
    private RaceService raceServiceMock;
    private CharacterClassService characterClassServiceMock;
    private WeaponService weaponServiceMock;
    private UserService userServiceMock;

    private CharacterService characterService;

    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        this.characterRepositoryMock = mock(CharacterRepository.class);
        this.spellServiceMock = mock(SpellService.class);
        this.raceServiceMock = mock(RaceService.class);
        this.characterClassServiceMock = mock(CharacterClassService.class);
        this.weaponServiceMock = mock(WeaponService.class);
        this.userServiceMock = mock(UserService.class);
        this.characterService = new CharacterServiceImpl(
            characterRepositoryMock,
            spellServiceMock,
            raceServiceMock,
            characterClassServiceMock,
            weaponServiceMock,
            userServiceMock,
            modelMapper);
    }

    @Test
    public void givenValidDtoAndUserWhenCreateThenCreateCharacter() {
        CharacterCreateDto characterCreateDto = mockCharacterCreateDto();
        when(this.raceServiceMock.getById(UUID))
            .thenReturn(TestUtils.mockRace());
        when(this.characterClassServiceMock.getById(UUID))
            .thenReturn(TestUtils.mockCharacterClass());
        when(this.spellServiceMock.getSpells(Collections.singletonList(UUID)))
            .thenReturn(Collections.singleton(TestUtils.mockSpell()));
        when(this.weaponServiceMock.getWeapons(Collections.singletonList(UUID)))
            .thenReturn(Collections.singleton(TestUtils.mockWeapon()));

        this.characterService.create(characterCreateDto, TestUtils.mockUser());

        verify(this.raceServiceMock).getById(UUID);
        verify(this.characterClassServiceMock).getById(UUID);
        verify(this.spellServiceMock).getSpells(anyList());
        verify(this.weaponServiceMock).getWeapons(anyList());
        verify(this.characterRepositoryMock).save(any(Character.class));
        verify(this.userServiceMock).addCharacter(any(User.class), any(Character.class));
    }

    @Test
    public void givenValidUuidWhenGetThenReturnCharacterViewDto() {
        Character character = TestUtils.mockCharacter();
        when(characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));

        CharacterViewDto result = this.characterService.get(UUID);

        assertEquals(character.getName(), result.getName());
        assertEquals(character.getDescription(), result.getDescription());
        assertEquals(character.getArmor(), result.getArmor());
        assertEquals(character.getClazz(), result.getClazz());
        assertEquals(character.getRace(), result.getRace());
        assertEquals(character.getGold(), result.getGold());
        assertEquals(character.getInitiative(), result.getInitiative());
        assertEquals(character.getLevel(), result.getLevel());
        assertEquals(character.getMaxHitPoints(), result.getMaxHitPoints());
        assertEquals(character.getCurrentHitPoints(), result.getCurrentHitPoints());
        assertEquals(1, result.getWeapons().size());
        assertEquals(1, result.getSpells().size());
        assertEquals(6, result.getStats().size());
        assertFalse(result.isDead());


    }

    @Test
    public void givenInvalidUuidWhenGetThenThrowException() {
        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.empty());

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("Character not found!");

        this.characterService.get(UUID);
    }

    @Test
    public void givenValidDtoWhenChangeHpThenChangeHp() {
        CharacterHpChangeDto characterDto = new CharacterHpChangeDto()
            .setUuid(UUID)
            .setCurrentHitPoints(3);
        Character character = TestUtils.mockCharacter();

        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));
        when(this.characterRepositoryMock.save(character))
            .thenReturn(character);

        CharacterViewDto result = this.characterService.changeHp(characterDto);

        verify(this.characterRepositoryMock, times(1)).save(any(Character.class));
        verify(this.characterRepositoryMock, times(1)).findById(anyString());
        assertEquals(characterDto.getCurrentHitPoints(), result.getCurrentHitPoints());
        assertFalse(result.isDead());
    }

    @Test
    public void givenNegativeHpWhenChangeHpThenChangeHpAndSetDead() {
        CharacterHpChangeDto characterDto = new CharacterHpChangeDto()
            .setUuid(UUID)
            .setCurrentHitPoints(-3);
        Character character = TestUtils.mockCharacter();

        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));
        when(this.characterRepositoryMock.save(character))
            .thenReturn(character);

        CharacterViewDto result = this.characterService.changeHp(characterDto);

        verify(this.characterRepositoryMock, times(1)).save(any(Character.class));
        verify(this.characterRepositoryMock, times(1)).findById(anyString());
        assertEquals(Integer.valueOf(0), result.getCurrentHitPoints());
        assertTrue(result.isDead());
    }

    @Test
    public void givenZeroHpWhenChangeHpThenChangeHpAndSetDead() {
        CharacterHpChangeDto characterDto = new CharacterHpChangeDto()
            .setUuid(UUID)
            .setCurrentHitPoints(0);
        Character character = TestUtils.mockCharacter();

        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));
        when(this.characterRepositoryMock.save(character))
            .thenReturn(character);

        CharacterViewDto result = this.characterService.changeHp(characterDto);

        verify(this.characterRepositoryMock, times(1)).save(any(Character.class));
        verify(this.characterRepositoryMock, times(1)).findById(anyString());
        assertEquals(Integer.valueOf(0), result.getCurrentHitPoints());
        assertTrue(result.isDead());
    }

    @Test
    public void givenBiggerHpThenMaxHpWhenChangeHpThenChangeHp() {
        Character character = TestUtils.mockCharacter();
        CharacterHpChangeDto characterDto = new CharacterHpChangeDto()
            .setUuid(UUID)
            .setCurrentHitPoints(character.getMaxHitPoints() + 1);

        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));
        when(this.characterRepositoryMock.save(character))
            .thenReturn(character);

        CharacterViewDto result = this.characterService.changeHp(characterDto);

        verify(this.characterRepositoryMock, times(1)).save(any(Character.class));
        verify(this.characterRepositoryMock, times(1)).findById(anyString());
        assertEquals(character.getMaxHitPoints(), result.getCurrentHitPoints());
        assertFalse(result.isDead());
    }

    @Test
    public void givenPositiveHpWhenChangeHpThenChangeHpAndSetAlive() {
        CharacterHpChangeDto characterDto = new CharacterHpChangeDto()
            .setUuid(UUID)
            .setCurrentHitPoints(3);
        Character character = TestUtils.mockCharacter();

        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));
        when(this.characterRepositoryMock.save(character))
            .thenReturn(character);

        CharacterViewDto result = this.characterService.changeHp(characterDto);

        verify(this.characterRepositoryMock, times(1)).save(any(Character.class));
        verify(this.characterRepositoryMock, times(1)).findById(anyString());
        assertEquals(character.getCurrentHitPoints(), result.getCurrentHitPoints());
        assertFalse(result.isDead());
    }

    @Test
    public void givenInvalidUuidWhenChangeHpThenThrowException() {
        when(this.characterRepositoryMock.findById(anyString()))
            .thenReturn(Optional.empty());

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("Character not found!");

        this.characterService.changeHp(new CharacterHpChangeDto());
    }

    @Test
    public void givenValidUuidWhenCastSpellThenChangeAvailableSpellCharges() {
        Character character = TestUtils.mockCharacter()
            .setMaxSpellCharges(3.0d)
            .setAvailableSpellCharges(3.0d);
        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));
        when(this.characterRepositoryMock.save(any(Character.class)))
            .thenReturn(character);

        CharacterViewDto result = this.characterService.castSpell(UUID);

        verify(this.characterRepositoryMock, times(1)).save(any(Character.class));
        assertEquals(Double.valueOf(2.0), result.getAvailableSpellCharges());
    }

    @Test
    public void givenInvalidUuidWhenCastSpellThenThrowException() {
        when(this.characterRepositoryMock.findById(anyString()))
            .thenReturn(Optional.empty());

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("Character not found!");

        this.characterService.castSpell(UUID);
    }

    @Test
    public void givenValidUuidAndAvailableChargesAreZeroWhenCastSpellThenThrowException() {
        Character character = TestUtils.mockCharacter()
            .setMaxSpellCharges(3.0d)
            .setAvailableSpellCharges(0.0d);

        when(this.characterRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(character));

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("You don't have available spell charges!");

        this.characterService.castSpell(UUID);
    }



    private CharacterCreateDto mockCharacterCreateDto() {
        return new CharacterCreateDto()
            .setName("Name")
            .setDescription("Description")
            .setGold(10)
            .setRace(UUID)
            .setClazz(UUID)
            .setStats(mockCharacterStatCreateDto())
            .setSpells(Collections.singletonList(UUID))
            .setWeapons(Collections.singletonList(UUID));

    }

    private List<CharacterStatCreateDto> mockCharacterStatCreateDto() {
        CharacterStatCreateDto str = new CharacterStatCreateDto()
            .setName(CharacterStatsEnum.STRENGTH)
            .setValue((byte) 17);
        CharacterStatCreateDto dex = new CharacterStatCreateDto()
            .setName(CharacterStatsEnum.DEXTERITY)
            .setValue((byte) 12);
        CharacterStatCreateDto con = new CharacterStatCreateDto()
            .setName(CharacterStatsEnum.CONSTITUTION)
            .setValue((byte) 9);
        CharacterStatCreateDto cha = new CharacterStatCreateDto()
            .setName(CharacterStatsEnum.CHARISMA)
            .setValue((byte) 4);
        CharacterStatCreateDto wis = new CharacterStatCreateDto()
            .setName(CharacterStatsEnum.WISDOM)
            .setValue((byte) 10);
        CharacterStatCreateDto intel = new CharacterStatCreateDto()
            .setName(CharacterStatsEnum.INTELLIGENCE)
            .setValue((byte) 20);
        return List.of(str, dex, con, cha, wis, intel);
    }
}
