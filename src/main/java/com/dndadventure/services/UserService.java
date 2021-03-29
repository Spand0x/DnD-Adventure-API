package com.dndadventure.services;

import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.dtos.UserRegisterDto;
import com.dndadventure.domain.entities.User;
import com.dndadventure.domain.entities.UserPrincipal;

import java.util.List;


public interface UserService {
    UserInfoDto get(User user);

    List<User> findAll();

    void register(UserRegisterDto userRegisterDto);
}
