package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.UserChangeRoleDto;
import com.dndadventure.domain.dtos.UserDetailsDto;
import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.dtos.UserRegisterDto;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.domain.entities.constants.UserRoleEnum;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.UserRepository;
import com.dndadventure.repositories.UserRoleRepository;
import com.dndadventure.services.AuthService;
import com.dndadventure.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           AuthService authService,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.authService = authService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserInfoDto get(User user) {
        User userEntity = this.userRepository.findById(user.getUuid())
            .orElseThrow(() -> new NotFoundException("User was not found!"));
        UserInfoDto userInfoDto = this.modelMapper.map(userEntity, UserInfoDto.class);
        userInfoDto.setRoles(userEntity.getUserRoles()
            .stream()
            .map(UserRole::getRole)
            .collect(Collectors.toSet()));
        return userInfoDto;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getRepPassword())) {
            throw new IllegalArgumentException("Passwords does not match!");
        }

        if (userRegisterDto.getUsername().length() < 3) {
            throw new IllegalArgumentException("Username should be more then 3 symbols!");
        }

        if (userRegisterDto.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password should be more then 6 symbols!");
        }

        if (this.userRepository.findByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this username / email already exists!");
        }
        UserRole userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER)
            .orElseThrow(() -> new NotFoundException("User Role was not found!"));

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

    @Override
    public Page<UserDetailsDto> getAllByPages(String searchValue, Pageable pageable) {
        String value = searchValue.toLowerCase().trim();
        Page<User> users = this.userRepository.findAllContainingValue(value, pageable);
        return users.map(u -> {
            UserDetailsDto userDetailsDto = this.modelMapper.map(u, UserDetailsDto.class);
            userDetailsDto.setRoles(u.getUserRoles()
                .stream()
                .map(UserRole::getRole)
                .collect(Collectors.toSet()));
            return userDetailsDto;
        });
    }

    @Override
    public void changeRole(UserChangeRoleDto userChangeRoleDto) {
        User user = this.userRepository.findById(userChangeRoleDto.getUuid())
            .orElseThrow(() -> new NotFoundException("User was not found!"));
        UserRole newRole = this.userRoleRepository.findById(userChangeRoleDto.getNewRole())
            .orElseThrow(() -> new NotFoundException("Role was not found!"));
        Set<UserRole> roles = new HashSet<>();
        switch (newRole.getRole()) {
            case USER:
                roles.add(getUserRole(UserRoleEnum.USER));
                break;
            case DUNGEON_MASTER:
                roles.add(getUserRole(UserRoleEnum.USER));
                roles.add(getUserRole(UserRoleEnum.DUNGEON_MASTER));
                break;
            case ADMIN:
                roles.add(getUserRole(UserRoleEnum.USER));
                roles.add(getUserRole(UserRoleEnum.DUNGEON_MASTER));
                roles.add(getUserRole(UserRoleEnum.ADMIN));
                break;
            default:
                throw new NotFoundException("Role was not found.");
        }
        user.setUserRoles(roles);
        this.userRepository.save(user);
    }

    @Override
    public void delete(String uuid) {
        User user = this.userRepository.findById(uuid)
            .orElseThrow(() -> new NotFoundException("User was not found!"));
        this.authService.deleteUserRefreshTokens(user);
        this.userRepository.delete(user);
    }

    private UserRole getUserRole(UserRoleEnum roleEnum) {
        return this.userRoleRepository.findByRole(roleEnum)
            .orElseThrow(() -> new NotFoundException("Role was not found."));
    }

}
