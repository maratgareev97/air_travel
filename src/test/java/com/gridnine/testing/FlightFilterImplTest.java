package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightFilterImplTest {
    @Test
    public void testFilterFlightsDepartingBeforeNow() {

        LocalDateTime timeBefore = LocalDateTime.now().plusDays(3);
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        List<Flight> filteredFlights = flightFilter.filterFlightsDepartingBeforeNow(flights, timeBefore);

        assertEquals(5, filteredFlights.size());
    }

    @Test
    public void testFilterFlightsWithArrivalBeforeDeparture() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        List<Flight> filteredFlights = flightFilter.filterFlightsWithArrivalBeforeDeparture(flights);

        assertEquals(5, filteredFlights.size());
    }

    @Test
    public void testFilterFlightsWithExcessiveGroundTime() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        List<Flight> filteredFlights = flightFilter.filterFlightsWithExcessiveGroundTime(flights);

        assertEquals(4, filteredFlights.size());
    }
}
