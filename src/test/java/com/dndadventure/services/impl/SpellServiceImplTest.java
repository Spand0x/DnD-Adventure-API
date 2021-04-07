package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.SpellCreateDto;
import com.dndadventure.domain.dtos.SpellDetailsDto;
import com.dndadventure.domain.entities.Spell;
import com.dndadventure.domain.entities.constants.SpellCastingTypeEnum;
import com.dndadventure.domain.entities.constants.SpellDurationTypeEnum;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.SpellRepository;
import com.dndadventure.services.SpellService;
import com.dndadventure.testutils.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SpellServiceImplTest {
    private static final String UUID = "aaa-aaa-aaa";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private SpellRepository spellRepositoryMock;
    private SpellService spellService;

    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        this.spellRepositoryMock = mock(SpellRepository.class);
        this.spellService = new SpellServiceImpl(this.spellRepositoryMock, modelMapper);
    }

    @Test
    public void givenValidSpellWhenCreateThenSuccessfullyPersist() {
        SpellCreateDto spellCreateDto = mockSpellCreateDto();
        Spell spell = TestUtils.mockSpell();
        when(this.spellRepositoryMock.save(any(Spell.class)))
            .thenReturn(spell);

        this.spellService.create(spellCreateDto);

        verify(this.spellRepositoryMock, times(1)).save(any(Spell.class));
    }

    @Test
    public void givenValidUuidWhenGetSpellDetailsThenReturnSpellDetails() {
        Spell spell = TestUtils.mockSpell();
        when(this.spellRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(spell));

        SpellDetailsDto spellDetails = this.spellService.getSpellDetails(UUID);

        assertEquals(spell.getName(), spellDetails.getName());
        assertEquals(spell.getDescription(), spellDetails.getDescription());
        assertEquals(spell.getLevel(), spellDetails.getLevel());
        assertEquals(spell.getEffect(), spellDetails.getEffect());
        assertEquals(spell.getNotes(), spellDetails.getNotes());
        assertEquals(spell.getRange(), spellDetails.getRange());
        assertEquals(spell.getCastingType(), spellDetails.getCastingType());
        assertEquals(spell.getDurationType(), spellDetails.getDurationType());
    }

    @Test
    public void givenInvalidUuidWhenGetSpellDetailsThenThrowException() {
        when(this.spellRepositoryMock.findById(UUID))
            .thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("Spell was not found");

        this.spellService.getSpellDetails(UUID);
    }

    @Test
    public void givenValidUuidWhenGetSpellThenReturnSpell() {
        Spell spell = TestUtils.mockSpell();
        when(this.spellRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(spell));

        Spell result = this.spellService.getSpell(UUID);

        assertEquals(spell.getName(), result.getName());
        assertEquals(spell.getDescription(), result.getDescription());
        assertEquals(spell.getLevel(), result.getLevel());
        assertEquals(spell.getEffect(), result.getEffect());
        assertEquals(spell.getNotes(), result.getNotes());
        assertEquals(spell.getRange(), result.getRange());
        assertEquals(spell.getCastingType(), result.getCastingType());
        assertEquals(spell.getDurationType(), result.getDurationType());
    }

    @Test
    public void givenInvalidUuidWhenGetSpellThenThrowException() {
        when(this.spellRepositoryMock.findById(UUID))
            .thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("Spell was not found");

        this.spellService.getSpell(UUID);
    }

    @Test
    public void givenValidPropertiesWhenGetAllByPagesThenReturnPageWithSpellDetails() {
        Spell spell = TestUtils.mockSpell();
        Page<Spell> page = new PageImpl<>(Collections.singletonList(spell));
        when(this.spellRepositoryMock.findAllContainingValue(anyString(), any(Pageable.class)))
            .thenReturn(page);
        Pageable pageable = mock(Pageable.class);

        Page<SpellDetailsDto> result = this.spellService.getAllByPages("search", pageable);

        verify(this.spellRepositoryMock, times(1)).findAllContainingValue(anyString(), any(Pageable.class));
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getTotalElements());
        assertEquals(spell.getName(), result.getContent().get(0).getName());
    }

    @Test
    public void givenValidUuidsWhenGetSpellThenReturnSetOfSpells() {
        Spell spell = (Spell) TestUtils.mockSpell().setUuid(UUID);
        when(this.spellRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(spell));

        Set<Spell> result = this.spellService.getSpells(Collections.singletonList(UUID));

        assertEquals(1, result.size());
    }

    @Test
    public void givenInvalidUuidsWhenGetSpellThenThrowException() {
        when(this.spellRepositoryMock.findById(UUID))
            .thenReturn(Optional.empty());

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("Spell was not found");

        this.spellService.getSpells(Collections.singletonList(UUID));
    }

    private SpellCreateDto mockSpellCreateDto() {
        return new SpellCreateDto()
            .setName("Spell")
            .setDescription("Description")
            .setLevel((byte) 1)
            .setEffect("Burning")
            .setNotes("Causes Burning effect.")
            .setRange("Ranged 60ft.")
            .setCastingType(SpellCastingTypeEnum.ENEMY)
            .setDurationType(SpellDurationTypeEnum.UNTIL_DISPELLED);
    }

}
