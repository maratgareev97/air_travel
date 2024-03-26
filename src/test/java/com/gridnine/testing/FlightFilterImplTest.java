package com.gridnine.testing;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightFilterImplTest {
    @Test
    public void testFilterFlightsDepartingBeforeNow() {
        // Arrange
        LocalDateTime timeBefore = LocalDateTime.now().plusDays(3);  // Сдвигаем время на 3 дня вперед
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        // Act
        List<Flight> filteredFlights = flightFilter.filterFlightsDepartingBeforeNow(flights, timeBefore);

        // Assert
        assertEquals(5, filteredFlights.size()); // Ожидаем, что будет исключен один перелет
    }

    @Test
    public void testFilterFlightsWithArrivalBeforeDeparture() {
        // Arrange
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        // Act
        List<Flight> filteredFlights = flightFilter.filterFlightsWithArrivalBeforeDeparture(flights);

        // Assert
        assertEquals(5, filteredFlights.size()); // Ожидаем, что будет исключен один перелет
    }

    @Test
    public void testFilterFlightsWithExcessiveGroundTime() {
        // Arrange
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter flightFilter = new FlightFilterImpl();

        // Act
        List<Flight> filteredFlights = flightFilter.filterFlightsWithExcessiveGroundTime(flights);

        // Assert
        // Проверяем количество перелетов, прошедших через фильтр, адаптируйте ожидаемое значение согласно вашей логике
        assertEquals(4, filteredFlights.size());
    }
}
