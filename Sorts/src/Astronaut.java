public class Astronaut {
    private String name;
    private int year;
    private String gender;
    private int spaceFlights;
    private int flightHours;

    public Astronaut(String name, int year, String gender, int spaceFlights, int flightHours) {
        this.name = name;
        this.year = year;
        this.gender = gender;
        this.spaceFlights = spaceFlights;
        this.flightHours = flightHours;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getGender() {
        return gender;
    }

    public int getSpaceFlights() {
        return spaceFlights;
    }

    public int getFlightHours() {
        return flightHours;
    }

    @Override
    public String toString() {
        return String.format("Astronaut{name='%s', year=%d, gender='%s', flights=%d, hours=%d}",
                name, year, gender, spaceFlights, flightHours);
    }

}