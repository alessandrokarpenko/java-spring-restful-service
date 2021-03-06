package com.jamp.bt.rest.api.converters;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserToUserResponseDtoConverter());
        registry.addConverter(new UserRequestDtoToUserConverter());
        registry.addConverter(new SubscriptionToSubscriptionResponseDtoConverter());
    }
}
