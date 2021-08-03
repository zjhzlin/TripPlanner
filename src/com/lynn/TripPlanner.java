package com.lynn;

import java.time.LocalDate;
import java.util.*;

/* 2021-04-23
** CODING KATA
**
** 2021-04-25
*  21:30 - 23:06
*
** 2021-08-02
*  20:12 - 23:14
*/

public class TripPlanner {

//    public static void findTrip(HashSet<Person> personList, HashSet<Trip> tripList) {
//        // GOAL: for each trip, print out how many people is ready?
//        // cost of each person? cost higher than budget, no go
//        ForexConverter fc = new ForexConverter();
//        double threshold = 2.00;
//        // for each trip - loop
//        for (Trip trip : tripList) {      // trip - one trip
//            int count = 0;  // how many people is ready
//            // for each person
//            for (Person person : personList) {
//                // check price in the same currency
//                // check each person whether they are ready in: price, gear, date, qualification
//                if (person.getAvailableDates().contains(trip.getDate())
//                        && person.getGears().containsAll(trip.getGears())
//                        && person.getQualifications().containsAll(trip.getQualifications())
//                        && person.getMoney().isLargerThan(trip.getPrice(), fc)
//                        || person.getMoney().isCloseTo(trip.getPrice(), fc, threshold)) {
//                    count += 1;
//                }
//            }
//            // if all met, count + 1
//            // print out trip name + count
//            System.out.println("For Trip " + trip.getName() + ", " + count + " people ready");
//        }
//    }

    public static HashMap<Trip, HashSet<Person>> findWhoReady(HashSet<Person> personList, HashSet<Trip> tripList) {
        // GOAL: for each trip, return who is ready
        // cost of each person? cost higher than budget, no go
        ForexConverter fc = new ForexConverter();
        double threshold = 2.00;
        HashMap<Trip, HashSet<Person>> result = new HashMap<>();
        // for each trip - loop
        for (Trip trip : tripList) {      // trip - one trip
            // the following has limit - if nobody is ready, still create HashSet
//            HashSet<Person> persons = new HashSet<>();
//            result.put(trip, persons);
            // for each person
            for (Person person : personList) {
                // check price in the same currency
                // check each person whether they are ready in: price, gear, date, qualification
                if (person.getAvailableDates().contains(trip.getDate())
                        && person.getGears().containsAll(trip.getGears())
                        && person.getQualifications().containsAll(trip.getQualifications())
                        && person.getMoney().isLargerThan(trip.getPrice(), fc)
                        || person.getMoney().isCloseTo(trip.getPrice(), fc, threshold)) {
                    // only add the person when there is person ready for the trip
                    if(!result.containsKey(trip)){
                        HashSet<Person> persons = new HashSet<>();
                        persons.add(person);
                        result.put(trip, persons);
                    }
                    else {
                        result.get(trip).add(person);
                    }
                }
            }


        }
        return result;
    }

    public static Trip tripWithMaxPersons(HashSet<Person> personList, HashSet<Trip> tripList) {
        // GOAL: for each trip, return who is ready
        // cost of each person? cost higher than budget, no go
        ForexConverter fc = new ForexConverter();
        double threshold = 2.00;
        HashMap<Trip, Integer> result = new HashMap<>();
        // for each trip - loop
        for (Trip trip : tripList) {      // trip - one trip
            // for each person
            for (Person person : personList) {
                // check price in the same currency
                // check each person whether they are ready in: price, gear, date, qualification
                if (person.getAvailableDates().contains(trip.getDate())
                        && person.getGears().containsAll(trip.getGears())
                        && person.getQualifications().containsAll(trip.getQualifications())
                        && person.getMoney().isLargerThan(trip.getPrice(), fc)
                        || person.getMoney().isCloseTo(trip.getPrice(), fc, threshold)) {
                    // only add the person when there is person ready for the trip
                    if (result.containsKey(trip)) {
                        result.put(trip, result.get(trip) + 1);
                    } else {
                        result.put(trip, 1);
                    }
                }
            }
        }
        int max = 0;
        Trip tripResult = null;
//        Set<Map.Entry<Trip, Integer>> entries = result.entrySet();
//        result.forEach((K,V)->{
//
//        });
        for(Trip trip: result.keySet()){
            int personNum = result.get(trip);
            if(personNum > max) {
                max = personNum;
                tripResult = trip;
            }
        }
        return tripResult;
    }


    public static void main(String[] args) {

        // Given a few people and a few trips
        // Q1. for each trip, how many people ready?
        // Q2. for a given trip, who is not ready for what?

        // trip 1 - diving trip
        // price per person
        Trip diving = new Trip("ScubaDiving", new Money(2000.00, Currency.GBP),
                new HashSet<Gear>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS)),
                LocalDate.of(2021, 5, 3),
                new HashSet<Qualification>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER)));

        // trip 2 - freediving trip
        // price per person
        Trip freeDiving = new Trip("FreeDiving",
                new Money(500.00, Currency.GBP),
                new HashSet<Gear>(Arrays.asList(Gear.FREEDIVING_FINS, Gear.FREEDIVING_WET_SUITS, Gear.FREEDIVING_MASK)),
                LocalDate.of(2021, 8, 18),
                new HashSet<Qualification>(Arrays.asList(Qualification.AIDA_2)));

        // Lynn person detail
        Person lynn = new Person("Lynn",
                new HashSet<Gear>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS)),
                new Money(2000.00, Currency.GBP),
                new HashSet<LocalDate>(Arrays.asList(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 2),
                                                                        LocalDate.of(2021, 5, 3))),
                new HashSet<Qualification>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER, Qualification.PADI_OPEN_WATER)));

        // Tao person detail
        Person tao = new Person("TAO",
                new HashSet<Gear>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS, Gear.FREEDIVING_MASK, Gear.FREEDIVING_WET_SUITS, Gear.FREEDIVING_FINS)),
                new Money(10000.00, Currency.GBP),
                new HashSet<LocalDate>(Arrays.asList(LocalDate.of(2021, 8, 18), LocalDate.of(2021, 5, 2),
                        LocalDate.of(2021, 5, 3))),
                new HashSet<Qualification>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER, Qualification.PADI_OPEN_WATER, Qualification.AIDA_2, Qualification.AIDA_3)));

        HashSet<Person> persons = new HashSet<>();
        HashSet<Trip> trips = new HashSet<>();

        persons.add(lynn);
        persons.add(tao);
        trips.add(diving);
        trips.add(freeDiving);

//        findTrip(persons, trips);

        Trip tripToGo = tripWithMaxPersons(persons, trips);

        System.out.println("Trip to go: " + tripToGo.getName());
        System.out.println("=================================");


        HashMap<Trip, HashSet<Person>> tripsAndPersons = findWhoReady(persons, trips);
        for(Trip trip: tripsAndPersons.keySet()){
            System.out.println("Trip " + trip.getName() + " ready: " );
            HashSet<Person> personPerTrip = tripsAndPersons.get(trip);
            for(Person person: personPerTrip) {
                System.out.println(person.getName());
            }
            System.out.println("-------------------------------");

        }


        // currency EURO
        // exchange rate?



    }
}
