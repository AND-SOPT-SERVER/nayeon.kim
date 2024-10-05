package org.sopt.practice;

public class Driver {
    private Person person;

    public Driver(Person person) {
        this.person = person;
    }

    public boolean canDrive(){
        return person.getAge()>20;
    }
}
