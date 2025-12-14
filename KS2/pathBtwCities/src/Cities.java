import java.util.*;

public class Cities {

    private final List<City> cities;

    public Cities(List<City> cities) {
        this.cities = cities;
    }

    public List<City> getCities() {
        return cities;
    }

    public City getFirstCity() {
        return cities.get(0);
    }

    public City findByUUID(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        for (City city : cities) {
            if (city.id().equals(uuid)) {
                return city;
            }
        }
        return null;
    }

    public UUID findByName(String name) {
        if (name == null) {
            return null;
        }
        for (City city : cities) {
            if (city.name().equalsIgnoreCase(name.trim())) {
                return city.id();
            }
        }
        return null; // City was not found
    }

    public boolean cityExists(UUID uuid) {
        for (City city : cities) {
            if (city.id().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
