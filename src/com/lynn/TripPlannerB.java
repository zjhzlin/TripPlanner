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
 *
 ** 2021-08-03
 *  07:44
 */

public class TripPlannerB {

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
                    if (!result.containsKey(trip)) {
                        HashSet<Person> persons = new HashSet<>();
                        persons.add(person);
                        result.put(trip, persons);
                    } else {
                        result.get(trip).add(person);
                    }
                }
            }


        }
        return result;
    }

    // TODO: Investigate and ensure knowledge of HashMap, HashSet, ArrayList.... (we call it collection framework)
    // TODO: The challenge is to consider all corner cases and define the method accordingly
    // TODO: Consider multiple trips has the same participants and is the max -> try to implement
    // TODO: Pay attention, when defining method definition (signature), consider all possibilities such nothing received / matched ...
    public static Optional<Trip> findTripWithMaxParticipants(HashSet<Person> personList, HashSet<Trip> tripList) {
        // GOAL: for each trip, return who is ready
        // cost of each person? cost higher than budget, no go
        ForexConverter fc = new ForexConverter();
        double threshold = 2.00;
        // TODO: self-explainable naming
        HashMap<Trip, Integer> tripAndCntParticipants = new HashMap<>();
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
                    // TODO: know how to use IntelliJ to inert if-condition, to make code readable
                    if (!tripAndCntParticipants.containsKey(trip)) {
                        // Put initial value
                        tripAndCntParticipants.put(trip, 1);
                    } else {
                        // Increase
                        tripAndCntParticipants.put(trip, tripAndCntParticipants.get(trip) + 1);
                    }
                }
            }
        }
        // TODO: Investigate Optional
        Optional<Integer> max = Optional.empty();
        Optional<Trip> tripResult = Optional.empty();
        // TODO: Investigate different approaches to iterate Map, Set, List...
//        Set<Map.Entry<Trip, Integer>> entries = tripAndCntParticipants.entrySet();
//        result.forEach((k,v)->{
//
//        });
//        tripAndCntParticipants.keySet().forEach(key->{
//
//        });
        for (Trip trip : tripAndCntParticipants.keySet()) {
            int personNum = tripAndCntParticipants.get(trip);
            if (max.isPresent() && personNum <= max.get()) {
                continue;
            } else {
                max = Optional.of(personNum);
                tripResult = Optional.of(trip);
            }
        }


        // TODO: Sort and return
//        ArrayList<Map.Entry<Trip, Integer>> entries = new ArrayList<>(tripAndCntParticipants.entrySet());
//        Collections.sort(entries, (o1, o2) -> o1.getValue() > o2.getValue() ? 1 : -1);
//        if (entries.isEmpty()) {
//            return Optional.empty();
//        } else {
//            return Optional.of(entries.get(0).getKey());
//        }

        // TODO: Max by providing comparator and use Collections.max
//        // Check if empty first
//        if (tripAndCntParticipants.isEmpty())
//            return Optional.empty();
//        else {
//            ArrayList<Map.Entry<Trip, Integer>> entries = new ArrayList<>(tripAndCntParticipants.entrySet());
//            Map.Entry<Trip, Integer> max1 = Collections.max(entries, (o1, o2) -> o1.getValue() > o2.getValue() ? 1 : -1);
//            return Optional.of(max1.getKey());
//            // Optional.ofNullable() can return Optional with consideration of null value -> null value becomes Optional.empty
//        }
//

        // TODO: This is functional programming approach using lambda
        return tripAndCntParticipants.entrySet().stream()
                .reduce((a, b) -> a.getValue() > b.getValue() ? a : b)
                .map(Map.Entry::getKey);

//        return tripResult;
    }


    public static void main(String[] args) {

        // Given a few people and a few trips
        // Q1. for each trip, how many people ready?
        // Q2. for a given trip, who is not ready for what?

        // trip 1 - diving trip
        // price per person
        // TODO: Check how to 'in-line' variable, method, and find the IntelliJ short-cut
        Trip divingTrip = new Trip(
                "ScubaDiving",
                new Money(2000.00, Currency.GBP),
                new HashSet<Gear>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS)),
                LocalDate.of(2021, 5, 3),
                new HashSet<Qualification>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER)));

        // trip 2 - freediving trip
        Money freeDivingPrice = new Money(500.00, Currency.GBP);  // price per person
        Set<Gear> freeDivingGear = new HashSet<>(Arrays.asList(Gear.FREEDIVING_FINS, Gear.FREEDIVING_WET_SUITS, Gear.FREEDIVING_MASK));
        LocalDate freeDivingDate = LocalDate.of(2021, 8, 18);
        Set<Qualification> freeDivingQ = new HashSet<>(Arrays.asList(Qualification.AIDA_2));
        Trip freeDivingTrip = new Trip("FreeDiving", freeDivingPrice, freeDivingGear, freeDivingDate, freeDivingQ);

        // Lynn person detail
        Set<LocalDate> dateLynn = new HashSet<>(Arrays.asList(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 2),
                LocalDate.of(2021, 5, 3)));
        Set<Qualification> qualificationsLynn = new HashSet<>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER, Qualification.PADI_OPEN_WATER));
        Money moneyLynn = new Money(2000.00, Currency.GBP);
        Set<Gear> gearLynn = new HashSet<>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS));
        Person lynn = new Person("Lynn", gearLynn, moneyLynn, dateLynn, qualificationsLynn);

        // Tao person detail
        Set<LocalDate> dateTao = new HashSet<>(Arrays.asList(LocalDate.of(2021, 8, 18), LocalDate.of(2021, 5, 2),
                LocalDate.of(2021, 5, 3)));
        Set<Qualification> qualificationsTao = new HashSet<>(Arrays.asList(Qualification.PADI_ADVANCED_OPEN_WATER, Qualification.PADI_OPEN_WATER, Qualification.AIDA_2, Qualification.AIDA_3));
        Money moneyTao = new Money(10000.00, Currency.GBP);
        Set<Gear> gearTao = new HashSet<>(Arrays.asList(Gear.BCD, Gear.DIVING_SUITS, Gear.FREEDIVING_MASK, Gear.FREEDIVING_WET_SUITS, Gear.FREEDIVING_FINS));
        Person tao = new Person("TAO", gearTao, moneyTao, dateTao, qualificationsTao);

        // TODO: Check how to instantiate Set / List / Array / Map with values in one line
        HashSet<Person> persons = new HashSet<>(Arrays.asList(lynn, tao));
        List<Person> insList = Arrays.asList(lynn, tao);
        Person[] insArray = new Person[]{lynn, tao};

        HashSet<Trip> trips = new HashSet<>();
        trips.add(divingTrip);
        trips.add(freeDivingTrip);

//        findTrip(persons, trips);

        Optional<Trip> tripToGo = findTripWithMaxParticipants(persons, trips);

        // TODO: This is a good example of using 'map' function of Optional - Lambda is difficult, can learn next time
        System.out.println("Trip to go: " + tripToGo.map(Trip::getName).orElse("N/A"));
        System.out.println("=================================");


        HashMap<Trip, HashSet<Person>> tripsAndPersons = findWhoReady(persons, trips);
        for (Trip trip : tripsAndPersons.keySet()) {
            System.out.println("Trip " + trip.getName() + " ready: ");
            HashSet<Person> personPerTrip = tripsAndPersons.get(trip);
            for (Person person : personPerTrip) {
                System.out.println(person.getName());
            }
            System.out.println("-------------------------------");

        }

    }
}
