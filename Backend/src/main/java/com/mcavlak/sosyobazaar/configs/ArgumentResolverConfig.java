package com.mcavlak.sosyobazaar.configs;

import com.mcavlak.sosyobazaar.utils.argumentresolver.FileResultArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Component
public class ArgumentResolverConfig implements WebMvcConfigurer {

    @Autowired
    FileResultArgumentResolver fileResultArgumentResolver;


    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(fileResultArgumentResolver);
    }

}
