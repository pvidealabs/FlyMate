package app.labs.idea.com.flymate;

/**
 * Created by Pavan on 1/18/2015.
 */
public class LocationDetails {
    private double logitutde;
    private double latitude;
    private String locationName;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLogitutde() {
        return logitutde;
    }

    public void setLogitutde(double logitutde) {
        this.logitutde = logitutde;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
