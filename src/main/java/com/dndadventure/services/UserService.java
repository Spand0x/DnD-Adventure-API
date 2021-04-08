package com.dndadventure.services;

import com.dndadventure.domain.dtos.UserChangeRoleDto;
import com.dndadventure.domain.dtos.UserDetailsDto;
import com.dndadventure.domain.dtos.UserInfoDto;
import com.dndadventure.domain.dtos.UserRegisterDto;
import com.dndadventure.domain.entities.Character;
import com.dndadventure.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    UserInfoDto get(User user);

    List<User> getAll();

    void register(UserRegisterDto userRegisterDto);

    void addCharacter(User user, Character character);

    Page<UserDetailsDto> getAllByPages(String searchValue, Pageable pageable);

    void changeRole(UserChangeRoleDto userChangeRoleDto);

    void delete(String uuid);
}
