import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader(String configFileName) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
                throw new IllegalArgumentException("Config file not found: " + configFileName);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load config file: " + configFileName, ex);
        }
    }

    public int[] getFillerPositions() {
        String positions = properties.getProperty("filler.positions");
        return Stream.of(positions.split(",")).mapToInt(Integer::parseInt).toArray();
    }

    public String getFillerCharacter() {
        return properties.getProperty("filler.character");
    }

    public int getFillerLength() {
        return Integer.parseInt(properties.getProperty("filler.length"));
    }

    public int getRecordNumberStart() {
        return Integer.parseInt(properties.getProperty("recordNumber.start"));
    }

    public int getRecordNumberLength() {
        return Integer.parseInt(properties.getProperty("recordNumber.length"));
    }

    public int getLineLength() {
        return Integer.parseInt(properties.getProperty("line.length"));
    }
}

