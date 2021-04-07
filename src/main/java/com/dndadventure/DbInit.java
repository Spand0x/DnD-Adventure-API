package com.dndadventure;

import com.dndadventure.domain.entities.*;
import com.dndadventure.domain.entities.BaseRaceTraits;
import com.dndadventure.domain.entities.constants.*;
import com.dndadventure.domain.entities.Weapon;
import com.dndadventure.repositories.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DbInit {


    private final UserRoleRepository userRoleRepository;
    private final StatModifierRepository statModifierRepository;
    private final RaceRepository raceRepository;
    private final CharacterClassRepository classRepository;
    private final SpellRepository spellRepository;
    private final WeaponRepository weaponRepository;


    public DbInit(UserRoleRepository userRoleRepository,
                  StatModifierRepository statModifierRepository,
                  RaceRepository raceRepository,
                  CharacterClassRepository classRepository,
                  SpellRepository spellRepository,
                  WeaponRepository weaponRepository) {
        this.statModifierRepository = statModifierRepository;
        this.userRoleRepository = userRoleRepository;
        this.raceRepository = raceRepository;
        this.classRepository = classRepository;
        this.spellRepository = spellRepository;
        this.weaponRepository = weaponRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedUserRoles();
        this.seedModifiers();
        this.seedRaces();
        this.seedClasses();
        this.seedSpells();
        this.seedWeapons();
    }

    private void seedUserRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRole admin = new UserRole().setRole(UserRoleEnum.ADMIN);
            UserRole dungeonMaster = new UserRole().setRole(UserRoleEnum.DUNGEON_MASTER);
            UserRole user = new UserRole().setRole(UserRoleEnum.USER);

            this.userRoleRepository.save(admin);
            this.userRoleRepository.save(dungeonMaster);
            this.userRoleRepository.save(user);
        }
    }

    private void seedModifiers() {
        if (this.statModifierRepository.count() == 0) {
            StatModifier strModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.STRENGTH).setValue((byte) 2);
            StatModifier intModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.INTELLIGENCE).setValue((byte) 2);
            StatModifier dexModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.DEXTERITY).setValue((byte) 2);
            StatModifier wisModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.WISDOM).setValue((byte) 2);
            StatModifier charModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.CHARISMA).setValue((byte) 2);
            StatModifier conModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.CONSTITUTION).setValue((byte) 2);

            StatModifier strNegModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.STRENGTH).setValue((byte) -2);
            StatModifier intNegModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.INTELLIGENCE).setValue((byte) -2);
            StatModifier dexNegModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.DEXTERITY).setValue((byte) -2);
            StatModifier wisNegModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.WISDOM).setValue((byte) -2);
            StatModifier charNegModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.CHARISMA).setValue((byte) -2);
            StatModifier conNegModifier = (StatModifier) new StatModifier().setName(CharacterStatsEnum.CONSTITUTION).setValue((byte) -2);

            this.statModifierRepository.save(strModifier);
            this.statModifierRepository.save(intModifier);
            this.statModifierRepository.save(dexModifier);
            this.statModifierRepository.save(wisModifier);
            this.statModifierRepository.save(charModifier);
            this.statModifierRepository.save(conModifier);

            this.statModifierRepository.save(strNegModifier);
            this.statModifierRepository.save(intNegModifier);
            this.statModifierRepository.save(dexNegModifier);
            this.statModifierRepository.save(wisNegModifier);
            this.statModifierRepository.save(charNegModifier);
            this.statModifierRepository.save(conNegModifier);
        }
    }

    private void seedRaces() {
        if (this.raceRepository.count() == 0) {
            List<StatModifier> statModifiers = this.statModifierRepository.findAll();
            int statsSize = statModifiers.size();

            List<BaseRaceTraits> advantages = new ArrayList<>();
            List<BaseRaceTraits> disadvantages = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                advantages.add((BaseRaceTraits) new BaseRaceTraits()
                    .setName(String.format("Advantage name %d", (i + 1)))
                    .setDescription(String.format("Advantage description %d for advantage with name %d.", (i + 1), (i + 1))));

                disadvantages.add((BaseRaceTraits) new BaseRaceTraits()
                    .setName(String.format("Disadvantage name %d", (i + 1)))
                    .setDescription(String.format("Disadvantage description %d for disadvantage with name %d.", (i + 1), (i + 1))));
            }

            Race elf = new Race()
                .setName("Elf")
                .setDescription("Elves are a magical people of otherworldly grace, living in the world but not entirely part of it.")
                .setAdvantages(Set.of(advantages.get(0), advantages.get(1)))
                .setDisadvantages(Set.of(disadvantages.get(0), disadvantages.get(1)))
                .setModifiers(List.of(statModifiers.get(randomNumber(statsSize)), statModifiers.get(randomNumber(statsSize))));

            Race dwarf = new Race()
                .setName("Dwarf")
                .setDescription("Bold and hardy, dwarves are known as skilled warriors, miners, and workers of stone and metal.")
                .setAdvantages(Set.of(advantages.get(2), advantages.get(3)))
                .setDisadvantages(Set.of(disadvantages.get(2), disadvantages.get(3)))
                .setModifiers(List.of(statModifiers.get(randomNumber(statsSize)), statModifiers.get(randomNumber(statsSize))));

            Race human = new Race()
                .setName("Human")
                .setDescription("Humans are the most adaptable and ambitious people among the common races. " +
                    "Whatever drives them, humans are the innovators, the achievers, and the pioneers of the worlds.")
                .setAdvantages(Set.of(advantages.get(4), advantages.get(5)))
                .setDisadvantages(Set.of(disadvantages.get(4), disadvantages.get(5)))
                .setModifiers(List.of(statModifiers.get(randomNumber(statsSize)), statModifiers.get(randomNumber(statsSize))));

            Race orc = new Race()
                .setName("Orc")
                .setDescription("Half-orcs’ grayish pigmentation, sloping foreheads, jutting jaws, prominent teeth, " +
                    "and towering builds make their orcish heritage plain for all to see.")
                .setAdvantages(Set.of(advantages.get(6), advantages.get(7)))
                .setDisadvantages(Set.of(disadvantages.get(6), disadvantages.get(7)))
                .setModifiers(List.of(statModifiers.get(randomNumber(statsSize)), statModifiers.get(randomNumber(statsSize))));

            this.raceRepository.save(elf);
            this.raceRepository.save(dwarf);
            this.raceRepository.save(human);
            this.raceRepository.save(orc);
        }
    }

    private void seedClasses() {
        if (this.classRepository.count() == 0) {
            CharacterClass rogue = new CharacterClass()
                .setName("Rogue")
                .setDescription("A scoundrel who uses stealth and trickery to overcome obstacles and enemies")
                .setHitPointsDice(DiceTypeEnum.D8)
                .setSavingThrowStat(CharacterStatsEnum.DEXTERITY);

            CharacterClass sorcerer = new CharacterClass()
                .setName("Sorcerer")
                .setDescription("A spellcaster who draws on inherent magic from a gift or bloodline")
                .setHitPointsDice(DiceTypeEnum.D6)
                .setSavingThrowStat(CharacterStatsEnum.CHARISMA)
                .setMaxSpellCharges(3.0d)
                .setSpellChargesPerLevel(1.5d);

            CharacterClass barbarian = new CharacterClass()
                .setName("Barbarian")
                .setDescription("A fierce warrior of primitive background who can enter a battle rage")
                .setHitPointsDice(DiceTypeEnum.D12)
                .setSavingThrowStat(CharacterStatsEnum.STRENGTH);

            CharacterClass druid = new CharacterClass()
                .setName("Druid")
                .setDescription("A priest of the Old Faith, wielding the powers of nature and adopting animal forms")
                .setHitPointsDice(DiceTypeEnum.D8)
                .setSavingThrowStat(CharacterStatsEnum.WISDOM)
                .setMaxSpellCharges(3.0d)
                .setSpellChargesPerLevel(1.0d);

            CharacterClass paladin = new CharacterClass()
                .setName("Paladin")
                .setDescription("A holy warrior bound to a sacred oath")
                .setHitPointsDice(DiceTypeEnum.D10)
                .setSavingThrowStat(CharacterStatsEnum.WISDOM)
                .setMaxSpellCharges(2.0d)
                .setSpellChargesPerLevel(1.0d);

            this.classRepository.save(rogue);
            this.classRepository.save(sorcerer);
            this.classRepository.save(barbarian);
            this.classRepository.save(druid);
            this.classRepository.save(paladin);

        }
    }

    private void seedSpells() {
        if (this.spellRepository.count() == 0) {
            Spell fireball = new Spell()
                .setName("Fireball")
                .setDescription("A bright streak flashes from your pointing finger to a point you choose within range " +
                    "and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere " +
                    "centered on that point must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, " +
                    "or half as much damage on a successful one.\n" +
                    "\n" +
                    "The fire spreads around corners. It ignites flammable objects in the area that aren't being worn or carried.")
                .setLevel((byte) 1)
                .setDamageDice(DiceTypeEnum.D8)
                .setDamageModifier(CharacterStatsEnum.INTELLIGENCE)
                .setHitChanceBonus((byte) 2)
                .setEffect("Burning")
                .setNotes("Burns everything flammable in the rooms")
                .setRange("Ranged 60ft.")
                .setCastingType(SpellCastingTypeEnum.ENEMY)
                .setDurationType(SpellDurationTypeEnum.TIME)
                .setDuration(3)
                .setDurationUnit(DurationUnitEnum.ROUND);

            Spell darkness = new Spell()
                .setName("Darkness")
                .setDescription("Magical darkness spreads from a point you choose within range to fill " +
                    "a 15-foot-radius sphere for the duration. The darkness spreads around corners. " +
                    "A creature with darkvision can't see through this darkness, and nonmagical " +
                    "light can't illuminate it.\n" +
                    "\n" +
                    "If the point you choose is on an object you are " +
                    "holding or one that isn't being worn or carried, the darkness " +
                    "emanates from the object and moves with it. Completely covering " +
                    "the source of the darkness with an opaque object, such as a bowl " +
                    "or a helm, blocks the darkness.")
                .setLevel((byte) 1)
                .setHitChanceBonus((byte) 2)
                .setEffect("Blind")
                .setNotes("Blinds Enemies")
                .setRange("Touch")
                .setCastingType(SpellCastingTypeEnum.MATERIAL)
                .setDurationType(SpellDurationTypeEnum.UNTIL_DISPELLED);

            Spell confusion = new Spell()
                .setName("Confusion")
                .setDescription("This spell assaults and twists creatures' minds, " +
                    "spawning delusions and provoking uncontrolled action. " +
                    "Each creature in a 10-foot-radius sphere centered on a " +
                    "point you choose within range must succeed on a Wisdom saving " +
                    "throw when you cast this spell or be affected by it.")
                .setLevel((byte) 1)
                .setHitChanceBonus((byte) 1)
                .setEffect("Control")
                .setNotes("Affects all creatures around")
                .setRange("Ranged 100ft.")
                .setCastingType(SpellCastingTypeEnum.ENEMY)
                .setDurationType(SpellDurationTypeEnum.TIME)
                .setDuration(1)
                .setDurationUnit(DurationUnitEnum.MINUTE);

            this.spellRepository.save(fireball);
            this.spellRepository.save(darkness);
            this.spellRepository.save(confusion);

        }
    }

    private void seedWeapons() {
        if (this.weaponRepository.count() == 0) {
            Weapon shortSword = new Weapon()
                .setName("Short Sword")
                .setDescription("A basic short sword")
                .setGold(10)
                .setRarity(RarityEnum.COMMON)
                .setWeaponType("Sword")
                .setAttackType("Slashing")
                .setDamageDice(DiceTypeEnum.D6)
                .setDamageModifier(CharacterStatsEnum.DEXTERITY)
                .setHitChanceBonus((byte) 0);

            Weapon bow = new Weapon()
                .setName("Bow")
                .setDescription("Bow, requires any kind of arrows")
                .setGold(7)
                .setRarity(RarityEnum.UNCOMMON)
                .setWeaponType("Bow")
                .setAttackType("Ranged")
                .setDamageDice(DiceTypeEnum.D8)
                .setDamageModifier(CharacterStatsEnum.DEXTERITY)
                .setHitChanceBonus((byte) 2);

            Spell spell = this.spellRepository.findAllByName("Confusion").get(0);

            Weapon magicAxe = new Weapon()
                .setName("Ace Axe")
                .setDescription("Special Axe applying confusion on successful hit.")
                .setGold(30)
                .setRarity(RarityEnum.RARE)
                .setWeaponType("Axe")
                .setAttackType("Slashing")
                .setDamageDice(DiceTypeEnum.D10)
                .setDamageModifier(CharacterStatsEnum.STRENGTH)
                .setHitChanceBonus((byte) -1)
                .setSpell(spell);

            this.weaponRepository.save(shortSword);
            this.weaponRepository.save(bow);
            this.weaponRepository.save(magicAxe);
        }
    }

    private Integer randomNumber(int limit) {
        return new Random().nextInt(limit);
    }
}
