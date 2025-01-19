package gmail.fatihmergunqa.utils;

/**
 * Utility class that provides API endpoint paths and the base URI for API requests.
 * This class is designed as a utility class, containing static members to represent
 * various API-related constants such as the base URI and endpoint paths.
 * The base URI is initialized dynamically using configuration properties.
 * The class cannot be instantiated as it includes a private constructor.
 */
public class API {
    /**
     * The base URI for all API requests.
     * This value is initialized by reading the configuration file specified in
     * {@link Constants#CONFIGURATION_FILEPATH}.
     */
    public static final String BASE_URI;

    // Endpoints

    // Static block for initializing the BASE_URI
    static {
        BASE_URI = Configs.getProperty("api_uri");
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private API() {
    }
}