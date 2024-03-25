package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FlightFilterImpl implements FlightFilter {

    @Override
    public List<Segment> filterFlightsDepartingBeforeNow(List<Segment> segments, LocalDateTime timeBefore) {
        List<Segment> filteredSegments = new ArrayList<>();
        for (Segment segment : segments) {
            if (segment.getDepartureDate().isBefore(timeBefore)) {
                filteredSegments.add(segment);
            }
        }
        return filteredSegments;
    }

    @Override
    public List<Segment> filterFlightsWithArrivalBeforeDeparture(List<Segment> segments) {
        List<Segment> filteredSegments = new ArrayList<>();
        for (Segment segment : segments) {
            if (segment.getDepartureDate().isAfter(segment.getArrivalDate())) {
                filteredSegments.add(segment);
            }
        }
        return filteredSegments;
    }

    @Override
    public List<Segment> filterFlightsWithExcessiveGroundTime(List<Segment> segments) {
        segments.sort(Comparator.comparing(Segment::getArrivalDate));

        List<Segment> exceedingGroundTimeSegments = new ArrayList<>();
        for (int i = 0; i < segments.size() - 1; i++) {
            LocalDateTime arrival = segments.get(i).getArrivalDate();
            LocalDateTime departure = segments.get(i + 1).getDepartureDate();
            Duration groundTime = Duration.between(arrival, departure);
            if (groundTime.toHours() > 2) {
                exceedingGroundTimeSegments.add(segments.get(i));
                exceedingGroundTimeSegments.add(segments.get(i + 1));
            }
        }
        return exceedingGroundTimeSegments;
    }
}
