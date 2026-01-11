import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AstronautLoader {

    private static final int EXPECTED_FIELD_COUNT = 5;

    public static List<Astronaut> loadAstronauts(String csvFilePath, String delimiter) throws IOException {
        List<Astronaut> astronauts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                try {
                    Astronaut astronaut = parseLine(line, delimiter);
                    if (astronaut != null) {
                        astronauts.add(astronaut);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    // Log parsing err
                    System.err.println("Skipping invalid line: " + line + " -> " + e.getMessage());
                }
            }
        }
        return astronauts;
    }

    // Convert line to Astronaut
    private static Astronaut parseLine(String line, String delimiter) {
        String[] values = line.split(delimiter);

        if (values.length != EXPECTED_FIELD_COUNT) {
            return null;
        }

        String name = values[0].trim();
        int year = Integer.parseInt(values[1].trim());
        String gender = values[2].trim();
        int spaceFlights = Integer.parseInt(values[3].trim());
        int flightHours = Integer.parseInt(values[4].trim());

        return new Astronaut(name, year, gender, spaceFlights, flightHours);
    }
}