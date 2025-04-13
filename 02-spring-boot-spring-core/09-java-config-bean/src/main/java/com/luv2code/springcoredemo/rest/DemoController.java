package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

     //define a private field for the dependency
    private Coach myCoach;

    //define a constructor for dependency injection
    @Autowired
    public DemoController(
            @Qualifier("aquatic") Coach coach ){
        System.out.println("In Constructor: "+getClass().getSimpleName());
        this.myCoach = coach;
    }

    @GetMapping("/dailyWorkout")
    public String getDailyWorkOut(){
        return myCoach.getDailyWorkout();
    }

}
