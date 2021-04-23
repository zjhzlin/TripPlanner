package com.lynn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public class Person {

    private String name;
    private Set<Gear> gears;
    //private int money;
    private Money money;
    private Set<LocalDate> availableDates;
    private Set<Qualification> qualifications;

    public Person(String name, Set<Gear> gears, Money money, Set<LocalDate> availableDates, Set<Qualification> qualifications) {
        this.name = name;
        this.gears = gears;
        this.money = money;
        this.availableDates = availableDates;
        this.qualifications = qualifications;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setMoney(Money money) {
        this.money = money;
    }

    public void setAvailableDates(Set<LocalDate> availableDates) {
        this.availableDates = availableDates;
    }

    public String getName() {
        return name;
    }

    public Set<Gear> getGears() {
        return gears;
    }

    public Money getMoney() {
        return money;
    }

    public Set<LocalDate> getAvailableDates() {
        return availableDates;
    }
}
