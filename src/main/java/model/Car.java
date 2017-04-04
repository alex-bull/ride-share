package model;

public class Car {

    private String type;
    private String model;
    private String colour;
    private String license;
    private String year;
    private String seatNum;

    Car(String type, String model, String colour, String license, String year, String seatNum) {
        this.type = type;
        this.model = model;
        this.colour = colour;
        this.license = license;
        this.year = year;
        this.seatNum = seatNum;
    }

    @Override
    public String toString(){
        return model + " " + year;
    }


    //public double getLatitude() { return lat; }

    //public void setLatitude(double latitude) { this.lat = latitude; }

}
