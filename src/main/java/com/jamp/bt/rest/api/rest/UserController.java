package com.jamp.bt.rest.api.rest;

import com.jamp.bt.rest.api.dto.user.User;
import com.jamp.bt.rest.api.dto.user.UserRequestDto;
import com.jamp.bt.rest.api.dto.user.UserResponseDto;
import com.jamp.bt.rest.api.service.api.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Tag(name = "User service", description = "API for interaction with users")
public class UserController {

    @Autowired()
    private UserService userService;

    @Autowired()
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    UserController() {

    }

    @PostMapping()
    @Operation(summary = "Create new user")
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        User user = conversionService.convert(userRequestDto, User.class);
        userService.createUser(user);
        return conversionService.convert(user, UserResponseDto.class);
    }

    @PutMapping()
    @Operation(summary = "Update user")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto) {
        User user = conversionService.convert(userRequestDto, User.class);
        return conversionService.convert(userService.updateUser(user), UserResponseDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user")
    public UserResponseDto getUser(@PathVariable Long id) {
        return conversionService.convert(userService.findById(id), UserResponseDto.class);
    }

    @GetMapping()
    @Operation(summary = "Get all users")
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream().map(x -> conversionService.convert(x, UserResponseDto.class))
                .collect(Collectors.toList());
    }

}
