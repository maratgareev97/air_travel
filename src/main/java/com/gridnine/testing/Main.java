package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Текущее время: " + now);

        System.out.println("Исходные перелеты:");
        flights.forEach(flight -> System.out.println(flight));

        System.out.println("\nПерелеты, исключенные фильтром 'вылет до текущего момента времени':");
        List<Flight> filteredByDepartureBeforeNow = flightFilter.filterFlightsDepartingBeforeNow(flights, now);
        filteredByDepartureBeforeNow.forEach(System.out::println);

        System.out.println("\nПерелеты, исключенные фильтром 'имеются сегменты с датой прилёта раньше даты вылета':");
        List<Flight> filteredByArrivalBeforeDeparture = flightFilter.filterFlightsWithArrivalBeforeDeparture(flights);
        filteredByArrivalBeforeDeparture.forEach(System.out::println);

        System.out.println("\nПерелеты, исключенные фильтром 'общее время, проведённое на земле превышает два часа':");
        List<Flight> filteredByExcessiveGroundTime = flightFilter.filterFlightsWithExcessiveGroundTime(flights);
        filteredByExcessiveGroundTime.forEach(System.out::println);
    }
    }


