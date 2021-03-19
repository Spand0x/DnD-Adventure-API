package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserPrincipal;
import com.dndadventure.exceptions.NotFoundException;
import com.dndadventure.repositories.UserRepository;
import com.dndadventure.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserInfoDto get(User user) {
        User userEntity = this.userRepository.findById(user.getUuid())
            .orElseThrow(NotFoundException::new);
        UserInfoDto userInfoDto = this.modelMapper.map(userEntity, UserInfoDto.class);
        return userInfoDto;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

}
