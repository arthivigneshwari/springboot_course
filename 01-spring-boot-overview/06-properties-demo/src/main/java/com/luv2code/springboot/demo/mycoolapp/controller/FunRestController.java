package com.luv2code.springboot.demo.mycoolapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class FunRestController {

    //inject properties for coach.name and team.name
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    //expose new endpoint for "teamInfo"

    @GetMapping("/teamInfo")
    public String getTeamInfo(){
        return "Coach Name: " +coachName +", Team Name: " +teamName;
    }

    //expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello(){
        return "Hello World";
    }

    //expose an endpoint for "workout"
    @GetMapping("workout")
    public String getDailyWorkout(){
        return "Run a hard 1k";
    }

    //expose an endpoint for "fortune"
    @GetMapping("fortune")
    public String getDailyFortune(){
        return "Today is your lucky day";
    }
}
