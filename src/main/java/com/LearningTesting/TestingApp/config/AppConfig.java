/**
 * @author mohnishkumar on 09 Jan, 2026 at 19:15:02
 */

package com.LearningTesting.TestingApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
