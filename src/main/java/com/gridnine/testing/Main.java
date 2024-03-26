package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

//        // Создаем список перелетов
//        List<Flight> flights = FlightBuilder.createFlights();
//
//        // Создаем объект FlightFilterImpl
//        FlightFilter flightFilter = new FlightFilterImpl();
//
//        // Получаем текущее время
//        LocalDateTime timeBefore = LocalDateTime.now();
//
//        // Выводим информацию о перелете
//        System.out.println("Перелет:");
//        for (Flight flight : flights) {
//            for (Segment segment : flight.getSegments()) {
//                System.out.println("Вылет: " + segment.getDepartureDate() + ", Прилет: " + segment.getArrivalDate());
//            }
//        }
//
//        System.out.println("-----вылет до текущего момента времени----");
//
//        // Получаем список сегментов из списка перелетов
//        List<Segment> allSegments = flights.stream()
//                .flatMap(flight -> flight.getSegments().stream())
//                .collect(Collectors.toList());
//
//        // Фильтруем перелеты с вылетом до текущего момента времени
//        System.out.println(flightFilter.filterFlightsDepartingBeforeNow(allSegments, timeBefore));
//
//        System.out.println("-----имеются сегменты с датой прилёта раньше даты вылета---");
//
//        // Фильтруем перелеты с сегментами, где дата прилета раньше даты вылета
//        System.out.println(flightFilter.filterFlightsWithArrivalBeforeDeparture(allSegments));
//
//        System.out.println("-----------------------------");
//
//        // Фильтруем перелеты с излишним временем на земле
//        System.out.println(flightFilter.filterFlightsWithExcessiveGroundTime(allSegments));
//    }

        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Текущее время: " + now);

        // Выводим начальный список перелётов
        System.out.println("Исходные перелеты:");
        flights.forEach(flight -> System.out.println(flight));

        // Применяем и выводим результаты фильтра "вылет до текущего момента времени"
        System.out.println("\nПерелеты, исключенные фильтром 'вылет до текущего момента времени':");
        List<Flight> filteredByDepartureBeforeNow = flightFilter.filterFlightsDepartingBeforeNow(flights, now);
        filteredByDepartureBeforeNow.forEach(System.out::println);

        // Применяем и выводим результаты фильтра "имеются сегменты с датой прилёта раньше даты вылета"
        System.out.println("\nПерелеты, исключенные фильтром 'имеются сегменты с датой прилёта раньше даты вылета':");
        List<Flight> filteredByArrivalBeforeDeparture = flightFilter.filterFlightsWithArrivalBeforeDeparture(flights);
        filteredByArrivalBeforeDeparture.forEach(System.out::println);

        // Применяем и выводим результаты фильтра "общее время, проведённое на земле превышает два часа"
        System.out.println("\nПерелеты, исключенные фильтром 'общее время, проведённое на земле превышает два часа':");
        List<Flight> filteredByExcessiveGroundTime = flightFilter.filterFlightsWithExcessiveGroundTime(flights);
        filteredByExcessiveGroundTime.forEach(System.out::println);
    }
    }


