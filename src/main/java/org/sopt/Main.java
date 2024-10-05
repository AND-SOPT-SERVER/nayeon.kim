package org.sopt;

import org.sopt.practice.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Person person = new Person(30);
        Driver driver = new Driver(person);

        Vehicle vehicle1 = new Car("Gv80", "현대");
        Car car = new Car("Gv80", "현대");
        Vehicle vehicle2 = new Cycle();

        car.bbang();
        System.out.println(vehicle1.run(driver));
        System.out.println(vehicle2.run(driver));

    }
}