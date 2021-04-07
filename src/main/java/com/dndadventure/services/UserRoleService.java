package com.dndadventure.services;

import com.dndadventure.domain.dtos.UserRoleDetails;

import java.util.List;

public interface UserRoleService {
    List<UserRoleDetails> getAll();
}
