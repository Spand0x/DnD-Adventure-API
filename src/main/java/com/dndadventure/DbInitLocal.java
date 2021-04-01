package com.dndadventure;

import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.domain.entities.constants.UserRoleEnum;
import com.dndadventure.repositories.UserRepository;
import com.dndadventure.repositories.UserRoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Profile("!prod")
@Component
public class DbInitLocal {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder encoder;

    public DbInitLocal(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.encoder = encoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        this.seedUsers();
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
}
