package com.dndadventure.testutils;

import com.dndadventure.domain.entities.*;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.constants.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TestUtils {
    private static final String UUID = "aaa-aaa-aaa";
    private static final String UUID_TWO = "bbb-bbb-bbb0";

    public static Race mockRace() {
        BaseRaceTraits advantage = (BaseRaceTraits) new BaseRaceTraits()
            .setName("Advantage 1")
            .setDescription("Advantage 1 Description")
            .setUuid(UUID);
        BaseRaceTraits disadvantage = (BaseRaceTraits) new BaseRaceTraits()
            .setName("Disadvantage 1")
            .setDescription("Disadvantage 1 Description")
            .setUuid(UUID);
        StatModifier statModifier = (StatModifier) new StatModifier()
            .setName(CharacterStatsEnum.DEXTERITY)
            .setValue((byte) 2)
            .setUuid(UUID);
        StatModifier statModifierTwo = (StatModifier) new StatModifier()
            .setName(CharacterStatsEnum.STRENGTH)
            .setValue((byte) -2)
            .setUuid(UUID_TWO);

        return new Race()
            .setName("Human")
            .setDescription("Some Description")
            .setAdvantages(Collections.singleton(advantage))
            .setDisadvantages(Collections.singleton(disadvantage))
            .setModifiers(List.of(statModifier, statModifierTwo));

    }


    public static StatModifier mockStatModifier(String uuid) {
        return (StatModifier) new StatModifier()
            .setName(CharacterStatsEnum.STRENGTH)
            .setValue((byte) -2)
            .setUuid(uuid);
    }

    public static CharacterClass mockCharacterClass() {
        return new CharacterClass()
            .setName("Mage")
            .setDescription("Some Description")
            .setHitPointsDice(DiceTypeEnum.D8)
            .setSavingThrowStat(CharacterStatsEnum.DEXTERITY)
            .setMaxSpellCharges(3.0d)
            .setSpellChargesPerLevel(1.0d);
    }

    public static User mockUser() {
        return new User()
            .setEmail("asd@asd")
            .setUsername("username")
            .setPassword("password")
            .setCharacters(Collections.emptySet())
            .setUserRoles(Collections.singleton(new UserRole().setRole(UserRoleEnum.USER)));
    }

    public static Spell mockSpell() {
        return new Spell()
            .setName("Spell")
            .setDescription("Description")
            .setLevel((byte) 1)
            .setEffect("Burning")
            .setNotes("Causes Burning effect.")
            .setRange("Ranged 60ft.")
            .setCastingType(SpellCastingTypeEnum.ENEMY)
            .setDurationType(SpellDurationTypeEnum.UNTIL_DISPELLED);
    }

    public static Weapon mockWeapon() {
        return new Weapon()
            .setName("Sword")
            .setDescription("Sword description")
            .setGold(2)
            .setWeaponType("Sword")
            .setRarity(RarityEnum.COMMON)
            .setAttackType("Piercing")
            .setDamageDice(DiceTypeEnum.D8)
            .setDamageModifier(CharacterStatsEnum.STRENGTH)
            .setHitChanceBonus((byte) 2);
    }

    public static Character mockCharacter() {
        return new Character()
            .setName("Name")
            .setDescription("Description")
            .setLevel((byte) 1)
            .setMaxHitPoints(10)
            .setCurrentHitPoints(5)
            .setInitiative((byte) 3)
            .setGold(10)
            .setDead(false)
            .setRace(TestUtils.mockRace())
            .setClazz(TestUtils.mockCharacterClass())
            .setUser(TestUtils.mockUser())
            .setStats(mockCharacterStats())
            .setSpells(Collections.singleton(TestUtils.mockSpell()))
            .setWeapons(Collections.singleton(TestUtils.mockWeapon()));
    }

    public static Set<CharacterStat> mockCharacterStats() {
        CharacterStat str = (CharacterStat) new CharacterStat()
            .setName(CharacterStatsEnum.STRENGTH)
            .setValue((byte) 17);
        CharacterStat dex = (CharacterStat) new CharacterStat()
            .setName(CharacterStatsEnum.DEXTERITY)
            .setValue((byte) 12);
        CharacterStat con = (CharacterStat) new CharacterStat()
            .setName(CharacterStatsEnum.CONSTITUTION)
            .setValue((byte) 9);
        CharacterStat cha = (CharacterStat) new CharacterStat()
            .setName(CharacterStatsEnum.CHARISMA)
            .setValue((byte) 4);
        CharacterStat wis = (CharacterStat) new CharacterStat()
            .setName(CharacterStatsEnum.WISDOM)
            .setValue((byte) 10);
        CharacterStat intel = (CharacterStat) new CharacterStat()
            .setName(CharacterStatsEnum.INTELLIGENCE)
            .setValue((byte) 20);
        return Set.of(str, dex, con, cha, wis, intel);

    }
}
