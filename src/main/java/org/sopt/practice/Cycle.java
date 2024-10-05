package org.sopt.practice;

public class Cycle implements Vehicle {
    public String run(Driver driver) {
        if(driver.canDrive()) {
            return "dd";
        } else{
            return "ee";
        }
    }
}
