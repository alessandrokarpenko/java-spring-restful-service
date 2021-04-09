package com.jamp.bt.rest.api.converters;

import com.jamp.bt.rest.api.dto.user.User;
import com.jamp.bt.rest.api.dto.user.UserResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {

    @Override
    public UserResponseDto convert(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        userResponseDto.setBirthday(user.getBirthday().toString());

        return userResponseDto;
    }

}
