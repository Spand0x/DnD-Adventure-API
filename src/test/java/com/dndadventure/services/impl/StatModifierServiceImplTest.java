package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.StatModifierCreateDto;
import com.dndadventure.domain.dtos.StatModifierDto;
import com.dndadventure.domain.entities.StatModifier;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.repositories.StatModifierRepository;
import com.dndadventure.services.StatModifierService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StatModifierServiceImplTest {
    private static final String UUID = "1";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private StatModifierRepository statModifierRepository;
    private StatModifierService statModifierService;

    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        this.statModifierRepository = mock(StatModifierRepository.class);
        this.statModifierService = new StatModifierServiceImpl(this.statModifierRepository, modelMapper);
    }

    @Test
    public void whenCreateValidThenSuccessfullyPersist() {
        StatModifierCreateDto statModifierCreateDto = mockStatModifierCreateDto();
        StatModifier statModifierMock = mockStatModifier();

        when(this.statModifierRepository.findByNameAndValue(statModifierCreateDto.getName(), statModifierCreateDto.getValue()))
            .thenReturn(Optional.empty());
        when(this.statModifierRepository.save(any(StatModifier.class))).thenReturn(statModifierMock);

        this.statModifierService.create(statModifierCreateDto);

        verify(statModifierRepository, times(1)).save(any(StatModifier.class));
    }

    @Test
    public void whenCreateAndModifierExistsThenThrowException() {
        StatModifierCreateDto statModifierCreateDto = mockStatModifierCreateDto();
        when(this.statModifierRepository.findByNameAndValue(statModifierCreateDto.getName(), statModifierCreateDto.getValue()))
            .thenReturn(Optional.of(mockStatModifier()));

        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("This modifier already exists");

        this.statModifierService.create(statModifierCreateDto);
    }

    @Test
    public void whenGetAllEmptyThenReturnEmptyList() {
        when(this.statModifierRepository.findAll())
            .thenReturn(Collections.emptyList());

        List<StatModifierDto> stats = this.statModifierService.getAll();

        assertEquals(0, stats.size());
    }

    @Test
    public void whenGetAllThenReturnListWithStatsModifiers() {
        StatModifier statModifier = mockStatModifier();
        when(this.statModifierRepository.findAll())
            .thenReturn(Collections.singletonList(statModifier));

        List<StatModifierDto> stats = this.statModifierService.getAll();

        assertEquals(1, stats.size());
        assertEquals(statModifier.getName(), stats.get(0).getName());
        assertEquals(statModifier.getValue(), stats.get(0).getValue());
        assertEquals(statModifier.getUuid(), stats.get(0).getUuid());
    }

    private StatModifier mockStatModifier() {
        return (StatModifier) new StatModifier()
            .setName(CharacterStatsEnum.STRENGTH)
            .setValue((byte) 2)
            .setUuid(UUID);
    }

    private StatModifierCreateDto mockStatModifierCreateDto() {
        return new StatModifierCreateDto()
            .setName(CharacterStatsEnum.STRENGTH)
            .setValue((byte) 2);
    }
}
