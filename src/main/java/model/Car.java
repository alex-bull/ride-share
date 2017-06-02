package model;

public class Car {

    private String type;
    private String model;
    private String colour;
    private String license;
    private String year;
    private Integer seatNum;

    public Car(String type, String model, String colour, String license, String year, Integer seatNum) {
        this.type = type;
        this.model = model;
        this.colour = colour;
        this.license = license;
        this.year = year;
        this.seatNum = seatNum;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getLicense() {
        return license;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }


    @Override
    public String toString(){
        return model + " " + year;
    }


    //public double getLatitude() { return lat; }

    //public void setLatitude(double latitude) { this.lat = latitude; }

}
