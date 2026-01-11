import java.util.Comparator;

public class AstronautSorters {
    // By name
    public static final Comparator<Astronaut> BY_NAME =
            Comparator.comparing(Astronaut::getName);

    // By number of flights hrs desc
    public static final Comparator<Astronaut> BY_FLIGHTS_HRS_DESC =
            Comparator.comparingInt(Astronaut::getFlightHours).reversed();

    // By number of flights hrs
    public static final Comparator<Astronaut> BY_FLIGHTS_HRS =
            Comparator.comparingInt(Astronaut::getFlightHours);
    // By year
    public static final Comparator<Astronaut> BY_YEAR =
            Comparator.comparingInt(Astronaut::getYear);
}