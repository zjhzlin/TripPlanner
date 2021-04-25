package com.lynn;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 2021-04-23
** CODING KATA
**
** 2021-04-25
*  21:30 - 23:06
*/

public class TripPlanner {

    public void findTrip(HashSet<Person> personList, HashSet<Trip> tripList) {
        // GOAL: for each trip, print out how many people is ready?
        // cost of each person? cost higher than budget, no go
        ForexConverter fc = new ForexConverter();
        double threshold = 2.00;
        // for each trip - loop
        for (Trip trip : tripList) {      // trip - one trip
            int count = 0;  // how many people is ready
            // for each person
            for (Person person : personList) {
                // check price in the same currency
                // check each person whether they are ready in: price, gear, date, qualification
                if (person.getAvailableDates().contains(trip.getDate())
                        && person.getGears().containsAll(trip.getGears())
                        && person.getQualifications().containsAll(trip.getQualifications())
                        && person.getMoney().isLargerThan(trip.getPrice(), fc)
                        || person.getMoney().isCloseTo(trip.getPrice(), fc, threshold)) {
                    count += 1;
                }
            }
            // if all met, count + 1
            // print out trip name + count
            System.out.println("For Trip " + trip.getName() + ", " + count + "people ready");
        }
    }

    public static void main(String[] args) {

        // Given a few people and a few trips
        // Q1. for each trip, how many people ready?
        // Q2. for a given trip, who is not ready for what?

        // trip 1 - diving trip
        Money divingPrice = new Money(2000.00, Currency.GBP);  // price per person
        Set<Gear> divingGear = new HashSet<>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS));
        LocalDate divingDate = LocalDate.of(2021, 5, 3);
        Set<Qualification> divingQ = new HashSet<>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER));
        Trip diving = new Trip("Diving", divingPrice, divingGear, divingDate, divingQ);

        // Lynn person detail
        Set<LocalDate> dateLynn = new HashSet<>(Arrays.asList(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 2),
                                                                LocalDate.of(2021, 5, 3)));
        Set<Qualification> qualificationsLynn = new HashSet<>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER, Qualification.PADI_OPEN_WATER));
        Money moneyLynn = new Money(500.00, Currency.GBP);
        Set<Gear> gearLynn = new HashSet<>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS));
        Person lynn = new Person("Lynn", gearLynn, moneyLynn, dateLynn, qualificationsLynn);

        // findTrip(HashSet<Person> personList, HashSet<Trip> tripList)

        // currency EURO
        // exchange rate?



    }
}
