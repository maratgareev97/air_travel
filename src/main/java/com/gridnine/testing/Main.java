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
        // Создаем объекты Segment
//        Segment segment1 = new Segment(LocalDateTime.of(2024, 3, 25, 10, 0), LocalDateTime.of(2024, 3, 25, 12, 0));
//        Segment segment2 = new Segment(LocalDateTime.of(2024, 3, 25, 13, 0), LocalDateTime.of(2024, 3, 25, 15, 0));
//        Segment segment11 = new Segment(LocalDateTime.of(2024, 3, 25, 10, 0), LocalDateTime.of(2024, 3, 25, 12, 0));
//        Segment segment12 = new Segment(LocalDateTime.of(2024, 3, 25, 13, 0), LocalDateTime.of(2024, 3, 25, 15, 0));
//        Segment segment3 = new Segment(LocalDateTime.of(2024, 3, 26, 10, 0), LocalDateTime.of(2024, 3, 26, 12, 0));
//        Segment segment4 = new Segment(LocalDateTime.of(2024, 3, 26, 13, 0), LocalDateTime.of(2024, 3, 26, 15, 0));
//        Segment segment5 = new Segment(LocalDateTime.of(2024, 3, 27, 10, 0), LocalDateTime.of(2024, 3, 27, 12, 0));
//        Segment segment6 = new Segment(LocalDateTime.of(2024, 3, 27, 13, 0), LocalDateTime.of(2024, 3, 27, 15, 0));
//        Segment segment7 = new Segment(LocalDateTime.of(2024, 3, 28, 10, 0), LocalDateTime.of(2024, 3, 28, 12, 0));
//        Segment segment8 = new Segment(LocalDateTime.of(2024, 3, 28, 13, 0), LocalDateTime.of(2024, 3, 28, 15, 0));
//        Segment segment9 = new Segment(LocalDateTime.of(2024, 3, 29, 10, 0), LocalDateTime.of(2024, 3, 29, 12, 0));
//        Segment segment10 = new Segment(LocalDateTime.of(2024, 3, 29, 13, 0), LocalDateTime.of(2023, 3, 29, 15, 0));
//
//        // Создаем список сегментов для Flight
//        List<Segment> segments = new ArrayList<>(List.of(segment1, segment2, segment3, segment4, segment5, segment6, segment7, segment8, segment9, segment10, segment11, segment12));
//
//        // Создаем объект Flight
//        Flight flight = new Flight(segments);
//        FlightFilter flightFilter = new FlightFilterImpl();
//
//        // Выводим информацию о перелете
//        System.out.println("Перелет:");
//        for (Segment segment : flight.getSegments()) {
//            System.out.println("Вылет: " + segment.getDepartureDate() + ", Прилет: " + segment.getArrivalDate());
//        }
//
//
//        System.out.println("-----вылет до текущего момента времени----");
//        LocalDateTime timeBefore = LocalDateTime.of(2024, 3, 27, 12, 0);
//        System.out.println(flightFilter.filterFlightsDepartingBeforeNow(segments,timeBefore));
//
//
//        System.out.println("-----имеются сегменты с датой прилёта раньше даты вылета---");
//        System.out.println(flightFilter.filterFlightsWithArrivalBeforeDeparture(segments));
//
//        System.out.println("-----------------------------");
//        System.out.println(flightFilter.filterFlightsWithExcessiveGroundTime(segments));

        // Создаем список перелетов
        List<Flight> flights = FlightBuilder.createFlights();

        // Создаем объект FlightFilterImpl
        FlightFilter flightFilter = new FlightFilterImpl();

        // Получаем текущее время
        LocalDateTime timeBefore = LocalDateTime.now();

        // Выводим информацию о перелете
        System.out.println("Перелет:");
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                System.out.println("Вылет: " + segment.getDepartureDate() + ", Прилет: " + segment.getArrivalDate());
            }
        }

        System.out.println("-----вылет до текущего момента времени----");

        // Получаем список сегментов из списка перелетов
        List<Segment> allSegments = flights.stream()
                .flatMap(flight -> flight.getSegments().stream())
                .collect(Collectors.toList());

        // Фильтруем перелеты с вылетом до текущего момента времени
        System.out.println(flightFilter.filterFlightsDepartingBeforeNow(allSegments, timeBefore));

        System.out.println("-----имеются сегменты с датой прилёта раньше даты вылета---");

        // Фильтруем перелеты с сегментами, где дата прилета раньше даты вылета
        System.out.println(flightFilter.filterFlightsWithArrivalBeforeDeparture(allSegments));

        System.out.println("-----------------------------");

        // Фильтруем перелеты с излишним временем на земле
        System.out.println(flightFilter.filterFlightsWithExcessiveGroundTime(allSegments));
    }
//        List<Flight> flights = FlightBuilder.createFlights();
//        FlightFilter beforeNowFilter = new DepartingBeforeNowFilter(LocalDateTime.now());
//        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
//        FlightFilter excessiveGroundTimeFilter = new ExcessiveGroundTimeFilter();
//
//        System.out.println("Filtered by Departing Before Now:");
//        List<Flight> filteredByDeparture = beforeNowFilter.filterFlights(flights);
//        filteredByDeparture.forEach(System.out::println);
//
//        System.out.println("\nFiltered by Arrival Before Departure:");
//        List<Flight> filteredByArrival = arrivalBeforeDepartureFilter.filterFlights(flights);
//        filteredByArrival.forEach(System.out::println);
//
//        System.out.println("\nFiltered by Excessive Ground Time:");
//        List<Flight> filteredByGroundTime = excessiveGroundTimeFilter.filterFlights(flights);
//        filteredByGroundTime.forEach(System.out::println);
}

