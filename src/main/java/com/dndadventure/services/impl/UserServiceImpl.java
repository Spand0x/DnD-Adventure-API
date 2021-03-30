package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.dtos.UserRegisterDto;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.domain.entities.constants.UserRoleEnum;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.UserRepository;
import com.dndadventure.repositories.UserRoleRepository;
import com.dndadventure.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserInfoDto get(User user) {
        User userEntity = this.userRepository.findById(user.getUuid())
            .orElseThrow(NotFoundException::new);
        return this.modelMapper.map(userEntity, UserInfoDto.class);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getRepPassword())) {
            throw new IllegalArgumentException("Passwords does not match");
        }

        if(userRegisterDto.getUsername().length() < 3) {
            throw new IllegalArgumentException("Username should be more then 3 symbols!");
        }

        if(userRegisterDto.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password should be more then 6 symbols!");
        }

        if (this.userRepository.findByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("User with this username / email already exists.");
        }
            UserRole userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER)
                .orElseThrow(() -> new NotFoundException("User Role was not found."));

        User user = new User()
            .setUserRoles(Set.of(userRole))
            .setEmail(userRegisterDto.getEmail().toLowerCase())
            .setUsername(userRegisterDto.getUsername().toLowerCase())
            .setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        this.userRepository.save(user);
    }

    @Override
    public void addCharacter(User user, Character character) {
        if (user.getCharacters() == null) {
            user.setCharacters(new HashSet<>());
        }
        user.getCharacters().add(character);
        this.userRepository.save(user);
    }

}
