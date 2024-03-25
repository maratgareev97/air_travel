package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightFilterImplTest {

    @Test
    public void testFilterFlightsDepartingBeforeNow() {
        // Arrange
        LocalDateTime timeBefore = LocalDateTime.now();
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        // Act
        List<Segment> filteredSegments = flightFilter.filterFlightsDepartingBeforeNow(flights.get(0).getSegments(), timeBefore);

        // Assert
        assertEquals(0, filteredSegments.size()); // Ожидаем, что будет один перелёт
    }

    @Test
    public void testFilterFlightsWithArrivalBeforeDeparture() {
        // Arrange
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        // Act
        List<Segment> filteredSegments = flightFilter.filterFlightsWithArrivalBeforeDeparture(flights.get(3).getSegments());

        // Assert
        assertEquals(1, filteredSegments.size()); // Ожидаем, что будет один перелёт
    }

    @Test
    public void testFilterFlightsWithExcessiveGroundTime() {
        // Arrange
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        // Act
        List<Segment> filteredSegments = flightFilter.filterFlightsWithExcessiveGroundTime(flights.get(4).getSegments());

        // Assert
        assertEquals(2, filteredSegments.size()); // Ожидаем, что будет четыре перелёта
    }
}
