package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.CharacterClassCreateDto;
import com.dndadventure.domain.entities.CharacterClass;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.DiceTypeEnum;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.CharacterClassRepository;
import com.dndadventure.services.CharacterClassService;
import com.dndadventure.testutils.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CharacterClassServiceImplTest {
    private static final String UUID = "aaa-aaa-aaa";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    private CharacterClassRepository characterClassRepositoryMock;
    private CharacterClassService characterClassService;

    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        this.characterClassRepositoryMock = mock(CharacterClassRepository.class);
        this.characterClassService = new CharacterClassServiceImpl(this.characterClassRepositoryMock, modelMapper);
    }

    @Test
    public void givenValidCharacterClassWhenCreateThenSuccessfullyPersist() {
        CharacterClassCreateDto characterClassCreateDto = new CharacterClassCreateDto()
            .setName("Mage")
            .setDescription("Some Description")
            .setHitPointsDice(DiceTypeEnum.D8)
            .setSavingThrowStat(CharacterStatsEnum.DEXTERITY);
        CharacterClass characterClassMock = new CharacterClass()
            .setName("Mage")
            .setDescription("Some Description")
            .setHitPointsDice(DiceTypeEnum.D8)
            .setSavingThrowStat(CharacterStatsEnum.DEXTERITY)
            .setMaxSpellCharges(0.0d)
            .setSpellChargesPerLevel(0.0d);

        when(this.characterClassRepositoryMock.save(any(CharacterClass.class)))
            .thenReturn((CharacterClass) characterClassMock.setUuid(UUID));

        this.characterClassService.create(characterClassCreateDto);

       verify(this.characterClassRepositoryMock, times(1)).save(any(CharacterClass.class));
    }


    @Test
    public void givenInvalidUuidWhenGetByIdThenThrowException() {
        when(this.characterClassRepositoryMock.findById(UUID))
            .thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("Character Class not found.");

        this.characterClassService.getById(UUID);
    }

    @Test
    public void givenValidUuidWhenGetByIdThenReturnValidInstance() {
        CharacterClass mockedCharacterClass = TestUtils.mockCharacterClass();
        when(this.characterClassRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(mockedCharacterClass));

        CharacterClass characterClassResult = this.characterClassService.getById(UUID);

        verify(this.characterClassRepositoryMock, times(1)).findById(UUID);
        assertEquals(mockedCharacterClass.getName(), characterClassResult.getName());
        assertEquals(mockedCharacterClass.getDescription(), characterClassResult.getDescription());
        assertEquals(mockedCharacterClass.getHitPointsDice(), characterClassResult.getHitPointsDice());
        assertEquals(mockedCharacterClass.getSavingThrowStat(), characterClassResult.getSavingThrowStat());
        assertEquals(mockedCharacterClass.getMaxSpellCharges(), characterClassResult.getMaxSpellCharges());
        assertEquals(mockedCharacterClass.getSpellChargesPerLevel(), characterClassResult.getSpellChargesPerLevel());
    }

    @Test
    public void whenGetAllEmptyThenReturnEmptyList() {
        when(this.characterClassRepositoryMock.findAll())
            .thenReturn(Collections.emptyList());

        List<CharacterClass> characterClasses = this.characterClassService.getAll();

        assertEquals(0, characterClasses.size());
    }

    @Test
    public void whenGetAllThenReturnListWithCharacterClasses() {
        when(this.characterClassRepositoryMock.findAll())
            .thenReturn(Collections.singletonList(TestUtils.mockCharacterClass()));

        List<CharacterClass> characterClasses = this.characterClassService.getAll();

        assertEquals(1, characterClasses.size());
    }
}
