package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlightFilterImpl implements FlightFilter {

    //    @Override
//    public List<Segment> filterFlightsDepartingBeforeNow(List<Segment> segments, LocalDateTime timeBefore) {
//        List<Segment> filteredSegments = new ArrayList<>();
//        for (Segment segment : segments) {
//            if (segment.getDepartureDate().isBefore(timeBefore)) {
//                filteredSegments.add(segment);
//            }
//        }
//        return filteredSegments;
//    }
    @Override
    public List<Flight> filterFlightsDepartingBeforeNow(List<Flight> flights, LocalDateTime timeBefore) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean excludeFlight = false;
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(timeBefore)) {
                    excludeFlight = true;
                    break;
                }
            }
            if (!excludeFlight) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }



//    @Override
//    public List<Segment> filterFlightsWithArrivalBeforeDeparture(List<Segment> segments) {
//        List<Segment> filteredSegments = new ArrayList<>();
//        for (Segment segment : segments) {
//            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
//                filteredSegments.add(segment);
//            }
//        }
//        return filteredSegments;
//    }
@Override
public List<Flight> filterFlightsWithArrivalBeforeDeparture(List<Flight> flights) {
    List<Flight> filteredFlights = new ArrayList<>();
    for (Flight flight : flights) {
        boolean isValidFlight = true;
        for (Segment segment : flight.getSegments()) {
            if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                isValidFlight = false;
                break;
            }
        }
        if (isValidFlight) {
            filteredFlights.add(flight);
        }
    }
    return filteredFlights;
}



//    @Override
//    public List<Segment> filterFlightsWithExcessiveGroundTime(List<Segment> segments) {
//        segments.sort(Comparator.comparing(Segment::getArrivalDate));
//
//        List<Segment> exceedingGroundTimeSegments = new ArrayList<>();
//        for (int i = 0; i < segments.size() - 1; i++) {
//            LocalDateTime arrival = segments.get(i).getArrivalDate();
//            LocalDateTime departure = segments.get(i + 1).getDepartureDate();
//            Duration groundTime = Duration.between(arrival, departure);
//            if (groundTime.toHours() > 2) {
//                exceedingGroundTimeSegments.add(segments.get(i));
//                exceedingGroundTimeSegments.add(segments.get(i + 1));
//            }
//        }
//        return exceedingGroundTimeSegments;
//    }
@Override
public List<Flight> filterFlightsWithExcessiveGroundTime(List<Flight> flights) {
    List<Flight> filteredFlights = new ArrayList<>();
    for (Flight flight : flights) {
        List<Segment> segments = flight.getSegments();
        boolean isValidFlight = true;
        long totalGroundTime = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            Duration groundTime = Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
            totalGroundTime += groundTime.toMinutes();
            if (groundTime.toHours() > 2) {
                isValidFlight = false;
                break;
            }
        }
        if (isValidFlight && totalGroundTime <= 120) {
            filteredFlights.add(flight);
        }
    }
    return filteredFlights;
}

}
