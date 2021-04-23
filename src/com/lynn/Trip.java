package com.lynn;

import java.time.LocalDate;
import java.util.Set;

public class Trip {

    // String trip name
    private String name;
    // int budget
    private Money price;
    // ArrayList<String> gear?
    private Set<Gear> gears;
    // String date
    // private String date;  // not as good as LocalDate
    private LocalDate date;
    // required qualification
    private Set<Qualification> qualifications;

    public Trip(String name, Money price, Set<Gear> gears, LocalDate date, Set<Qualification> qualifications) {
        this.name = name;
        this.price = price;
        this.gears = gears;
        this.date = date;
        this.qualifications = qualifications;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Set<Gear> getGears() {
        return gears;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }
}
