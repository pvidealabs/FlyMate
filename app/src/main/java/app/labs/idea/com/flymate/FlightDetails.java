package app.labs.idea.com.flymate;

/**
 * Created by vijin on 18/01/2015.
 */
public class FlightDetails {
    private String timings;
    private String flightNames;
    private String duration;
    private String stops;
    private String stopCities;

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getFlightNames() {
        return flightNames;
    }

    public void setFlightNames(String flightNames) {
        this.flightNames = flightNames;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getStopCities() {
        return stopCities;
    }

    public void setStopCities(String stopCities) {
        this.stopCities = stopCities;
    }
}
