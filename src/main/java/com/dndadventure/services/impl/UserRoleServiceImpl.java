package com.dndadventure.services.impl;

import com.dndadventure.domain.dtos.UserRoleDetails;
import com.dndadventure.domain.entities.UserRole;
import com.dndadventure.repositories.UserRoleRepository;
import com.dndadventure.services.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, ModelMapper modelMapper) {
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserRoleDetails> getAll() {
        List<UserRole> userRoles = this.userRoleRepository.findAll();
        return userRoles.stream()
            .map(userRole -> this.modelMapper.map(userRole, UserRoleDetails.class))
            .sorted(Comparator.comparing(a -> a.getRole().name()))
            .collect(Collectors.toList());
    }
}
