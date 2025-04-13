package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component // marks the class as a Spring Bean makes it available for dependency injection
public class CricketCoach implements Coach{

    public CricketCoach(){
        System.out.println("In Constructor: "+getClass().getSimpleName());
    }

    //define our init method
    @PostConstruct //runs after Constructor of this class. After bean construction.
    public void doMyStartupStuff(){
        System.out.println("In doMyStartupStuff(): "+getClass().getSimpleName());
    }

    //define our destroy method
    @PreDestroy //this is executed when we stop the application
    public void doMyCleanUpStuff(){
        System.out.println("In doMyCleanUpStuff(): "+getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
