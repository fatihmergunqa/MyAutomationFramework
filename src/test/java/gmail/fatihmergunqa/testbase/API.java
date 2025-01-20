package gmail.fatihmergunqa.testbase;

import gmail.fatihmergunqa.utils.Configs;
import gmail.fatihmergunqa.utils.Constants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for managing API configurations and endpoint paths.
 * This class provides a centralized location for defining the base URI for API requests
 * and managing endpoint paths. It utilizes static initialization to set up these
 * configurations dynamically at runtime by reading properties from a configuration file.
 * The class is part of the test base structure and extends {@link PageInitializer} to
 * integrate with other components of the test framework.
 */
public class API extends PageInitializer {

    /**
     * The base URI for all API requests.
     * This constant is dynamically initialized by reading the `api_uri` property
     * from the configuration file specified in {@link Constants#CONFIGURATION_FILEPATH}.
     */
    public static final String BASE_URI;

    /**
     * A map containing endpoint paths for various API operations.
     * Each key in the map is a descriptive identifier (e.g., "GET_LIST_USERS"),
     * and its corresponding value is the relative path to the endpoint
     * (e.g., "/api/users/").
     */
    public static Map<String, String> endpoints = new HashMap<>();

    /**
     * A reusable {@link RequestSpecification} object for configuring and executing API requests.
     * This object can be initialized and reused across different test cases.
     */
    public static RequestSpecification request;

    /**
     * A reusable {@link Response} object for handling the responses of API requests.
     * This object can be used to store and validate the response data in test cases.
     */
    public static Response response;

    /**
     * A static block to initialize the base URI and populate the endpoints map.
     * This block is executed when the class is loaded into memory. It performs the following:
     * - Reads the configuration file using {@link Configs#readProperties(String)}.
     * - Initializes the `BASE_URI` with the value of the `api_uri` property.
     * - Populates the `endpoints` map with predefined endpoint paths.
     */
    static {
        // Load configuration properties from the specified file
        Configs.readProperties(Constants.CONFIGURATION_FILEPATH);

        // Initialize the base URI from the configuration
        BASE_URI = Configs.getProperty("api_uri");

        // Define API endpoint paths
        endpoints.put("GET_LIST_USERS", "/api/users/");
        endpoints.put("GET_SINGLE_USER", "/api/users/{id}");
        endpoints.put("POST_CREATE_USER", "/api/users/");
        endpoints.put("PUT_UPDATE_USER", "/api/users/2");
        endpoints.put("DELETE_USER", "/api/users/2");
        endpoints.put("POST_REGISTER", "/api/register/");
        endpoints.put("POST_LOGIN", "/api/login/");
    }
}