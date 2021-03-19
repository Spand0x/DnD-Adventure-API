package com.dndadventure;

import com.dndadventure.domain.entities.CharacterRace;
import com.dndadventure.domain.entities.StatsModifier;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.domain.entities.constants.CharacterStatsEnum;
import com.dndadventure.domain.entities.constants.UserRoleEnum;
import com.dndadventure.repositories.UserRepository;
import com.dndadventure.repositories.UserRoleRepository;
import com.dndadventure.services.CharacterRaceService;
import com.dndadventure.services.StatsModifierService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbInit {

    private final CharacterRaceService characterRaceService;
    private final StatsModifierService statsModifierService;

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public DbInit(CharacterRaceService characterRaceService,
                  StatsModifierService statsModifierService,
                  UserRoleRepository userRoleRepository,
                  UserRepository userRepository, PasswordEncoder encoder) {
        this.characterRaceService = characterRaceService;
        this.statsModifierService = statsModifierService;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedUserRoles();
        this.seedUsers();
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

    public void run() {


        if (this.characterRaceService.findAll().isEmpty()) {
            CharacterRace elf = new CharacterRace();
            CharacterRace dwarf = new CharacterRace();

//            StatsModifier strModifier = this.statsModifierService.save((StatsModifier) new StatsModifier().setName(CharacterStatsEnum.STRENGTH).setValue((byte) 2));
//            StatsModifier intModifier = this.statsModifierService.save((StatsModifier) new StatsModifier().setName(CharacterStatsEnum.INTELLIGENCE).setValue((byte) 3));
//            StatsModifier dexModifier = this.statsModifierService.save((StatsModifier) new StatsModifier().setName(CharacterStatsEnum.DEXTERITY).setValue((byte) 2));

//            elf = new CharacterRace()
//                .setName("Elf")
//                .setDescription("Well, its an Elf")
//                .setPros("Sees in the dark")
//                .setCons("Very Tall")
//                .setModifiers(List.of(strModifier, intModifier));
//
//            dwarf = new CharacterRace()
//                .setName("Dwarf")
//                .setDescription("Well, its a Dwarf")
//                .setPros("Its small")
//                .setCons("Slow")
//                .setModifiers(List.of(intModifier, dexModifier));
//
//            this.characterRaceService.saveAll(List.of(elf, dwarf));

        }
    }
}
