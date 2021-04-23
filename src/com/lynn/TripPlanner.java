package com.lynn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2021-04-23
// CODING KATA

public class TripPlanner {

    public void findTrip(ArrayList<Person> personList, ArrayList<Trip> tripList) {
        // GOAL: for each trip, print out how many people is ready?
        // cost of each person? cost higher than budget, no go

        // for each trip - loop
        for (Trip t : tripList) {
            int count = 0;
            LocalDate tDate = t.getDate();
            for (Person p : personList) {
                // check each person whether they are ready in: price, gear, date, qualification

                // check date
                Set<LocalDate> pDates = p.getAvailableDates();
                if (pDates.contains(tDate)) {
                    count += 1;
                }

                // check price money type

                // check

                //
                

            }
            // if all met, count + 1
            // print out trip name + count
            System.out.println("For Trip " + t.getName() + ", " + count + "people ready");
        }
    }


    public static void main(String[] args) {

        // Given a few people and a few trips
        // Q1. for each trip, how many people ready?
        // Q2. for a given trip, who is not ready for what?

        // Lynn person detail
        Set<LocalDate> dateLynn = new HashSet<>(Arrays.asList(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 2),
                                                                LocalDate.of(2021, 5, 3)));
        Set<Qualification> qualificationsLynn = new HashSet<>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER, Qualification.PADI_OPEN_WATER));
        Money moneyLynn = new Money(500.00, Currency.GBP);
        Set<Gear> gearLynn = new HashSet<>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS));
        Person lynn = new Person("Lynn", gearLynn, moneyLynn, dateLynn, qualificationsLynn);

        // currency EURO
        // exchange rate?



    }
}
