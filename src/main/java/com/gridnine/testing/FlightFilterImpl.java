package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {
    @Override
    public List<Flight> filterFlightsDepartingBeforeNow(List<Flight> flights, LocalDateTime timeBefore) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(timeBefore)))
                .collect(Collectors.toList());
    }

@Override
public List<Flight> filterFlightsWithArrivalBeforeDeparture(List<Flight> flights) {
    return flights.stream()
            .filter(flight -> flight.getSegments().stream()
                    .allMatch(segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate())))
            .collect(Collectors.toList());
}

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
