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
import com.dndadventure.services.UserService;
import com.dndadventure.testutils.TestUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private static final String UUID = "aaa-aaa-aaa";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UserRepository userRepositoryMock;
    private UserRoleRepository userRoleRepositoryMock;
    private PasswordEncoder passwordEncoderMock;
    private UserService userService;

    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        this.userRepositoryMock = mock(UserRepository.class);
        this.userRoleRepositoryMock = mock(UserRoleRepository.class);
        this.passwordEncoderMock = mock(PasswordEncoder.class);
        this.userService = new UserServiceImpl(this.userRepositoryMock,
            this.userRoleRepositoryMock,
            modelMapper,
            this.passwordEncoderMock);
    }

    @Test
    public void givenValidUserWhenGetThenReturnUserInfoDto() {
        User user = (User) TestUtils.mockUser()
            .setUuid(UUID);
        when(this.userRepositoryMock.findById(UUID))
            .thenReturn(Optional.of(user));

        UserInfoDto result = this.userService.get(user);

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getUserRoles().size(), result.getRoles().size());
    }

    @Test
    public void givenInvalidUserWhenGetThenThrowException() {
        when(this.userRepositoryMock.findById(UUID))
            .thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("User was not found!");

        this.userService.get((User) TestUtils.mockUser().setUuid(UUID));
    }

    @Test
    public void whenGetAllEmptyThenReturnEmptyList() {
        when(this.userRepositoryMock.findAll())
            .thenReturn(Collections.emptyList());

        List<User> result = this.userService.getAll();

        verify(this.userRepositoryMock).findAll();
        assertEquals(0, result.size());
    }

    @Test
    public void whenGetAllThenReturnListWithUsers() {
        User user = TestUtils.mockUser();
        when(this.userRepositoryMock.findAll())
            .thenReturn(Collections.singletonList(user));

        List<User> result = this.userService.getAll();

        verify(this.userRepositoryMock).findAll();
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    public void givenPasswordMismatchWhenRegisterThenThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto()
            .setPassword("a")
            .setRepPassword("aa");

        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("Passwords does not match!");

        this.userService.register(userRegisterDto);
    }

    @Test
    public void givenShortUsernameWhenRegisterThenThrowException() {
        UserRegisterDto userRegisterDto = mockUserRegisterDto().setUsername("a");

        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("Username should be more then 3 symbols!");

        this.userService.register(userRegisterDto);
    }

    @Test
    public void givenShortPasswordWhenRegisterThenThrowException() {
        UserRegisterDto userRegisterDto = mockUserRegisterDto()
            .setPassword("aa")
            .setRepPassword("aa");

        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("Password should be more then 6 symbols!");

        this.userService.register(userRegisterDto);
    }

    @Test
    public void givenValidUserDtoAndUsernameTakenWhenRegisterThenThrowException() {
        UserRegisterDto userRegisterDto = mockUserRegisterDto();
        when(this.userRepositoryMock.findByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail()))
            .thenReturn(Optional.of(TestUtils.mockUser()));

        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("User with this username / email already exists!");

        this.userService.register(userRegisterDto);
    }

    @Test
    public void givenValidUserAndRoleNotPersistedWhenRegisterThenThrowException() {
        UserRegisterDto userRegisterDto = mockUserRegisterDto();
        when(this.userRepositoryMock.findByUsernameOrEmail(anyString(), anyString()))
            .thenReturn(Optional.empty());
        when(this.userRoleRepositoryMock.findByRole(UserRoleEnum.USER))
            .thenReturn(Optional.empty());

        this.expectedException.expect(NotFoundException.class);
        this.expectedException.expectMessage("User Role was not found!");

        this.userService.register(userRegisterDto);
    }

    @Test
    public void givenValidUserDtoWhenRegisterThenPersistUser() {
        UserRegisterDto userRegisterDto = mockUserRegisterDto();
        when(this.userRepositoryMock.findByUsernameOrEmail(anyString(), anyString()))
            .thenReturn(Optional.empty());
        when(this.userRoleRepositoryMock.findByRole(UserRoleEnum.USER))
            .thenReturn(Optional.of(new UserRole().setRole(UserRoleEnum.USER)));

        when(this.passwordEncoderMock.encode(userRegisterDto.getPassword()))
            .thenReturn("EncodedPassword");

        this.userService.register(userRegisterDto);

        verify(this.userRepositoryMock).findByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());
        verify(this.userRoleRepositoryMock).findByRole(UserRoleEnum.USER);
        verify(this.passwordEncoderMock).encode(userRegisterDto.getPassword());
        verify(this.userRepositoryMock).save(any(User.class));
    }

    @Test
    public void givenValidUserAndCharacterWhenAddCharacterThenAddCharacterToUser() {
        User user = TestUtils.mockUser()
            .setCharacters(null);
        Character character = TestUtils.mockCharacter();

        when(this.userRepositoryMock.save(user))
            .thenReturn(user);

        this.userService.addCharacter(user, character);

        verify(this.userRepositoryMock).save(user);
    }

    @Test
    public void givenValidPropertiesWhenGetAllByPagesThenReturnPageWithUserDetails() {
        User user = TestUtils.mockUser();
        Page<User> page = new PageImpl<>(Collections.singletonList(user));
        when(this.userRepositoryMock.findAllContainingValue(anyString(), any(Pageable.class)))
            .thenReturn(page);
        Pageable pageable = mock(Pageable.class);

        Page<UserDetailsDto> result = this.userService.getAllByPages("search", pageable);

        verify(this.userRepositoryMock).findAllContainingValue(anyString(), any(Pageable.class));
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getTotalElements());
        assertEquals(user.getUsername(), result.getContent().get(0).getUsername());
    }

    private UserRegisterDto mockUserRegisterDto() {
        return new UserRegisterDto()
            .setUsername("Username")
            .setPassword("aaaaaa")
            .setRepPassword("aaaaaa")
            .setEmail("email@email");
    }
}
