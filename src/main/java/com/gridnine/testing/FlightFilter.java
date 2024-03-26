package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightFilter {
    public List<Flight> filterFlightsDepartingBeforeNow(List<Flight> flights, LocalDateTime timeBefore);

    public List<Flight> filterFlightsWithArrivalBeforeDeparture(List<Flight> flights);

    public List<Flight> filterFlightsWithExcessiveGroundTime(List<Flight> flights);

}

