import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

public class CityDataLoader {

    private final ObjectMapper objectMapper;

    public CityDataLoader() {
        // main class in Jackson library - takes json file and maps it to java object
        this.objectMapper = new ObjectMapper();
    }

    public List<City> loadCities(String jsonContent) {
        try {
            // Java Type Erasure problem solves new TypeReference<List<City>>() {}
            return objectMapper.readValue(
                    jsonContent,
                    new TypeReference<List<City>>() {}
            );

        } catch (IOException e) {
            throw new RuntimeException("Data loading from JSON has failed.", e);
        }
    }
}