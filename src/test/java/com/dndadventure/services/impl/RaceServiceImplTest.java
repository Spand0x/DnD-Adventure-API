package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.RaceBaseTraitCreateDto;
import com.dndadventure.domain.dtos.RaceCreateDto;
import com.dndadventure.domain.dtos.StatModifierDto;
import com.dndadventure.domain.entities.Race;
import com.dndadventure.domain.entities.StatModifier;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.RaceRepository;
import com.dndadventure.repositories.StatModifierRepository;
import com.dndadventure.services.RaceService;
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

public class RaceServiceImplTest {
    private static final String UUID = "1";
    private static final String UUID_TWO = "2";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private RaceRepository raceRepositoryMock;
    private StatModifierRepository statModifierRepositoryMock;
    private RaceService raceService;

    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        this.raceRepositoryMock = mock(RaceRepository.class);
        this.statModifierRepositoryMock = mock(StatModifierRepository.class);
        this.raceService = new RaceServiceImpl(this.raceRepositoryMock, this.statModifierRepositoryMock, modelMapper);
    }

    @Test
    public void givenValidRaceWhenCreateThenSuccessfullyPersist() {
        RaceCreateDto raceCreateDto = mockRaceCreateDto();
        StatModifier statModifier = TestUtils.mockStatModifier(UUID);
        StatModifier statModifierTwo = TestUtils.mockStatModifier(UUID_TWO);
        Race raceMock = TestUtils.mockRace();

        when(statModifierRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(statModifier));
        when(statModifierRepositoryMock.findById(UUID_TWO))
            .thenReturn(Optional.of(statModifierTwo));
        when(this.raceRepositoryMock.save(any(Race.class)))
            .thenReturn(raceMock);

        this.raceService.create(raceCreateDto);

        verify(this.raceRepositoryMock, times(1)).save(any(Race.class));
    }

    @Test
    public void givenValidRaceAndInvalidModifierThenThrowException() {
        RaceCreateDto raceCreateDto = mockRaceCreateDto();

        when(statModifierRepositoryMock.findById(anyString()))
            .thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("Modifier not found");
        this.raceService.create(raceCreateDto);

    }


    @Test
    public void givenInvalidUuidWhenGetByIdThenThrowException() {
        when(this.raceRepositoryMock.findById(UUID))
            .thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("Race not found.");

        this.raceService.getById(UUID);
    }

    @Test
    public void givenValidUuidWhenGetByIdThenReturnValidInstance() {
        Race mockRace = TestUtils.mockRace();
        when(this.raceRepositoryMock.findByIdWithModifiers(UUID))
            .thenReturn(Optional.of(mockRace));

        Race race = this.raceService.getById(UUID);

        verify(this.raceRepositoryMock, times(1)).findByIdWithModifiers(UUID);
        assertEquals(mockRace.getName(), race.getName());
        assertEquals(mockRace.getDescription(), race.getDescription());
    }

    @Test
    public void whenGetAllEmptyThenReturnEmptyList() {
        when(this.raceRepositoryMock.findAll())
            .thenReturn(Collections.emptyList());

        List<Race> races = this.raceService.getAll();

        assertEquals(0, races.size());
    }

    @Test
    public void whenGetAllThenReturnListWithRaces() {
        when(this.raceRepositoryMock.findAll())
            .thenReturn(Collections.singletonList(TestUtils.mockRace()));

        List<Race> races = this.raceService.getAll();

        assertEquals(1, races.size());
    }

    private RaceCreateDto mockRaceCreateDto() {
        RaceBaseTraitCreateDto advantage = new RaceBaseTraitCreateDto()
            .setName("Advantage 1")
            .setDescription("Advantage 1 Description")
            .setUuid(UUID);
        RaceBaseTraitCreateDto disadvantage = new RaceBaseTraitCreateDto()
            .setName("Disadvantage 1")
            .setDescription("Disadvantage 1 Description")
            .setUuid(UUID);
        StatModifierDto statModifier = new StatModifierDto()
            .setName(CharacterStatsEnum.DEXTERITY)
            .setValue((byte) 2)
            .setUuid(UUID);
        StatModifierDto statModifierTwo = new StatModifierDto()
            .setName(CharacterStatsEnum.STRENGTH)
            .setValue((byte) -2)
            .setUuid(UUID_TWO);

        return new RaceCreateDto()
            .setName("Human")
            .setDescription("Some Description")
            .setAdvantages(Collections.singletonList(advantage))
            .setDisadvantages(Collections.singletonList(disadvantage))
            .setModifiers(List.of(statModifier, statModifierTwo));
    }

}
