package com.dndadventure;

import com.dndadventure.domain.entities.*;
import com.dndadventure.domain.entities.RaceTraits.RaceAdvantage;
import com.dndadventure.domain.entities.RaceTraits.RaceDisadvantage;
import com.dndadventure.domain.entities.constants.*;
import com.dndadventure.domain.entities.items.Weapon;
import com.dndadventure.repositories.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DbInit {


    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final StatsModifierRepository statsModifierRepository;
    private final RaceRepository raceRepository;
    private final CharacterClassRepository classRepository;
    private final SpellRepository spellRepository;
    private final WeaponRepository weaponRepository;

    private final PasswordEncoder encoder;

    public DbInit(UserRoleRepository userRoleRepository,
                  UserRepository userRepository,
                  StatsModifierRepository statsModifierRepository,
                  RaceRepository raceRepository,
                  CharacterClassRepository classRepository,
                  SpellRepository spellRepository,
                  WeaponRepository weaponRepository,
                  PasswordEncoder encoder) {
        this.statsModifierRepository = statsModifierRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.raceRepository = raceRepository;
        this.classRepository = classRepository;
        this.spellRepository = spellRepository;
        this.weaponRepository = weaponRepository;
        this.encoder = encoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedUserRoles();
        this.seedUsers();
        this.seedModifiers();
        this.seedRaces();
        this.seedClasses();
        this.seedSpells();
        this.seedWeapons();
    }

    private void seedUsers() {
        if (this.userRepository.count() == 0) {
            UserRole adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN).get();
            UserRole dungeonMasterRole = this.userRoleRepository.findByRole(UserRoleEnum.DUNGEON_MASTER).get();
            UserRole userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER).get();

            //TODO:

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin"));
            admin.setUserRoles(new HashSet<>(List.of(adminRole, dungeonMasterRole, userRole)));
            this.userRepository.save(admin);

            User dungeonMaster = new User();
            dungeonMaster.setUsername("dungeonMaster");
            dungeonMaster.setPassword(encoder.encode("dungeonMaster"));
            dungeonMaster.setUserRoles(new HashSet<>(List.of(dungeonMasterRole, userRole)));
            this.userRepository.save(dungeonMaster);

            User user = new User();
            user.setUsername("user");
            user.setPassword(encoder.encode("user"));
            user.setUserRoles(new HashSet<>(List.of(userRole)));
            this.userRepository.save(user);

        }
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
        if (this.statsModifierRepository.count() == 0) {
            StatsModifier strModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.STRENGTH).setValue((byte) 2);
            StatsModifier intModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.INTELLIGENCE).setValue((byte) 2);
            StatsModifier dexModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.DEXTERITY).setValue((byte) 2);
            StatsModifier wisModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.WISDOM).setValue((byte) 2);
            StatsModifier charModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.CHARISMA).setValue((byte) 2);
            StatsModifier conModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.CONSTITUTION).setValue((byte) 2);

            StatsModifier strNegModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.STRENGTH).setValue((byte) -2);
            StatsModifier intNegModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.INTELLIGENCE).setValue((byte) -2);
            StatsModifier dexNegModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.DEXTERITY).setValue((byte) -2);
            StatsModifier wisNegModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.WISDOM).setValue((byte) -2);
            StatsModifier charNegModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.CHARISMA).setValue((byte) -2);
            StatsModifier conNegModifier = (StatsModifier) new StatsModifier().setName(CharacterStatsEnum.CONSTITUTION).setValue((byte) -2);

            this.statsModifierRepository.save(strModifier);
            this.statsModifierRepository.save(intModifier);
            this.statsModifierRepository.save(dexModifier);
            this.statsModifierRepository.save(wisModifier);
            this.statsModifierRepository.save(charModifier);
            this.statsModifierRepository.save(conModifier);

            this.statsModifierRepository.save(strNegModifier);
            this.statsModifierRepository.save(intNegModifier);
            this.statsModifierRepository.save(dexNegModifier);
            this.statsModifierRepository.save(wisNegModifier);
            this.statsModifierRepository.save(charNegModifier);
            this.statsModifierRepository.save(conNegModifier);
        }
    }

    private void seedRaces() {
        if (this.raceRepository.count() == 0) {
            List<StatsModifier> statsModifiers = this.statsModifierRepository.findAll();
            int statsSize = statsModifiers.size();

            List<RaceAdvantage> raceAdvantages = new ArrayList<>();
            List<RaceDisadvantage> raceDisadvantages = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                raceAdvantages.add((RaceAdvantage) new RaceAdvantage()
                    .setName(String.format("Advantage name %d", (i + 1)))
                    .setDescription(String.format("Advantage description %d for advantage with name %d.", (i + 1), (i + 1))));

                raceDisadvantages.add((RaceDisadvantage) new RaceDisadvantage()
                    .setName(String.format("Disadvantage name %d", (i + 1)))
                    .setDescription(String.format("Disadvantage description %d for disadvantage with name %d.", (i + 1), (i + 1))));
            }

            Race elf = new Race()
                .setName("Elf")
                .setDescription("Elves are a magical people of otherworldly grace, living in the world but not entirely part of it.")
                .setAdvantages(Set.of(raceAdvantages.get(0), raceAdvantages.get(1)))
                .setDisadvantages(Set.of(raceDisadvantages.get(0), raceDisadvantages.get(1)))
                .setModifiers(List.of(statsModifiers.get(randomNumber(statsSize)), statsModifiers.get(randomNumber(statsSize))));

            Race dwarf = new Race()
                .setName("Dwarf")
                .setDescription("Bold and hardy, dwarves are known as skilled warriors, miners, and workers of stone and metal.")
                .setAdvantages(Set.of(raceAdvantages.get(2), raceAdvantages.get(3)))
                .setDisadvantages(Set.of(raceDisadvantages.get(2), raceDisadvantages.get(3)))
                .setModifiers(List.of(statsModifiers.get(randomNumber(statsSize)), statsModifiers.get(randomNumber(statsSize))));

            Race human = new Race()
                .setName("Human")
                .setDescription("Humans are the most adaptable and ambitious people among the common races. " +
                    "Whatever drives them, humans are the innovators, the achievers, and the pioneers of the worlds.")
                .setAdvantages(Set.of(raceAdvantages.get(4), raceAdvantages.get(5)))
                .setDisadvantages(Set.of(raceDisadvantages.get(4), raceDisadvantages.get(5)))
                .setModifiers(List.of(statsModifiers.get(randomNumber(statsSize)), statsModifiers.get(randomNumber(statsSize))));

            Race orc = new Race()
                .setName("Orc")
                .setDescription("Half-orcs’ grayish pigmentation, sloping foreheads, jutting jaws, prominent teeth, " +
                    "and towering builds make their orcish heritage plain for all to see.")
                .setAdvantages(Set.of(raceAdvantages.get(6), raceAdvantages.get(7)))
                .setDisadvantages(Set.of(raceDisadvantages.get(6), raceDisadvantages.get(7)))
                .setModifiers(List.of(statsModifiers.get(randomNumber(statsSize)), statsModifiers.get(randomNumber(statsSize))));

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
