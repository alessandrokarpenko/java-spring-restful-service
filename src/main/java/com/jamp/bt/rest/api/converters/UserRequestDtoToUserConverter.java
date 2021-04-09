package com.jamp.bt.rest.api.converters;

import com.jamp.bt.rest.api.dto.user.User;
import com.jamp.bt.rest.api.dto.user.UserRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {

    @Override
    public User convert(UserRequestDto userRequestDto) {

        return new User(userRequestDto.getId(), userRequestDto.getName(),
                userRequestDto.getSurname(), LocalDate.parse(userRequestDto.getBirthday()));

    }
}
