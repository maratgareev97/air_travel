package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilter {
    public List<Flight> filterFlightsDepartingBeforeNow(List<Flight> flights, LocalDateTime timeBefore);
//    public List<Segment> filterFlightsDepartingBeforeNow(List<Segment> list, LocalDateTime timeBefore);

    public List<Flight> filterFlightsWithArrivalBeforeDeparture(List<Flight> flights);
//    public List<Segment> filterFlightsWithArrivalBeforeDeparture(List<Segment> segments);

    public List<Flight> filterFlightsWithExcessiveGroundTime(List<Flight> flights);
//    public List<Segment> filterFlightsWithExcessiveGroundTime(List<Segment> segments);
//    List<Flight> filterFlights(List<Flight> flights);

}


//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public interface FlightFilter {
//    List<Flight> filterFlights(List<Flight> flights);
//}

