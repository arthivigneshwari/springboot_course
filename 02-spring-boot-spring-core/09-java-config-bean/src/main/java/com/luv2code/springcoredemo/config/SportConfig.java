package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    //SwimCoach did not have @Component annotation
    //Instead we configured as a Spring bean using @Bean
    //@Bean usecase: Take existing third-party class and expose as a Spring bean

    @Bean("aquatic") //provide custom bean id
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
