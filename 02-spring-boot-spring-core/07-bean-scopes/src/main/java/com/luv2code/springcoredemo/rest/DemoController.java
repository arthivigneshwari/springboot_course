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
    private Coach anotherCoach;

    //define a constructor for dependency injection
    @Autowired
    public DemoController(
            @Qualifier("cricketCoach") Coach coach, //Default scope is singleton. All dependency injections for the bean will reference the SAME bean
            @Qualifier("cricketCoach") Coach anotherCoach ){
        System.out.println("In Constructor: "+getClass().getSimpleName());
        this.myCoach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/dailyWorkout")
    public String getDailyWorkOut(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing beans: myCoach == anotherCoach, " +(myCoach==anotherCoach);
    }

}
