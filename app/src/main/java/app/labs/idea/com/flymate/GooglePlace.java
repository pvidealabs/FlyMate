package app.labs.idea.com.flymate;

public class GooglePlace {
    private String name;

    private double lat;
    private double lng;

    public GooglePlace() {
        this.name = "";
        this.lat = 0;
        this.lng = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }
}